import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TraficFormService } from './trafic-form.service';
import { TraficService } from '../service/trafic.service';
import { ITrafic } from '../trafic.model';

import { TraficUpdateComponent } from './trafic-update.component';

describe('Trafic Management Update Component', () => {
  let comp: TraficUpdateComponent;
  let fixture: ComponentFixture<TraficUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let traficFormService: TraficFormService;
  let traficService: TraficService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TraficUpdateComponent],
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
      .overrideTemplate(TraficUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TraficUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    traficFormService = TestBed.inject(TraficFormService);
    traficService = TestBed.inject(TraficService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const trafic: ITrafic = { id: 'CBA' };

      activatedRoute.data = of({ trafic });
      comp.ngOnInit();

      expect(comp.trafic).toEqual(trafic);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrafic>>();
      const trafic = { id: 'ABC' };
      jest.spyOn(traficFormService, 'getTrafic').mockReturnValue(trafic);
      jest.spyOn(traficService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trafic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trafic }));
      saveSubject.complete();

      // THEN
      expect(traficFormService.getTrafic).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(traficService.update).toHaveBeenCalledWith(expect.objectContaining(trafic));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrafic>>();
      const trafic = { id: 'ABC' };
      jest.spyOn(traficFormService, 'getTrafic').mockReturnValue({ id: null });
      jest.spyOn(traficService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trafic: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: trafic }));
      saveSubject.complete();

      // THEN
      expect(traficFormService.getTrafic).toHaveBeenCalled();
      expect(traficService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITrafic>>();
      const trafic = { id: 'ABC' };
      jest.spyOn(traficService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ trafic });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(traficService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
