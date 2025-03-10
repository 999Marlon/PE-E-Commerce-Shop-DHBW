import { Component, OnInit } from '@angular/core';
import { CartService } from '../../services/cart.service';
import { AuthService } from '../../services/auth.service';
import { Product, ProductsService } from '../../services/products.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { NavbarComponent } from "../../navbar/navbar.component";
import { OrderService } from '../../services/orders.service';
import { Router } from '@angular/router';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss'],
  imports: [CommonModule, FormsModule, NavbarComponent],
  standalone: true
})
export class CartComponent implements OnInit {
  cartItems: { product: Product, amount: number }[] = [];
  totalPrice: number = 0;
  userId: string | null = null;

  estimatedDeliveryDate:string =""

  constructor(private cartService: CartService, private authService: AuthService, private orderService:OrderService, private router:Router, private productService:ProductsService) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    if (this.userId) {
      this.loadCart();
    }
  }

  loadCart(): void {
    this.cartService.getCart(this.userId!).subscribe(response => {
      const productIDs = response.productIds
      this.loadProductDetails(productIDs);
    });
  }

  loadProductDetails(productIds: string[]): void {
    const productDetailsObservables = productIds.map(id => this.productService.getProductById(id));

    forkJoin(productDetailsObservables).subscribe({
      next: (prodcts:Product[]) => {
        const groupedItems = this.groupCartItems(prodcts);
        this.cartItems = groupedItems;
        this.calculateTotal();
      },
      error(err) {
        console.error('Fehler beim Laden der Produktdetails:', err);
      }
    });
  }

  groupCartItems(products: Product[]): { product: Product, amount: number }[] {
    const groupedMap = new Map<string, { product: Product, amount: number }>();

    products.forEach(product => {
      if (groupedMap.has(product.id.toString())) {
        groupedMap.get(product.id.toString())!.amount += 1;
      } else {
        groupedMap.set(product.id.toString(), { product, amount: 1 });
      }
    });

    return Array.from(groupedMap.values());
  }
  
  

  calculateTotal(): void {
    if (!Array.isArray(this.cartItems)) {
      console.error('Fehler: cartItems ist kein Array', this.cartItems);
      this.totalPrice = 0;
      return;
    }
  
    this.totalPrice = this.cartItems.reduce((sum, item) => sum + (item.product.price * item.amount), 0);
  }
  
  

  removeItem(productId: string): void {
    if (this.userId) {
      this.cartService.removeFromCart(this.userId, productId).subscribe(() => {
        this.cartItems = this.cartItems.filter(item => item.product.id.toString() !== productId);
        this.calculateTotal();
      });
    }
  }
  

  increaseAmount(item: { product: Product, amount: number }): void {
    if (!this.userId) return;
    item.amount += 1;
    
    this.cartService.updateProductQuantity(this.userId, item.product.id.toString(), item.amount).subscribe({
      next: () => this.calculateTotal(),
      error: (err) => console.error('Fehler beim Aktualisieren der Menge:', err)
    });
  }
  
  decreaseAmount(item: { product: Product, amount: number }): void {
    if (!this.userId) return;
  
    if (item.amount > 1) {
      item.amount -= 1;
      this.cartService.updateProductQuantity(this.userId, item.product.id.toString(), item.amount).subscribe({
        next: () => this.calculateTotal(),
        error: (err) => console.error('Fehler beim Aktualisieren der Menge:', err)
      });
    } else {
      this.removeItem(item.product.id.toString());
    }
  }

  checkout(): void {
    if (!this.userId) return;
  
    this.orderService.placeOrder(this.userId).subscribe({
      next: (response) => {
        alert('Bestellung erfolgreich!');

        this.cartItems = [];
        this.totalPrice = 0;
  
        const orderId = response.id; 
        this.router.navigate(['/order-confirmation'], { queryParams: { orderId } }); 
      },
      error: (err) => {
        console.error('Fehler beim Bestellen:', err);
        alert('Fehler beim Bestellen! Bitte versuchen Sie es erneut.');
      }
    });
  }
  
  
  
}
