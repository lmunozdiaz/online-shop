import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CategoryRosterComponent } from './category-roster.component';

describe('CategoryRosterComponent', () => {
  let component: CategoryRosterComponent;
  let fixture: ComponentFixture<CategoryRosterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CategoryRosterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CategoryRosterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
