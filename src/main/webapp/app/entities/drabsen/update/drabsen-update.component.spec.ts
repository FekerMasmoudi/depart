import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DrabsenFormService } from './drabsen-form.service';
import { DrabsenService } from '../service/drabsen.service';
import { IDrabsen } from '../drabsen.model';

import { DrabsenUpdateComponent } from './drabsen-update.component';

describe('Drabsen Management Update Component', () => {
  let comp: DrabsenUpdateComponent;
  let fixture: ComponentFixture<DrabsenUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let drabsenFormService: DrabsenFormService;
  let drabsenService: DrabsenService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DrabsenUpdateComponent],
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
      .overrideTemplate(DrabsenUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DrabsenUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    drabsenFormService = TestBed.inject(DrabsenFormService);
    drabsenService = TestBed.inject(DrabsenService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const drabsen: IDrabsen = { id: 'CBA' };

      activatedRoute.data = of({ drabsen });
      comp.ngOnInit();

      expect(comp.drabsen).toEqual(drabsen);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrabsen>>();
      const drabsen = { id: 'ABC' };
      jest.spyOn(drabsenFormService, 'getDrabsen').mockReturnValue(drabsen);
      jest.spyOn(drabsenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drabsen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: drabsen }));
      saveSubject.complete();

      // THEN
      expect(drabsenFormService.getDrabsen).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(drabsenService.update).toHaveBeenCalledWith(expect.objectContaining(drabsen));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrabsen>>();
      const drabsen = { id: 'ABC' };
      jest.spyOn(drabsenFormService, 'getDrabsen').mockReturnValue({ id: null });
      jest.spyOn(drabsenService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drabsen: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: drabsen }));
      saveSubject.complete();

      // THEN
      expect(drabsenFormService.getDrabsen).toHaveBeenCalled();
      expect(drabsenService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrabsen>>();
      const drabsen = { id: 'ABC' };
      jest.spyOn(drabsenService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drabsen });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(drabsenService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
