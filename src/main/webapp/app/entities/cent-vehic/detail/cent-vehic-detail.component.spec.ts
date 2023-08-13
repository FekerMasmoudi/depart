import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { CentVehicDetailComponent } from './cent-vehic-detail.component';

describe('CentVehic Management Detail Component', () => {
  let comp: CentVehicDetailComponent;
  let fixture: ComponentFixture<CentVehicDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CentVehicDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ centVehic: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(CentVehicDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(CentVehicDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load centVehic on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.centVehic).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
