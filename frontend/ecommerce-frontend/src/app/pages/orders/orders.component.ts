import { Component, OnInit } from '@angular/core';
import { OrderService, Order } from '../../services/orders.service';
import { AuthService } from '../../services/auth.service';
import { CommonModule, CurrencyPipe } from '@angular/common';
import { NavbarComponent } from "../../navbar/navbar.component";


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

  constructor(private orderService: OrderService, private authService: AuthService) {}

  ngOnInit(): void {
    this.userId = this.authService.getUserId();
    if (this.userId) {
      this.loadOrders();
    }
  }

  loadOrders(): void {
    this.orderService.getUserOrders(this.userId!).subscribe({
      next: (orders) => {
        this.orders = orders.map(order => ({
          ...order,
          products: this.groupOrderItems(order.products) 
        }));
      },
      error: (err) => console.error('Fehler beim Laden der Bestellungen:', err)
    });
  }

  groupOrderItems(products: { id: string; name: string; price: number }[]): { id: string; name: string; price: number; quantity: number }[] {
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
