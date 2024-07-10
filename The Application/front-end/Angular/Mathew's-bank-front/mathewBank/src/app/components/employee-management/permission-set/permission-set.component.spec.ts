import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PermissionSetComponent } from './permission-set.component';

describe('PermissionSetComponent', () => {
  let component: PermissionSetComponent;
  let fixture: ComponentFixture<PermissionSetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PermissionSetComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PermissionSetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
