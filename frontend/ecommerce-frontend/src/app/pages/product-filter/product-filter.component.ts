import { CommonModule } from '@angular/common';
import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ProductsService } from '../../services/products.service';
import { categoriesService } from '../../services/categories.service';


@Component({
  selector: 'app-product-filter',
  imports: [FormsModule, CommonModule],
  templateUrl: './product-filter.component.html',
  styleUrls: ['./product-filter.component.scss'],
  standalone: true
})
export class ProductFilterComponent implements OnInit {
  @Output() categoryChanged = new EventEmitter<string>();
  @Output() priceRangeChanged = new EventEmitter<{ min: number; max: number }>();
  @Output() searchQueryChanged = new EventEmitter<string>();

  categories: string[] = [];
  selectedCategory: string = 'Alle';
  searchQuery: string = '';
  minPrice: number = 0;
  maxPrice: number = 10000;

  constructor(private productService: ProductsService, private categoryService:categoriesService) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoryService.getCategories().subscribe(categories => {
      this.categories = ['Alle', ...categories]; 
    });
  }

  onCategoryChange(): void {
    this.categoryChanged.emit(this.selectedCategory);
  }

  onPriceChange(): void {
    this.priceRangeChanged.emit({ min: this.minPrice, max: this.maxPrice });
  }

  onSearchChange(): void {
    this.searchQueryChanged.emit(this.searchQuery);
  }
}
