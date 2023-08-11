import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../motifchserv.test-samples';

import { MotifchservFormService } from './motifchserv-form.service';

describe('Motifchserv Form Service', () => {
  let service: MotifchservFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MotifchservFormService);
  });

  describe('Service methods', () => {
    describe('createMotifchservFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMotifchservFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decmotif: expect.any(Object),
            delmotif: expect.any(Object),
            x: expect.any(Object),
            vs: expect.any(Object),
          })
        );
      });

      it('passing IMotifchserv should create a new form with FormGroup', () => {
        const formGroup = service.createMotifchservFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            decmotif: expect.any(Object),
            delmotif: expect.any(Object),
            x: expect.any(Object),
            vs: expect.any(Object),
          })
        );
      });
    });

    describe('getMotifchserv', () => {
      it('should return NewMotifchserv for default Motifchserv initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMotifchservFormGroup(sampleWithNewData);

        const motifchserv = service.getMotifchserv(formGroup) as any;

        expect(motifchserv).toMatchObject(sampleWithNewData);
      });

      it('should return NewMotifchserv for empty Motifchserv initial value', () => {
        const formGroup = service.createMotifchservFormGroup();

        const motifchserv = service.getMotifchserv(formGroup) as any;

        expect(motifchserv).toMatchObject({});
      });

      it('should return IMotifchserv', () => {
        const formGroup = service.createMotifchservFormGroup(sampleWithRequiredData);

        const motifchserv = service.getMotifchserv(formGroup) as any;

        expect(motifchserv).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMotifchserv should not enable id FormControl', () => {
        const formGroup = service.createMotifchservFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMotifchserv should disable id FormControl', () => {
        const formGroup = service.createMotifchservFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
