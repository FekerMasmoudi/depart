import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MotifaDetailComponent } from './motifa-detail.component';

describe('Motifa Management Detail Component', () => {
  let comp: MotifaDetailComponent;
  let fixture: ComponentFixture<MotifaDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [MotifaDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ motifa: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(MotifaDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(MotifaDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load motifa on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.motifa).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
