import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AffectagentFormService } from './affectagent-form.service';
import { AffectagentService } from '../service/affectagent.service';
import { IAffectagent } from '../affectagent.model';

import { AffectagentUpdateComponent } from './affectagent-update.component';

describe('Affectagent Management Update Component', () => {
  let comp: AffectagentUpdateComponent;
  let fixture: ComponentFixture<AffectagentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let affectagentFormService: AffectagentFormService;
  let affectagentService: AffectagentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AffectagentUpdateComponent],
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
      .overrideTemplate(AffectagentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AffectagentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    affectagentFormService = TestBed.inject(AffectagentFormService);
    affectagentService = TestBed.inject(AffectagentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const affectagent: IAffectagent = { id: 'CBA' };

      activatedRoute.data = of({ affectagent });
      comp.ngOnInit();

      expect(comp.affectagent).toEqual(affectagent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffectagent>>();
      const affectagent = { id: 'ABC' };
      jest.spyOn(affectagentFormService, 'getAffectagent').mockReturnValue(affectagent);
      jest.spyOn(affectagentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affectagent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: affectagent }));
      saveSubject.complete();

      // THEN
      expect(affectagentFormService.getAffectagent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(affectagentService.update).toHaveBeenCalledWith(expect.objectContaining(affectagent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffectagent>>();
      const affectagent = { id: 'ABC' };
      jest.spyOn(affectagentFormService, 'getAffectagent').mockReturnValue({ id: null });
      jest.spyOn(affectagentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affectagent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: affectagent }));
      saveSubject.complete();

      // THEN
      expect(affectagentFormService.getAffectagent).toHaveBeenCalled();
      expect(affectagentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffectagent>>();
      const affectagent = { id: 'ABC' };
      jest.spyOn(affectagentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affectagent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(affectagentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
