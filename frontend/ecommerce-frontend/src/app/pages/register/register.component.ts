import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  imports: [FormsModule, CommonModule],
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
      error: () => {
        this.errorMessage = 'Registrierung fehlgeschlagen. Bitte überprüfe deine Eingaben.';
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
