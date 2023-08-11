import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../trafic.test-samples';

import { TraficFormService } from './trafic-form.service';

describe('Trafic Form Service', () => {
  let service: TraficFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TraficFormService);
  });

  describe('Service methods', () => {
    describe('createTraficFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTraficFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            ancien: expect.any(Object),
            vldtrafic: expect.any(Object),
            clotrafic: expect.any(Object),
          })
        );
      });

      it('passing ITrafic should create a new form with FormGroup', () => {
        const formGroup = service.createTraficFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            ancien: expect.any(Object),
            vldtrafic: expect.any(Object),
            clotrafic: expect.any(Object),
          })
        );
      });
    });

    describe('getTrafic', () => {
      it('should return NewTrafic for default Trafic initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTraficFormGroup(sampleWithNewData);

        const trafic = service.getTrafic(formGroup) as any;

        expect(trafic).toMatchObject(sampleWithNewData);
      });

      it('should return NewTrafic for empty Trafic initial value', () => {
        const formGroup = service.createTraficFormGroup();

        const trafic = service.getTrafic(formGroup) as any;

        expect(trafic).toMatchObject({});
      });

      it('should return ITrafic', () => {
        const formGroup = service.createTraficFormGroup(sampleWithRequiredData);

        const trafic = service.getTrafic(formGroup) as any;

        expect(trafic).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITrafic should not enable id FormControl', () => {
        const formGroup = service.createTraficFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTrafic should disable id FormControl', () => {
        const formGroup = service.createTraficFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
