import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../depart.test-samples';

import { DepartFormService } from './depart-form.service';

describe('Depart Form Service', () => {
  let service: DepartFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartFormService);
  });

  describe('Service methods', () => {
    describe('createDepartFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDepartFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            cdmac: expect.any(Object),
            deheups: expect.any(Object),
            deheufs: expect.any(Object),
            denbrro: expect.any(Object),
            deheuaa: expect.any(Object),
            deheudr: expect.any(Object),
            deheupd: expect.any(Object),
            deampli: expect.any(Object),
            obsind: expect.any(Object),
            vldroul: expect.any(Object),
            deetat: expect.any(Object),
            deannul: expect.any(Object),
            decclot: expect.any(Object),
            execute: expect.any(Object),
            motifa: expect.any(Object),
            observ: expect.any(Object),
            recettes: expect.any(Object),
            nbrevoy: expect.any(Object),
            decmotifch: expect.any(Object),
            decmotifre: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            decmotifcha: expect.any(Object),
            decmotifrea: expect.any(Object),
          })
        );
      });

      it('passing IDepart should create a new form with FormGroup', () => {
        const formGroup = service.createDepartFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            cdmac: expect.any(Object),
            deheups: expect.any(Object),
            deheufs: expect.any(Object),
            denbrro: expect.any(Object),
            deheuaa: expect.any(Object),
            deheudr: expect.any(Object),
            deheupd: expect.any(Object),
            deampli: expect.any(Object),
            obsind: expect.any(Object),
            vldroul: expect.any(Object),
            deetat: expect.any(Object),
            deannul: expect.any(Object),
            decclot: expect.any(Object),
            execute: expect.any(Object),
            motifa: expect.any(Object),
            observ: expect.any(Object),
            recettes: expect.any(Object),
            nbrevoy: expect.any(Object),
            decmotifch: expect.any(Object),
            decmotifre: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            decmotifcha: expect.any(Object),
            decmotifrea: expect.any(Object),
          })
        );
      });
    });

    describe('getDepart', () => {
      it('should return NewDepart for default Depart initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDepartFormGroup(sampleWithNewData);

        const depart = service.getDepart(formGroup) as any;

        expect(depart).toMatchObject(sampleWithNewData);
      });

      it('should return NewDepart for empty Depart initial value', () => {
        const formGroup = service.createDepartFormGroup();

        const depart = service.getDepart(formGroup) as any;

        expect(depart).toMatchObject({});
      });

      it('should return IDepart', () => {
        const formGroup = service.createDepartFormGroup(sampleWithRequiredData);

        const depart = service.getDepart(formGroup) as any;

        expect(depart).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDepart should not enable id FormControl', () => {
        const formGroup = service.createDepartFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDepart should disable id FormControl', () => {
        const formGroup = service.createDepartFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
