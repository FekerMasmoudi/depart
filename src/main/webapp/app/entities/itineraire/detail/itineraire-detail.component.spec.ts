import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ItineraireDetailComponent } from './itineraire-detail.component';

describe('Itineraire Management Detail Component', () => {
  let comp: ItineraireDetailComponent;
  let fixture: ComponentFixture<ItineraireDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ItineraireDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ itineraire: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ItineraireDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ItineraireDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load itineraire on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.itineraire).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
