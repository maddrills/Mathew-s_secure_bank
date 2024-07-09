import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeManagementShellComponent } from './employee-management-shell.component';

describe('EmployeeManagementShellComponent', () => {
  let component: EmployeeManagementShellComponent;
  let fixture: ComponentFixture<EmployeeManagementShellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmployeeManagementShellComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeManagementShellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
