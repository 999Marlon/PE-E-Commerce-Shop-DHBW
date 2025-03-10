import { Component, OnInit } from '@angular/core';
import { OrderService, Order } from '../../services/orders.service';
import { AuthService } from '../../services/auth.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { NavbarComponent } from "../../navbar/navbar.component";
import { forkJoin, map } from 'rxjs';
import { ProductsService, Product } from '../../services/products.service';

@Component({
  selector: 'app-orders',
  imports: [CommonModule, CurrencyPipe, NavbarComponent],
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss'],
  standalone: true
})
export class OrdersComponent implements OnInit {
  orders: Order[] = [];
  userId: string | null = null;

  constructor(private orderService: OrderService, private authService: AuthService, private prouctService:ProductsService) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    if (this.userId) {
      this.loadOrders();
    }
  }

  loadOrders(): void {
    this.orderService.getUserOrders(this.userId!).subscribe({
      next: (orders) => {
        const orderRequests = orders.map(order => {
          const productDetailsObservables = order.productIds.map((id: string) => this.prouctService.getProductById(id));

          return forkJoin(productDetailsObservables).pipe(
            map((productDetails:Product[]) => ({
              ...order,
              products: this.groupOrderItems(productDetails) 
            }))
          );
        });

        forkJoin(orderRequests).subscribe({
          next: (orderDetails) => {
            this.orders = orderDetails;
          },
          error: (err) => console.error('Fehler beim Laden der Bestellungen:', err)
        });
      },
      error: (err) => console.error('Fehler beim Laden der Bestellungen:', err)
    });
  }
  
  groupOrderItems(products: any[]): { id: string; name: string; price: number; quantity: number }[] {
    const groupedMap = new Map<string, { id: string; name: string; price: number; quantity: number }>();

    products.forEach(product => {
      if (groupedMap.has(product.id)) {
        groupedMap.get(product.id)!.quantity += 1;
      } else {
        groupedMap.set(product.id, { ...product, quantity: 1 });
      }
    });
    return Array.from(groupedMap.values());
  }

}
