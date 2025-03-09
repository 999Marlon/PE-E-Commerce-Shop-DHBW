import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../services/auth.service';
import { UserService } from '../../services/user.service';
import { NavbarComponent } from "../../navbar/navbar.component";

@Component({
  imports: [FormsModule, CommonModule, NavbarComponent],
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  credentials = {
    username: '',
    email: '',
    password: '',
  };

  errorMessage = '';

  passwordValidations = {
    length: false,
    uppercase: false,
    lowercase: false,
    number: false,
    specialChar: false
  };


  constructor(private userService: UserService, private router: Router) {}

  createAccount(): void {
    this.userService.register(this.credentials.username,this.credentials.email, this.credentials.password ).subscribe({
      next: () => {
        this.router.navigate(['/login']);
      },
      error: (error) => {
        this.errorMessage = error.error || 'Login fehlgeschlagen';
      },
    });
  }

  checkPasswordStrength() {
    const password = this.credentials.password;

    this.passwordValidations.length = password.length >= 8;
    this.passwordValidations.uppercase = /[A-Z]/.test(password);
    this.passwordValidations.lowercase = /[a-z]/.test(password);
    this.passwordValidations.number = /\d/.test(password);
    this.passwordValidations.specialChar = /[!@#$%^&*(),.?":{}|<>]/.test(password);
  }
}
