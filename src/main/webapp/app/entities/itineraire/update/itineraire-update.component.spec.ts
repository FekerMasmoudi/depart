import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ItineraireFormService } from './itineraire-form.service';
import { ItineraireService } from '../service/itineraire.service';
import { IItineraire } from '../itineraire.model';

import { ItineraireUpdateComponent } from './itineraire-update.component';

describe('Itineraire Management Update Component', () => {
  let comp: ItineraireUpdateComponent;
  let fixture: ComponentFixture<ItineraireUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let itineraireFormService: ItineraireFormService;
  let itineraireService: ItineraireService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ItineraireUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(ItineraireUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ItineraireUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    itineraireFormService = TestBed.inject(ItineraireFormService);
    itineraireService = TestBed.inject(ItineraireService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const itineraire: IItineraire = { id: 'CBA' };

      activatedRoute.data = of({ itineraire });
      comp.ngOnInit();

      expect(comp.itineraire).toEqual(itineraire);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IItineraire>>();
      const itineraire = { id: 'ABC' };
      jest.spyOn(itineraireFormService, 'getItineraire').mockReturnValue(itineraire);
      jest.spyOn(itineraireService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ itineraire });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: itineraire }));
      saveSubject.complete();

      // THEN
      expect(itineraireFormService.getItineraire).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(itineraireService.update).toHaveBeenCalledWith(expect.objectContaining(itineraire));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IItineraire>>();
      const itineraire = { id: 'ABC' };
      jest.spyOn(itineraireFormService, 'getItineraire').mockReturnValue({ id: null });
      jest.spyOn(itineraireService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ itineraire: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: itineraire }));
      saveSubject.complete();

      // THEN
      expect(itineraireFormService.getItineraire).toHaveBeenCalled();
      expect(itineraireService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IItineraire>>();
      const itineraire = { id: 'ABC' };
      jest.spyOn(itineraireService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ itineraire });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(itineraireService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
