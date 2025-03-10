import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NavbarComponent } from "../../navbar/navbar.component";
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-order-confirmation',
  imports: [CommonModule, NavbarComponent],
  templateUrl: './order-confirmation.component.html',
  styleUrls: ['./order-confirmation.component.scss'],
  standalone: true
})
export class OrderConfirmationComponent implements OnInit {
  orderId: string | null = null;

  address: any = [];

  constructor(private route: ActivatedRoute, private router: Router, private userService:UserService) {}

  ngOnInit(): void {
    this.orderId = this.route.snapshot.queryParamMap.get('orderId');

    this.loadUserInformation();

  }

  loadUserInformation(): void {
    const userId = localStorage.getItem('userId');
    
    if (!userId) {
      console.error('Fehler: Kein User im localStorage gefunden.');
      return;
    }

    this.userService.getUserById(userId)
      .subscribe(user => {
        if (user) {
          this.address = user.address;
        } else {
          console.warn('Benutzeradresse konnte nicht geladen werden.');
        }
      });
  }

  goToOrders(): void {
    this.router.navigate(['/orders']);
  }

  goToProducts(): void {
    this.router.navigate(['/products']);
  }
}
