import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {SearchPaymentListComponent} from './search-payment-list/search-payment-list.component'
import {InitiatePaymentComponent} from './initiate-payment/initiate-payment.component';
import {HomeComponentComponent} from './home-component/home-component.component'
import { PageNotfoundComponent } from './page-notfound/page-notfound.component';

const routes: Routes = [
  { path: 'initiatePayment', component: InitiatePaymentComponent },
  { path: 'searchPayment', component: SearchPaymentListComponent },
  { path: '', component: HomeComponentComponent },
  //{path: '**', redirectTo: 'home', pathMatch: 'full'}
  {path: '**', component: PageNotfoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
