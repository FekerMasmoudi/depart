import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BordereauDetailComponent } from './bordereau-detail.component';

describe('Bordereau Management Detail Component', () => {
  let comp: BordereauDetailComponent;
  let fixture: ComponentFixture<BordereauDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BordereauDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ bordereau: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(BordereauDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BordereauDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load bordereau on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.bordereau).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
