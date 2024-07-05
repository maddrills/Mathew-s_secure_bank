import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubEmployeesComponent } from './sub-employees.component';

describe('SubEmployeesComponent', () => {
  let component: SubEmployeesComponent;
  let fixture: ComponentFixture<SubEmployeesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SubEmployeesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubEmployeesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
