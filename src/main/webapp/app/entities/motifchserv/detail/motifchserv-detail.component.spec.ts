import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotifchservDetailComponent } from './motifchserv-detail.component';

describe('Motifchserv Management Detail Component', () => {
  let comp: MotifchservDetailComponent;
  let fixture: ComponentFixture<MotifchservDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MotifchservDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ motifchserv: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(MotifchservDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MotifchservDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load motifchserv on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.motifchserv).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
