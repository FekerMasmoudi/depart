import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../typ-stat.test-samples';

import { TypStatFormService } from './typ-stat-form.service';

describe('TypStat Form Service', () => {
  let service: TypStatFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TypStatFormService);
  });

  describe('Service methods', () => {
    describe('createTypStatFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createTypStatFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dectyst: expect.any(Object),
            deltyst: expect.any(Object),
          })
        );
      });

      it('passing ITypStat should create a new form with FormGroup', () => {
        const formGroup = service.createTypStatFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dectyst: expect.any(Object),
            deltyst: expect.any(Object),
          })
        );
      });
    });

    describe('getTypStat', () => {
      it('should return NewTypStat for default TypStat initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createTypStatFormGroup(sampleWithNewData);

        const typStat = service.getTypStat(formGroup) as any;

        expect(typStat).toMatchObject(sampleWithNewData);
      });

      it('should return NewTypStat for empty TypStat initial value', () => {
        const formGroup = service.createTypStatFormGroup();

        const typStat = service.getTypStat(formGroup) as any;

        expect(typStat).toMatchObject({});
      });

      it('should return ITypStat', () => {
        const formGroup = service.createTypStatFormGroup(sampleWithRequiredData);

        const typStat = service.getTypStat(formGroup) as any;

        expect(typStat).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ITypStat should not enable id FormControl', () => {
        const formGroup = service.createTypStatFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewTypStat should disable id FormControl', () => {
        const formGroup = service.createTypStatFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
