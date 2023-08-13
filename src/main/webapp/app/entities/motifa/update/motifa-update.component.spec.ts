import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MotifaFormService } from './motifa-form.service';
import { MotifaService } from '../service/motifa.service';
import { IMotifa } from '../motifa.model';

import { MotifaUpdateComponent } from './motifa-update.component';

describe('Motifa Management Update Component', () => {
  let comp: MotifaUpdateComponent;
  let fixture: ComponentFixture<MotifaUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let motifaFormService: MotifaFormService;
  let motifaService: MotifaService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MotifaUpdateComponent],
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
      .overrideTemplate(MotifaUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MotifaUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    motifaFormService = TestBed.inject(MotifaFormService);
    motifaService = TestBed.inject(MotifaService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const motifa: IMotifa = { id: 'CBA' };

      activatedRoute.data = of({ motifa });
      comp.ngOnInit();

      expect(comp.motifa).toEqual(motifa);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifa>>();
      const motifa = { id: 'ABC' };
      jest.spyOn(motifaFormService, 'getMotifa').mockReturnValue(motifa);
      jest.spyOn(motifaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: motifa }));
      saveSubject.complete();

      // THEN
      expect(motifaFormService.getMotifa).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(motifaService.update).toHaveBeenCalledWith(expect.objectContaining(motifa));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifa>>();
      const motifa = { id: 'ABC' };
      jest.spyOn(motifaFormService, 'getMotifa').mockReturnValue({ id: null });
      jest.spyOn(motifaService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifa: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: motifa }));
      saveSubject.complete();

      // THEN
      expect(motifaFormService.getMotifa).toHaveBeenCalled();
      expect(motifaService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifa>>();
      const motifa = { id: 'ABC' };
      jest.spyOn(motifaService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifa });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(motifaService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
