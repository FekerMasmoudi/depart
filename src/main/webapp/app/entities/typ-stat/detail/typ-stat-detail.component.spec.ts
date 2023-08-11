import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TypStatDetailComponent } from './typ-stat-detail.component';

describe('TypStat Management Detail Component', () => {
  let comp: TypStatDetailComponent;
  let fixture: ComponentFixture<TypStatDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TypStatDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ typStat: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(TypStatDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(TypStatDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load typStat on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.typStat).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
