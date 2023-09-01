import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListMotifaComponent } from './list-motifa.component';

describe('ListMotifaComponent', () => {
  let component: ListMotifaComponent;
  let fixture: ComponentFixture<ListMotifaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ListMotifaComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(ListMotifaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
