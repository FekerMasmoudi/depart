import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { TypStatFormService } from './typ-stat-form.service';
import { TypStatService } from '../service/typ-stat.service';
import { ITypStat } from '../typ-stat.model';

import { TypStatUpdateComponent } from './typ-stat-update.component';

describe('TypStat Management Update Component', () => {
  let comp: TypStatUpdateComponent;
  let fixture: ComponentFixture<TypStatUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let typStatFormService: TypStatFormService;
  let typStatService: TypStatService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [TypStatUpdateComponent],
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
      .overrideTemplate(TypStatUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(TypStatUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    typStatFormService = TestBed.inject(TypStatFormService);
    typStatService = TestBed.inject(TypStatService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const typStat: ITypStat = { id: 'CBA' };

      activatedRoute.data = of({ typStat });
      comp.ngOnInit();

      expect(comp.typStat).toEqual(typStat);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITypStat>>();
      const typStat = { id: 'ABC' };
      jest.spyOn(typStatFormService, 'getTypStat').mockReturnValue(typStat);
      jest.spyOn(typStatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ typStat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: typStat }));
      saveSubject.complete();

      // THEN
      expect(typStatFormService.getTypStat).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(typStatService.update).toHaveBeenCalledWith(expect.objectContaining(typStat));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITypStat>>();
      const typStat = { id: 'ABC' };
      jest.spyOn(typStatFormService, 'getTypStat').mockReturnValue({ id: null });
      jest.spyOn(typStatService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ typStat: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: typStat }));
      saveSubject.complete();

      // THEN
      expect(typStatFormService.getTypStat).toHaveBeenCalled();
      expect(typStatService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ITypStat>>();
      const typStat = { id: 'ABC' };
      jest.spyOn(typStatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ typStat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(typStatService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
