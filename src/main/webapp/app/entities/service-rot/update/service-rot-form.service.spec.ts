import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../service-rot.test-samples';

import { ServiceRotFormService } from './service-rot-form.service';

describe('ServiceRot Form Service', () => {
  let service: ServiceRotFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ServiceRotFormService);
  });

  describe('Service methods', () => {
    describe('createServiceRotFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createServiceRotFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            codgrp: expect.any(Object),
            delserv: expect.any(Object),
            ordserv: expect.any(Object),
          })
        );
      });

      it('passing IServiceRot should create a new form with FormGroup', () => {
        const formGroup = service.createServiceRotFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            codgrp: expect.any(Object),
            delserv: expect.any(Object),
            ordserv: expect.any(Object),
          })
        );
      });
    });

    describe('getServiceRot', () => {
      it('should return NewServiceRot for default ServiceRot initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createServiceRotFormGroup(sampleWithNewData);

        const serviceRot = service.getServiceRot(formGroup) as any;

        expect(serviceRot).toMatchObject(sampleWithNewData);
      });

      it('should return NewServiceRot for empty ServiceRot initial value', () => {
        const formGroup = service.createServiceRotFormGroup();

        const serviceRot = service.getServiceRot(formGroup) as any;

        expect(serviceRot).toMatchObject({});
      });

      it('should return IServiceRot', () => {
        const formGroup = service.createServiceRotFormGroup(sampleWithRequiredData);

        const serviceRot = service.getServiceRot(formGroup) as any;

        expect(serviceRot).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IServiceRot should not enable id FormControl', () => {
        const formGroup = service.createServiceRotFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewServiceRot should disable id FormControl', () => {
        const formGroup = service.createServiceRotFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
