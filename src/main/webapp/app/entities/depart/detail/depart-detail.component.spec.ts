import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DepartDetailComponent } from './depart-detail.component';

describe('Depart Management Detail Component', () => {
  let comp: DepartDetailComponent;
  let fixture: ComponentFixture<DepartDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DepartDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ depart: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DepartDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DepartDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load depart on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.depart).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
