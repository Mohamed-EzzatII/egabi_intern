import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacultyRegComponent } from './faculty-reg.component';

describe('FacultyRegComponent', () => {
  let component: FacultyRegComponent;
  let fixture: ComponentFixture<FacultyRegComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacultyRegComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacultyRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
