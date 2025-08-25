/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { StRegComponent } from './st-reg.component';

describe('StRegComponent', () => {
  let component: StRegComponent;
  let fixture: ComponentFixture<StRegComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StRegComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StRegComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
