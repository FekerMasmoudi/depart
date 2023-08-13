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

import { DeprotatUpdateComponent } from './deprotat-update.component';

describe('Deprotat Management Update Component', () => {
  let comp: DeprotatUpdateComponent;
  let fixture: ComponentFixture<DeprotatUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let deprotatFormService: DeprotatFormService;
  let deprotatService: DeprotatService;

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

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const deprotat: IDeprotat = { id: 'CBA' };

      activatedRoute.data = of({ deprotat });
      comp.ngOnInit();

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
});
