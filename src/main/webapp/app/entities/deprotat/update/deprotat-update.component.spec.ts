import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DeprotatFormService } from './deprotat-form.service';
import { DeprotatService } from '../service/deprotat.service';
import { IDeprotat } from '../deprotat.model';
import { IDepart } from 'app/entities/depart/depart.model';
import { DepartService } from 'app/entities/depart/service/depart.service';

import { DeprotatUpdateComponent } from './deprotat-update.component';

describe('Deprotat Management Update Component', () => {
  let comp: DeprotatUpdateComponent;
  let fixture: ComponentFixture<DeprotatUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let deprotatFormService: DeprotatFormService;
  let deprotatService: DeprotatService;
  let departService: DepartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DeprotatUpdateComponent],
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
      .overrideTemplate(DeprotatUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DeprotatUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    deprotatFormService = TestBed.inject(DeprotatFormService);
    deprotatService = TestBed.inject(DeprotatService);
    departService = TestBed.inject(DepartService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Depart query and add missing value', () => {
      const deprotat: IDeprotat = { id: 'CBA' };
      const depart: IDepart = { id: 'ac527294-0fa7-4f9a-89aa-4e2e09e96821' };
      deprotat.depart = depart;

      const departCollection: IDepart[] = [{ id: '801e942f-be08-4023-9f56-02cdb8b94d19' }];
      jest.spyOn(departService, 'query').mockReturnValue(of(new HttpResponse({ body: departCollection })));
      const additionalDeparts = [depart];
      const expectedCollection: IDepart[] = [...additionalDeparts, ...departCollection];
      jest.spyOn(departService, 'addDepartToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ deprotat });
      comp.ngOnInit();

      expect(departService.query).toHaveBeenCalled();
      expect(departService.addDepartToCollectionIfMissing).toHaveBeenCalledWith(
        departCollection,
        ...additionalDeparts.map(expect.objectContaining)
      );
      expect(comp.departsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const deprotat: IDeprotat = { id: 'CBA' };
      const depart: IDepart = { id: '70006c0e-bbea-4284-9c8b-c9c21d05a190' };
      deprotat.depart = depart;

      activatedRoute.data = of({ deprotat });
      comp.ngOnInit();

      expect(comp.departsSharedCollection).toContain(depart);
      expect(comp.deprotat).toEqual(deprotat);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeprotat>>();
      const deprotat = { id: 'ABC' };
      jest.spyOn(deprotatFormService, 'getDeprotat').mockReturnValue(deprotat);
      jest.spyOn(deprotatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deprotat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: deprotat }));
      saveSubject.complete();

      // THEN
      expect(deprotatFormService.getDeprotat).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(deprotatService.update).toHaveBeenCalledWith(expect.objectContaining(deprotat));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeprotat>>();
      const deprotat = { id: 'ABC' };
      jest.spyOn(deprotatFormService, 'getDeprotat').mockReturnValue({ id: null });
      jest.spyOn(deprotatService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deprotat: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: deprotat }));
      saveSubject.complete();

      // THEN
      expect(deprotatFormService.getDeprotat).toHaveBeenCalled();
      expect(deprotatService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDeprotat>>();
      const deprotat = { id: 'ABC' };
      jest.spyOn(deprotatService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ deprotat });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(deprotatService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('compareDepart', () => {
      it('Should forward to departService', () => {
        const entity = { id: 'ABC' };
        const entity2 = { id: 'CBA' };
        jest.spyOn(departService, 'compareDepart');
        comp.compareDepart(entity, entity2);
        expect(departService.compareDepart).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
