import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { CentVehicFormService } from './cent-vehic-form.service';
import { CentVehicService } from '../service/cent-vehic.service';
import { ICentVehic } from '../cent-vehic.model';

import { CentVehicUpdateComponent } from './cent-vehic-update.component';

describe('CentVehic Management Update Component', () => {
  let comp: CentVehicUpdateComponent;
  let fixture: ComponentFixture<CentVehicUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let centVehicFormService: CentVehicFormService;
  let centVehicService: CentVehicService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [CentVehicUpdateComponent],
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
      .overrideTemplate(CentVehicUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(CentVehicUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    centVehicFormService = TestBed.inject(CentVehicFormService);
    centVehicService = TestBed.inject(CentVehicService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const centVehic: ICentVehic = { id: 'CBA' };

      activatedRoute.data = of({ centVehic });
      comp.ngOnInit();

      expect(comp.centVehic).toEqual(centVehic);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICentVehic>>();
      const centVehic = { id: 'ABC' };
      jest.spyOn(centVehicFormService, 'getCentVehic').mockReturnValue(centVehic);
      jest.spyOn(centVehicService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ centVehic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: centVehic }));
      saveSubject.complete();

      // THEN
      expect(centVehicFormService.getCentVehic).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(centVehicService.update).toHaveBeenCalledWith(expect.objectContaining(centVehic));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICentVehic>>();
      const centVehic = { id: 'ABC' };
      jest.spyOn(centVehicFormService, 'getCentVehic').mockReturnValue({ id: null });
      jest.spyOn(centVehicService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ centVehic: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: centVehic }));
      saveSubject.complete();

      // THEN
      expect(centVehicFormService.getCentVehic).toHaveBeenCalled();
      expect(centVehicService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ICentVehic>>();
      const centVehic = { id: 'ABC' };
      jest.spyOn(centVehicService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ centVehic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(centVehicService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
