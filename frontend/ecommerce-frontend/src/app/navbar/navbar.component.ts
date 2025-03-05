import { Component, OnInit  } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';


@Component({
  imports: [CommonModule],
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent implements OnInit {

  isCollapsed = false;
  isLoggedIn = false;

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  toggle() {
    this.isCollapsed = !this.isCollapsed;
  }

  login(): void {
    this.isLoggedIn = true; 
    this.router.navigate(['/login']); 
  }
  
  logout(): void {
    this.authService.logout();
    this.isLoggedIn = false; 
    this.router.navigate(['/products']); 
  }
}