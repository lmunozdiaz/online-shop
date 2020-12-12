import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// My components/modules
import {ProductListComponent} from './components/product-list/product-list.component';
import {ProductDetailsComponent} from './components/product-details/product-details.component';
import {CartDetailsComponent} from './components/cart-details/cart-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {LoginComponent} from "./components/login/login.component";
import {LoggedInGuard} from "./guards/logged-in.guard";
import { ProductAddComponent } from './components/product-add/product-add.component';
import {AdminMenuComponent} from "./components/admin/admin-menu/admin-menu.component";
import {AdminGuard} from "./guards/admin-guard.service";
import {DashComponent} from "./components/admin/dash/dash.component";
import {ProductRosterComponent} from "./components/admin/product-roster/product-roster.component";
import {CategoryRosterComponent} from "./components/admin/category-roster/category-roster.component";


const routes: Routes = [
  {
    path: 'admin-category-roster',
    component: CategoryRosterComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'admin-product-roster',
    component: ProductRosterComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'admin-dashboard',
    component: DashComponent,
    canActivate: [AdminGuard],
  },
  {
    path: 'admin-menu',
    component: AdminMenuComponent,
    canActivate: [AdminGuard],
  },
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
  {path:  'productadd', component: ProductAddComponent},
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
