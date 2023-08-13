import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DrabsenDetailComponent } from './drabsen-detail.component';

describe('Drabsen Management Detail Component', () => {
  let comp: DrabsenDetailComponent;
  let fixture: ComponentFixture<DrabsenDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DrabsenDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ drabsen: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DrabsenDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DrabsenDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load drabsen on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.drabsen).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
