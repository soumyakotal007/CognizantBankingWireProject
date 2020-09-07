import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPaymentListComponent } from './search-payment-list.component';

describe('SearchPaymentListComponent', () => {
  let component: SearchPaymentListComponent;
  let fixture: ComponentFixture<SearchPaymentListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchPaymentListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchPaymentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
