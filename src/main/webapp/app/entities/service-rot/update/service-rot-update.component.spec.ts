import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ServiceRotFormService } from './service-rot-form.service';
import { ServiceRotService } from '../service/service-rot.service';
import { IServiceRot } from '../service-rot.model';

import { ServiceRotUpdateComponent } from './service-rot-update.component';

describe('ServiceRot Management Update Component', () => {
  let comp: ServiceRotUpdateComponent;
  let fixture: ComponentFixture<ServiceRotUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let serviceRotFormService: ServiceRotFormService;
  let serviceRotService: ServiceRotService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ServiceRotUpdateComponent],
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
      .overrideTemplate(ServiceRotUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ServiceRotUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    serviceRotFormService = TestBed.inject(ServiceRotFormService);
    serviceRotService = TestBed.inject(ServiceRotService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const serviceRot: IServiceRot = { id: 'CBA' };

      activatedRoute.data = of({ serviceRot });
      comp.ngOnInit();

      expect(comp.serviceRot).toEqual(serviceRot);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServiceRot>>();
      const serviceRot = { id: 'ABC' };
      jest.spyOn(serviceRotFormService, 'getServiceRot').mockReturnValue(serviceRot);
      jest.spyOn(serviceRotService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ serviceRot });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: serviceRot }));
      saveSubject.complete();

      // THEN
      expect(serviceRotFormService.getServiceRot).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(serviceRotService.update).toHaveBeenCalledWith(expect.objectContaining(serviceRot));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServiceRot>>();
      const serviceRot = { id: 'ABC' };
      jest.spyOn(serviceRotFormService, 'getServiceRot').mockReturnValue({ id: null });
      jest.spyOn(serviceRotService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ serviceRot: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: serviceRot }));
      saveSubject.complete();

      // THEN
      expect(serviceRotFormService.getServiceRot).toHaveBeenCalled();
      expect(serviceRotService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IServiceRot>>();
      const serviceRot = { id: 'ABC' };
      jest.spyOn(serviceRotService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ serviceRot });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(serviceRotService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
