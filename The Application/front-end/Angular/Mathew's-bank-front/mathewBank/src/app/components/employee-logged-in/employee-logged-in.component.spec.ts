import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeLoggedInComponent } from './employee-logged-in.component';

describe('EmployeeLoggedInComponent', () => {
  let component: EmployeeLoggedInComponent;
  let fixture: ComponentFixture<EmployeeLoggedInComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeLoggedInComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeLoggedInComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
