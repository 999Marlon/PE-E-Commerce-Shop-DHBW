import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { Product } from './products.service';

export interface Order {
  id: string;
  userId: string;
  date?: string;
  total?: number;
  status: string;
  productIds: string[];
  address:any;
  products?: {
    id: string;
    name: string;
    price: number;
    quantity: number;
  }[];
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = 'http://localhost:8080/orders';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  placeOrder(userId: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/place/${userId}`, {}, { headers: this.getHeaders() });
  }

  getUserOrders(userId: string): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}/${userId}`, { headers: this.getHeaders() });
  }

  updateOrderStatus(orderId: string, status: string): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/update-status/${orderId}?status=${status}`, {}, { headers: this.getHeaders() });
  }
}
