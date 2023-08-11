import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DeprotatDetailComponent } from './deprotat-detail.component';

describe('Deprotat Management Detail Component', () => {
  let comp: DeprotatDetailComponent;
  let fixture: ComponentFixture<DeprotatDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DeprotatDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ deprotat: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DeprotatDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DeprotatDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load deprotat on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.deprotat).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
