import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PeriodeFormService } from './periode-form.service';
import { PeriodeService } from '../service/periode.service';
import { IPeriode } from '../periode.model';

import { PeriodeUpdateComponent } from './periode-update.component';

describe('Periode Management Update Component', () => {
  let comp: PeriodeUpdateComponent;
  let fixture: ComponentFixture<PeriodeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let periodeFormService: PeriodeFormService;
  let periodeService: PeriodeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PeriodeUpdateComponent],
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
      .overrideTemplate(PeriodeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PeriodeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    periodeFormService = TestBed.inject(PeriodeFormService);
    periodeService = TestBed.inject(PeriodeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const periode: IPeriode = { id: 'CBA' };

      activatedRoute.data = of({ periode });
      comp.ngOnInit();

      expect(comp.periode).toEqual(periode);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPeriode>>();
      const periode = { id: 'ABC' };
      jest.spyOn(periodeFormService, 'getPeriode').mockReturnValue(periode);
      jest.spyOn(periodeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ periode });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: periode }));
      saveSubject.complete();

      // THEN
      expect(periodeFormService.getPeriode).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(periodeService.update).toHaveBeenCalledWith(expect.objectContaining(periode));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPeriode>>();
      const periode = { id: 'ABC' };
      jest.spyOn(periodeFormService, 'getPeriode').mockReturnValue({ id: null });
      jest.spyOn(periodeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ periode: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: periode }));
      saveSubject.complete();

      // THEN
      expect(periodeFormService.getPeriode).toHaveBeenCalled();
      expect(periodeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IPeriode>>();
      const periode = { id: 'ABC' };
      jest.spyOn(periodeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ periode });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(periodeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
