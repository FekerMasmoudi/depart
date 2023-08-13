import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../drtypab.test-samples';

import { DrtypabFormService } from './drtypab-form.service';

describe('Drtypab Form Service', () => {
  let service: DrtypabFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrtypabFormService);
  });

  describe('Service methods', () => {
    describe('createDrtypabFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDrtypabFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdtypab: expect.any(Object),
            lbtypab: expect.any(Object),
            dabsjt: expect.any(Object),
            dabsjp: expect.any(Object),
          })
        );
      });

      it('passing IDrtypab should create a new form with FormGroup', () => {
        const formGroup = service.createDrtypabFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdtypab: expect.any(Object),
            lbtypab: expect.any(Object),
            dabsjt: expect.any(Object),
            dabsjp: expect.any(Object),
          })
        );
      });
    });

    describe('getDrtypab', () => {
      it('should return NewDrtypab for default Drtypab initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDrtypabFormGroup(sampleWithNewData);

        const drtypab = service.getDrtypab(formGroup) as any;

        expect(drtypab).toMatchObject(sampleWithNewData);
      });

      it('should return NewDrtypab for empty Drtypab initial value', () => {
        const formGroup = service.createDrtypabFormGroup();

        const drtypab = service.getDrtypab(formGroup) as any;

        expect(drtypab).toMatchObject({});
      });

      it('should return IDrtypab', () => {
        const formGroup = service.createDrtypabFormGroup(sampleWithRequiredData);

        const drtypab = service.getDrtypab(formGroup) as any;

        expect(drtypab).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDrtypab should not enable id FormControl', () => {
        const formGroup = service.createDrtypabFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDrtypab should disable id FormControl', () => {
        const formGroup = service.createDrtypabFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
