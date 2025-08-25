import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrollRegComponent } from './enroll-reg.component';

describe('EnrollRegComponent', () => {
  let component: EnrollRegComponent;
  let fixture: ComponentFixture<EnrollRegComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrollRegComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EnrollRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
