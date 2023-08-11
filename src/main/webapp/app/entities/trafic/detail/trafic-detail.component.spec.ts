import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TraficDetailComponent } from './trafic-detail.component';

describe('Trafic Management Detail Component', () => {
  let comp: TraficDetailComponent;
  let fixture: ComponentFixture<TraficDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TraficDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ trafic: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(TraficDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TraficDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load trafic on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.trafic).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
