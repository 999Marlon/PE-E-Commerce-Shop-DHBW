import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map, Observable } from 'rxjs';
import { Product } from './products.service';


export interface Category {
  id: number;
  name: string;
}

@Injectable({
  providedIn: 'root'
})
export class categoriesService {
  private apiUrl = 'http://localhost:8080/categories'; 

  constructor(private http: HttpClient) {}

  getCategories(): Observable<string[]> {
    return this.http.get<Category[]>(this.apiUrl).pipe(
      map(categories => categories.map(category => category.name))
    );
  }

  getProductsByCategory(category: string): Observable<Product[]> {
    return this.http.get<Product[]>(`${this.apiUrl}/category/${category}`);
  }
}
