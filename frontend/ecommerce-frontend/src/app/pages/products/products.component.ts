import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ProductsService, Product } from '../../services/products.service';
import { CommonModule } from '@angular/common';
import { NavbarComponent } from "../../navbar/navbar.component";
import { ProductFilterComponent } from "../product-filter/product-filter.component";

@Component({
  imports: [CommonModule, NavbarComponent, ProductFilterComponent],
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent {

  products: any[] = [];
  filteredProducts: Product[] = [];
  categories: string[] = [];
  selectedCategory: string = 'Alle';
  searchQuery: string = '';
  minPrice: number = 0;
  maxPrice: number = 10000;


  constructor(private productService: ProductsService, private router: Router) {}

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getAllProducts().subscribe(products => {
      this.products = products;
      this.filteredProducts = products;
    });
  }

  viewProduct(productId: number): void {
    this.router.navigate(['/products', productId]);
  }

  filterByCategory(category: string): void {
    this.selectedCategory = category;

    if (category === 'Alle') {
      this.loadProducts();
    } else {
      this.productService.getProductsByCategory(category).subscribe(products => {
        this.filteredProducts = products;
      });
    }
  }

  filterByPrice(priceRange: { min: number; max: number }): void {
    this.minPrice = priceRange.min;
    this.maxPrice = priceRange.max;

    this.productService.filterProducts(this.minPrice, this.maxPrice).subscribe(products => {
      this.filteredProducts = products;
    });
  }

  filterBySearch(query: string): void {
    this.searchQuery = query;

    if (query.trim() === '') {
      this.loadProducts();
    } else {
      this.productService.searchProducts(query).subscribe(products => {
        this.filteredProducts = products;
      });
    }
  }

  applyFilters(): void {
    this.filteredProducts = this.products
      .filter(p => this.selectedCategory === 'Alle' || p.category === this.selectedCategory)
      .filter(p => p.price >= this.minPrice && p.price <= this.maxPrice)
      .filter(p => this.searchQuery === '' || p.name.toLowerCase().includes(this.searchQuery.toLowerCase()));
  }
  

}
