import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApplyForAccountComponent } from './apply-for-account.component';

describe('ApplyForAccountComponent', () => {
  let component: ApplyForAccountComponent;
  let fixture: ComponentFixture<ApplyForAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ApplyForAccountComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ApplyForAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
