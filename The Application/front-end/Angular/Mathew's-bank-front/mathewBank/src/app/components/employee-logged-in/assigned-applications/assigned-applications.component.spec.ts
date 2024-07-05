import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssignedApplicationsComponent } from './assigned-applications.component';

describe('AssignedApplicationsComponent', () => {
  let component: AssignedApplicationsComponent;
  let fixture: ComponentFixture<AssignedApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AssignedApplicationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AssignedApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
