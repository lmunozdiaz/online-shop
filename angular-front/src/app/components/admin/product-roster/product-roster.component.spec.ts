import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductRosterComponent } from './product-roster.component';

describe('ProductRosterComponent', () => {
  let component: ProductRosterComponent;
  let fixture: ComponentFixture<ProductRosterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductRosterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProductRosterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
