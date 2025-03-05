import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductsService, Product } from '../../services/products.service'; 
import { CartService } from '../../services/cart.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { NavbarComponent } from "../../navbar/navbar.component";
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-detail',
  imports: [CurrencyPipe, CommonModule, NavbarComponent],
  templateUrl: './product-detail.component.html',
  styleUrl: './product-detail.component.css',
  standalone: true

})
export class ProductDetailComponent implements OnInit {
  product: Product | null = null;
  showLoginModal = false; 


  constructor(
    private route: ActivatedRoute,
    private productService: ProductsService,
    private cartService: CartService,
    private authService: AuthService,
    private router: Router)
    {}

  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id'); 

    if (productId) {
      console.log('Produkt-ID aus der URL:', productId);
      this.productService.getProductById(productId).subscribe((data) => {
        this.product = data;
      }, error => {
        console.error('Fehler beim Laden des Produkts:', error);
      });
    } else {
      console.error('Fehler: Keine Produkt-ID gefunden');
    }
  }
  
  addToCart(): void {
    if (!this.authService.isLoggedIn()) {
      this.showLoginModal = true;
      return;
    }
  
    const userId = this.authService.getUserId(); 
    const productId = this.product?.id.toString(); 

    console.log(userId, productId)
  
    if (userId && productId) {
      this.cartService.addToCart(userId, productId).subscribe({
        next: () => {
          alert('Produkt wurde in den Warenkorb gelegt!');
        },
        error: (err) => {
          console.error('Fehler beim Hinzufügen zum Warenkorb:', err);
          alert('Fehler beim Hinzufügen zum Warenkorb!');
        }
      });
    } else {
      console.error('Fehlende IDs:', { userId, productId });
    }
  }
  
  
  goToLogin(): void {
    this.showLoginModal = false;
    this.router.navigate(['/login']); 
  }

  closeModal(): void {
    this.showLoginModal = false; 
  }
}