import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { MotifchservFormService } from './motifchserv-form.service';
import { MotifchservService } from '../service/motifchserv.service';
import { IMotifchserv } from '../motifchserv.model';

import { MotifchservUpdateComponent } from './motifchserv-update.component';

describe('Motifchserv Management Update Component', () => {
  let comp: MotifchservUpdateComponent;
  let fixture: ComponentFixture<MotifchservUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let motifchservFormService: MotifchservFormService;
  let motifchservService: MotifchservService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [MotifchservUpdateComponent],
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
      .overrideTemplate(MotifchservUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(MotifchservUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    motifchservFormService = TestBed.inject(MotifchservFormService);
    motifchservService = TestBed.inject(MotifchservService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const motifchserv: IMotifchserv = { id: 'CBA' };

      activatedRoute.data = of({ motifchserv });
      comp.ngOnInit();

      expect(comp.motifchserv).toEqual(motifchserv);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifchserv>>();
      const motifchserv = { id: 'ABC' };
      jest.spyOn(motifchservFormService, 'getMotifchserv').mockReturnValue(motifchserv);
      jest.spyOn(motifchservService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifchserv });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: motifchserv }));
      saveSubject.complete();

      // THEN
      expect(motifchservFormService.getMotifchserv).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(motifchservService.update).toHaveBeenCalledWith(expect.objectContaining(motifchserv));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifchserv>>();
      const motifchserv = { id: 'ABC' };
      jest.spyOn(motifchservFormService, 'getMotifchserv').mockReturnValue({ id: null });
      jest.spyOn(motifchservService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifchserv: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: motifchserv }));
      saveSubject.complete();

      // THEN
      expect(motifchservFormService.getMotifchserv).toHaveBeenCalled();
      expect(motifchservService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IMotifchserv>>();
      const motifchserv = { id: 'ABC' };
      jest.spyOn(motifchservService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ motifchserv });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(motifchservService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
