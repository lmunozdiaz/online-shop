import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// My components/modules
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductDetailsComponent} from './components/product-details/product-details.component';
import {CartDetailsComponent} from './components/cart-details/cart-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {LoginComponent} from "./components/login/login.component";
import {LoggedInGuard} from "./guards/logged-in.guard";


const routes: Routes = [
  {
    path: 'cart-details',
    component: CartDetailsComponent,
    canActivate: [LoggedInGuard]
  },
  {path: 'products/:id', component: ProductDetailsComponent},
  {path: 'search/:searchStr', component: ProductListComponent},
  {path: 'category/:categoryType', component: ProductListComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: SignupComponent},
  {path: 'category', component: ProductListComponent},
  {path: 'products', component: ProductListComponent},
  {path: '', redirectTo: '/products', pathMatch: 'full'},
  {path: '**', redirectTo: '/products', pathMatch: 'full'}
];

// @ts-ignore
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [LoggedInGuard],
  exports: [RouterModule]
})
export class AppRoutingModule { }
