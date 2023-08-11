import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../drabsen.test-samples';

import { DrabsenFormService } from './drabsen-form.service';

describe('Drabsen Form Service', () => {
  let service: DrabsenFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DrabsenFormService);
  });

  describe('Service methods', () => {
    describe('createDrabsenFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDrabsenFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdtypab: expect.any(Object),
            matric: expect.any(Object),
            databs: expect.any(Object),
            numabs: expect.any(Object),
            nbrabs: expect.any(Object),
            validabs: expect.any(Object),
            observaabs: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
          })
        );
      });

      it('passing IDrabsen should create a new form with FormGroup', () => {
        const formGroup = service.createDrabsenFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdtypab: expect.any(Object),
            matric: expect.any(Object),
            databs: expect.any(Object),
            numabs: expect.any(Object),
            nbrabs: expect.any(Object),
            validabs: expect.any(Object),
            observaabs: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
          })
        );
      });
    });

    describe('getDrabsen', () => {
      it('should return NewDrabsen for default Drabsen initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDrabsenFormGroup(sampleWithNewData);

        const drabsen = service.getDrabsen(formGroup) as any;

        expect(drabsen).toMatchObject(sampleWithNewData);
      });

      it('should return NewDrabsen for empty Drabsen initial value', () => {
        const formGroup = service.createDrabsenFormGroup();

        const drabsen = service.getDrabsen(formGroup) as any;

        expect(drabsen).toMatchObject({});
      });

      it('should return IDrabsen', () => {
        const formGroup = service.createDrabsenFormGroup(sampleWithRequiredData);

        const drabsen = service.getDrabsen(formGroup) as any;

        expect(drabsen).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDrabsen should not enable id FormControl', () => {
        const formGroup = service.createDrabsenFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDrabsen should disable id FormControl', () => {
        const formGroup = service.createDrabsenFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
