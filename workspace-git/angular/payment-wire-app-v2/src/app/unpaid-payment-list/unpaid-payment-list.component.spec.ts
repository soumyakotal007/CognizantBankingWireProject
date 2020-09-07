import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { UnpaidPaymentListComponent } from './unpaid-payment-list.component';

describe('UnpaidPaymentListComponent', () => {
  let component: UnpaidPaymentListComponent;
  let fixture: ComponentFixture<UnpaidPaymentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ UnpaidPaymentListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UnpaidPaymentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
