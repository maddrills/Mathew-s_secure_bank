import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BankApplicationsComponent } from './bank-applications.component';

describe('BankApplicationsComponent', () => {
  let component: BankApplicationsComponent;
  let fixture: ComponentFixture<BankApplicationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BankApplicationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BankApplicationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
