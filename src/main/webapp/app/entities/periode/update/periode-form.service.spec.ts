import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../periode.test-samples';

import { PeriodeFormService } from './periode-form.service';

describe('Periode Form Service', () => {
  let service: PeriodeFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PeriodeFormService);
  });

  describe('Service methods', () => {
    describe('createPeriodeFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPeriodeFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decoper: expect.any(Object),
            denoper: expect.any(Object),
            primaire: expect.any(Object),
            startdate: expect.any(Object),
            enddate: expect.any(Object),
          })
        );
      });

      it('passing IPeriode should create a new form with FormGroup', () => {
        const formGroup = service.createPeriodeFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decoper: expect.any(Object),
            denoper: expect.any(Object),
            primaire: expect.any(Object),
            startdate: expect.any(Object),
            enddate: expect.any(Object),
          })
        );
      });
    });

    describe('getPeriode', () => {
      it('should return NewPeriode for default Periode initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createPeriodeFormGroup(sampleWithNewData);

        const periode = service.getPeriode(formGroup) as any;

        expect(periode).toMatchObject(sampleWithNewData);
      });

      it('should return NewPeriode for empty Periode initial value', () => {
        const formGroup = service.createPeriodeFormGroup();

        const periode = service.getPeriode(formGroup) as any;

        expect(periode).toMatchObject({});
      });

      it('should return IPeriode', () => {
        const formGroup = service.createPeriodeFormGroup(sampleWithRequiredData);

        const periode = service.getPeriode(formGroup) as any;

        expect(periode).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPeriode should not enable id FormControl', () => {
        const formGroup = service.createPeriodeFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPeriode should disable id FormControl', () => {
        const formGroup = service.createPeriodeFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
