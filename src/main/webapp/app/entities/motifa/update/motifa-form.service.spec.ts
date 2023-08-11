import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../motifa.test-samples';

import { MotifaFormService } from './motifa-form.service';

describe('Motifa Form Service', () => {
  let service: MotifaFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MotifaFormService);
  });

  describe('Service methods', () => {
    describe('createMotifaFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMotifaFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decmotif: expect.any(Object),
            libmotif: expect.any(Object),
          })
        );
      });

      it('passing IMotifa should create a new form with FormGroup', () => {
        const formGroup = service.createMotifaFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decmotif: expect.any(Object),
            libmotif: expect.any(Object),
          })
        );
      });
    });

    describe('getMotifa', () => {
      it('should return NewMotifa for default Motifa initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMotifaFormGroup(sampleWithNewData);

        const motifa = service.getMotifa(formGroup) as any;

        expect(motifa).toMatchObject(sampleWithNewData);
      });

      it('should return NewMotifa for empty Motifa initial value', () => {
        const formGroup = service.createMotifaFormGroup();

        const motifa = service.getMotifa(formGroup) as any;

        expect(motifa).toMatchObject({});
      });

      it('should return IMotifa', () => {
        const formGroup = service.createMotifaFormGroup(sampleWithRequiredData);

        const motifa = service.getMotifa(formGroup) as any;

        expect(motifa).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMotifa should not enable id FormControl', () => {
        const formGroup = service.createMotifaFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMotifa should disable id FormControl', () => {
        const formGroup = service.createMotifaFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
