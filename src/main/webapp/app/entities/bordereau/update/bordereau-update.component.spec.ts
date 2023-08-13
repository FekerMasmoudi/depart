import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BordereauFormService } from './bordereau-form.service';
import { BordereauService } from '../service/bordereau.service';
import { IBordereau } from '../bordereau.model';

import { BordereauUpdateComponent } from './bordereau-update.component';

describe('Bordereau Management Update Component', () => {
  let comp: BordereauUpdateComponent;
  let fixture: ComponentFixture<BordereauUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let bordereauFormService: BordereauFormService;
  let bordereauService: BordereauService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BordereauUpdateComponent],
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
      .overrideTemplate(BordereauUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BordereauUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    bordereauFormService = TestBed.inject(BordereauFormService);
    bordereauService = TestBed.inject(BordereauService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const bordereau: IBordereau = { id: 'CBA' };

      activatedRoute.data = of({ bordereau });
      comp.ngOnInit();

      expect(comp.bordereau).toEqual(bordereau);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBordereau>>();
      const bordereau = { id: 'ABC' };
      jest.spyOn(bordereauFormService, 'getBordereau').mockReturnValue(bordereau);
      jest.spyOn(bordereauService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bordereau });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bordereau }));
      saveSubject.complete();

      // THEN
      expect(bordereauFormService.getBordereau).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(bordereauService.update).toHaveBeenCalledWith(expect.objectContaining(bordereau));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBordereau>>();
      const bordereau = { id: 'ABC' };
      jest.spyOn(bordereauFormService, 'getBordereau').mockReturnValue({ id: null });
      jest.spyOn(bordereauService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bordereau: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bordereau }));
      saveSubject.complete();

      // THEN
      expect(bordereauFormService.getBordereau).toHaveBeenCalled();
      expect(bordereauService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBordereau>>();
      const bordereau = { id: 'ABC' };
      jest.spyOn(bordereauService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bordereau });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(bordereauService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
