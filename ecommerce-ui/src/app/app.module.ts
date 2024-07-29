import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { HttpClientModule } from '@angular/common/http';
import { Routes, RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { DetailProductComponent } from './components/cart/detail-product/detail-product.component';
import { ToastrModule } from 'ngx-toastr';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SumaryOrderComponent } from './components/orders/sumary-order/sumary-order.component';
import { CategoryListComponent } from './components/category/category-list/category-list.component';
import { CategoryAddComponent } from './components/category/category-add/category-add.component';
import { ProductListComponent } from './components/product/product-list/product-list.component';
import { HeaderAdminComponent } from './components/headers/header-admin/header-admin.component';
import { ProductAddComponent } from './components/product/product-add/product-add.component';
import { HeaderUserComponent } from './components/headers/header-user/header-user.component';
import { RegistrationComponent } from './components/authentication/registration/registration.component';
import { LoginComponent } from './components/authentication/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { authGuard } from './guards/auth.guard';
import { authAdminGuard } from './guards/auth-admin.guard';
import { UserListComponent } from './components/users/user-list/user-list.component';
import { UserAddComponent } from './components/users/user-add/user-add.component';
import { OrderListComponent } from './components/inbox/order-list/order-list.component';
import { UserViewComponent } from './components/inbox/user-view/user-view.component';
import { PaymentSuccessComponent } from './components/orders/payment-success/payment-success.component';
import { AdminOrderListComponent } from './components/orders/admin-order-list/admin-order-list.component';

const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'admin/products', component: ProductListComponent, canActivate: [authAdminGuard]},
  {path: 'admin/products/new', component: ProductAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/products/update/:id', component: ProductAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/categories', component: CategoryListComponent, canActivate: [authAdminGuard]},
  {path: 'admin/categories/new', component: CategoryAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/categories/update/:id', component: CategoryAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/users', component: UserListComponent, canActivate: [authAdminGuard]},
  {path: 'admin/users/register', component: UserAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/users/update/:id', component: UserAddComponent, canActivate: [authAdminGuard]},
  {path: 'admin/orders', component: AdminOrderListComponent, canActivate: [authAdminGuard]},
  {path: 'user/orders', component: OrderListComponent, canActivate: [authGuard]},//Done
  {path: 'user/data', component: UserViewComponent, canActivate: [authGuard]},//Done
  {path: 'product/detail/:id', component: DetailProductComponent},//Done
  {path: 'cart/sumary', component: SumaryOrderComponent, canActivate: [authGuard]},//Done
  {path: 'user/register', component: RegistrationComponent},//Done
  {path: 'user/login', component: LoginComponent},//Done
  {path: 'logout', component: LogoutComponent},//Done

  {path: 'payment/success', component: PaymentSuccessComponent},
]

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductListComponent,
    HeaderAdminComponent,
    ProductAddComponent,
    DetailProductComponent,
    HeaderUserComponent,
    SumaryOrderComponent,
    CategoryListComponent,
    CategoryAddComponent,
    RegistrationComponent,
    LoginComponent,
    LogoutComponent,
    UserListComponent,
    UserAddComponent,
    OrderListComponent,
    UserViewComponent,
    PaymentSuccessComponent,
    AdminOrderListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    RouterModule.forRoot(routes),
    ToastrModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
