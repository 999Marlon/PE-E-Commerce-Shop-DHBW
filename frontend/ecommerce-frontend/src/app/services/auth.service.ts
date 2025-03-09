import { Injectable, inject, PLATFORM_ID } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { isPlatformBrowser } from '@angular/common';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  private http = inject(HttpClient);
  private platformId = inject(PLATFORM_ID);


  login(email: string, password: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });

    return this.http.post(`${this.apiUrl}/login`, { email, password }, { headers }).pipe(
      tap((response: any) => {
        if (response.token) {
          localStorage.setItem('token', response.token); 
          localStorage.setItem('userId', response.userId);
        }
      })
    );
  }

  getUserId(): string | null {
    return localStorage.getItem('userId'); 
  }
  

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      return localStorage.getItem('token');
    }
    return null; 
  }
}
