import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ReturnitemComponent } from './returnitem.component';

describe('ReturnitemComponent', () => {
  let component: ReturnitemComponent;
  let fixture: ComponentFixture<ReturnitemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ReturnitemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ReturnitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
