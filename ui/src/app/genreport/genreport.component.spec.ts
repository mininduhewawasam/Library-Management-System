import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GenreportComponent } from './genreport.component';

describe('GenreportComponent', () => {
  let component: GenreportComponent;
  let fixture: ComponentFixture<GenreportComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GenreportComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GenreportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
