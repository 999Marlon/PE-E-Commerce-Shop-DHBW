import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
  private apiUrl = 'http://localhost:8080/cart';

  constructor(private http: HttpClient, private authService: AuthService) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`
    });
  }

  getCart(userId: string): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${userId}`, { headers: this.getHeaders() });
  }

  addToCart(userId: string, productId: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/${userId}/add/${productId}`, {}, { headers: this.getHeaders() });
  }

  removeFromCart(userId: string, productId: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${userId}/remove/${productId}`, { headers: this.getHeaders() });
  }

  updateProductQuantity(userId: string, productId: string, quantity: number): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${userId}/update-quantity/${productId}?quantity=${quantity}`, {}, { headers: this.getHeaders() });
  }

}
