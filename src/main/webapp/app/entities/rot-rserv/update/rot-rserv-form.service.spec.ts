import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../rot-rserv.test-samples';

import { RotRservFormService } from './rot-rserv-form.service';

describe('RotRserv Form Service', () => {
  let service: RotRservFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RotRservFormService);
  });

  describe('Service methods', () => {
    describe('createRotRservFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRotRservFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            matric: expect.any(Object),
            heurdeb: expect.any(Object),
            heurfin: expect.any(Object),
            statut: expect.any(Object),
            lieedeb: expect.any(Object),
            lieefin: expect.any(Object),
            program: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            id: expect.any(Object),
            annul: expect.any(Object),
          })
        );
      });

      it('passing IRotRserv should create a new form with FormGroup', () => {
        const formGroup = service.createRotRservFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            matric: expect.any(Object),
            heurdeb: expect.any(Object),
            heurfin: expect.any(Object),
            statut: expect.any(Object),
            lieedeb: expect.any(Object),
            lieefin: expect.any(Object),
            program: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            id: expect.any(Object),
            annul: expect.any(Object),
          })
        );
      });
    });

    describe('getRotRserv', () => {
      it('should return NewRotRserv for default RotRserv initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRotRservFormGroup(sampleWithNewData);

        const rotRserv = service.getRotRserv(formGroup) as any;

        expect(rotRserv).toMatchObject(sampleWithNewData);
      });

      it('should return NewRotRserv for empty RotRserv initial value', () => {
        const formGroup = service.createRotRservFormGroup();

        const rotRserv = service.getRotRserv(formGroup) as any;

        expect(rotRserv).toMatchObject({});
      });

      it('should return IRotRserv', () => {
        const formGroup = service.createRotRservFormGroup(sampleWithRequiredData);

        const rotRserv = service.getRotRserv(formGroup) as any;

        expect(rotRserv).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRotRserv should not enable id FormControl', () => {
        const formGroup = service.createRotRservFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRotRserv should disable id FormControl', () => {
        const formGroup = service.createRotRservFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
