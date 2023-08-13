import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BonTvxDetailComponent } from './bon-tvx-detail.component';

describe('BonTvx Management Detail Component', () => {
  let comp: BonTvxDetailComponent;
  let fixture: ComponentFixture<BonTvxDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BonTvxDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ bonTvx: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(BonTvxDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BonTvxDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load bonTvx on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.bonTvx).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
