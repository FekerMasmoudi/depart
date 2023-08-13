import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DrtypabDetailComponent } from './drtypab-detail.component';

describe('Drtypab Management Detail Component', () => {
  let comp: DrtypabDetailComponent;
  let fixture: ComponentFixture<DrtypabDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DrtypabDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ drtypab: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DrtypabDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DrtypabDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load drtypab on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.drtypab).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
