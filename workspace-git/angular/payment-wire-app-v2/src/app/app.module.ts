import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UnpaidPaymentListComponent } from './unpaid-payment-list/unpaid-payment-list.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { EditPaymentComponent } from './unpaid-payment-list/edit-payment/edit-payment.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { SearchPaymentListComponent } from './search-payment-list/search-payment-list.component';
import { InitiatePaymentComponent } from './initiate-payment/initiate-payment.component';
import { HomeComponentComponent } from './home-component/home-component.component';
import { PageNotfoundComponent } from './page-notfound/page-notfound.component';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatInputModule} from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';

@NgModule({
  declarations: [
    AppComponent,
    UnpaidPaymentListComponent,
    EditPaymentComponent,
    SearchPaymentListComponent,
    InitiatePaymentComponent,
    HomeComponentComponent,
    PageNotfoundComponent
  
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
