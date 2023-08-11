import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../cent-vehic.test-samples';

import { CentVehicFormService } from './cent-vehic-form.service';

describe('CentVehic Form Service', () => {
  let service: CentVehicFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentVehicFormService);
  });

  describe('Service methods', () => {
    describe('createCentVehicFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createCentVehicFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdmac: expect.any(Object),
            dateff: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
          })
        );
      });

      it('passing ICentVehic should create a new form with FormGroup', () => {
        const formGroup = service.createCentVehicFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdmac: expect.any(Object),
            dateff: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
          })
        );
      });
    });

    describe('getCentVehic', () => {
      it('should return NewCentVehic for default CentVehic initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createCentVehicFormGroup(sampleWithNewData);

        const centVehic = service.getCentVehic(formGroup) as any;

        expect(centVehic).toMatchObject(sampleWithNewData);
      });

      it('should return NewCentVehic for empty CentVehic initial value', () => {
        const formGroup = service.createCentVehicFormGroup();

        const centVehic = service.getCentVehic(formGroup) as any;

        expect(centVehic).toMatchObject({});
      });

      it('should return ICentVehic', () => {
        const formGroup = service.createCentVehicFormGroup(sampleWithRequiredData);

        const centVehic = service.getCentVehic(formGroup) as any;

        expect(centVehic).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ICentVehic should not enable id FormControl', () => {
        const formGroup = service.createCentVehicFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewCentVehic should disable id FormControl', () => {
        const formGroup = service.createCentVehicFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
