import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export interface User {
  username: string,
  email: string,
  password: string,
  address: {
    street: string,
    city: string,
    postalCode: string,
    country: string
    },
  role: Role
}

export enum Role {
  ADMIN = 'ADMIN',
  USER = 'USER'
}


@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: HttpClient, private authService: AuthService) {}


  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Authorization': `Bearer ${this.authService.getToken()}`,
      'Content-Type': 'application/json',
    });
  }

  register(username: string, email: string, password: string, address: any, role:Role): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json' 
    });
  
    return this.http.post(
      `${this.apiUrl}/register`,
      { username, email, password, address, role },
      { headers } 
    );
  }

  getUserById(id: string): Observable<any> {
    return this.http.get(`${this.apiUrl}/${id}`, { headers: this.getHeaders()});
  }


  updateUser(id: string, updatedUser: any): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, updatedUser, { headers: this.getHeaders(), withCredentials: true });
  }


  deleteUser(id: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`, { headers: this.getHeaders(), withCredentials: true });
  }
}
