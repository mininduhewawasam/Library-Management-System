import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DisplayitemComponent } from './displayitem.component';

describe('DisplayitemComponent', () => {
  let component: DisplayitemComponent;
  let fixture: ComponentFixture<DisplayitemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DisplayitemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DisplayitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
