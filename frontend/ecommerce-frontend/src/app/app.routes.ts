import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ProductsComponent } from './pages/products/products.component';
import { ProductDetailComponent } from './pages/product-detail/product-detail.component';
import { CartComponent } from './pages/cart/cart.component';
import { OrdersComponent } from './pages/orders/orders.component';
import { OrderConfirmationComponent } from './pages/order-confirmation/order-confirmation.component';

export const routes: Routes = [

    { path: '', redirectTo: '/products', pathMatch: 'full' },

    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },


    { path: 'products', component: ProductsComponent },
    { path: 'products/:id', component: ProductDetailComponent },

    { path: 'cart', component: CartComponent },

    { path: 'order-confirmation', component: OrderConfirmationComponent },
    { path: 'orders', component: OrdersComponent }, 



];
