import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DepartFormService } from './depart-form.service';
import { DepartService } from '../service/depart.service';
import { IDepart } from '../depart.model';

import { DepartUpdateComponent } from './depart-update.component';

describe('Depart Management Update Component', () => {
  let comp: DepartUpdateComponent;
  let fixture: ComponentFixture<DepartUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let departFormService: DepartFormService;
  let departService: DepartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DepartUpdateComponent],
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
      .overrideTemplate(DepartUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DepartUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    departFormService = TestBed.inject(DepartFormService);
    departService = TestBed.inject(DepartService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const depart: IDepart = { id: 'CBA' };

      activatedRoute.data = of({ depart });
      comp.ngOnInit();

      expect(comp.depart).toEqual(depart);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepart>>();
      const depart = { id: 'ABC' };
      jest.spyOn(departFormService, 'getDepart').mockReturnValue(depart);
      jest.spyOn(departService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ depart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: depart }));
      saveSubject.complete();

      // THEN
      expect(departFormService.getDepart).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(departService.update).toHaveBeenCalledWith(expect.objectContaining(depart));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepart>>();
      const depart = { id: 'ABC' };
      jest.spyOn(departFormService, 'getDepart').mockReturnValue({ id: null });
      jest.spyOn(departService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ depart: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: depart }));
      saveSubject.complete();

      // THEN
      expect(departFormService.getDepart).toHaveBeenCalled();
      expect(departService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDepart>>();
      const depart = { id: 'ABC' };
      jest.spyOn(departService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ depart });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(departService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
