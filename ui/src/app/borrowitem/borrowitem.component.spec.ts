import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BorrowitemComponent } from './borrowitem.component';

describe('BorrowitemComponent', () => {
  let component: BorrowitemComponent;
  let fixture: ComponentFixture<BorrowitemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BorrowitemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BorrowitemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
