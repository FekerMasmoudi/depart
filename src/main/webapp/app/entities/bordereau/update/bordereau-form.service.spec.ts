import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../bordereau.test-samples';

import { BordereauFormService } from './bordereau-form.service';

describe('Bordereau Form Service', () => {
  let service: BordereauFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BordereauFormService);
  });

  describe('Service methods', () => {
    describe('createBordereauFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBordereauFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            exercice: expect.any(Object),
            denbord: expect.any(Object),
            det_deccent: expect.any(Object),
            det_decagenc: expect.any(Object),
            decserv: expect.any(Object),
            cdmac: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            natbord: expect.any(Object),
            detadedb: expect.any(Object),
            numb_pdat: expect.any(Object),
            deheupsr: expect.any(Object),
            demnttn: expect.any(Object),
            demntto: expect.any(Object),
            decetbr: expect.any(Object),
            decetcs: expect.any(Object),
            denumcs: expect.any(Object),
            denumbr: expect.any(Object),
            denumver: expect.any(Object),
            declote: expect.any(Object),
            date_saisie: expect.any(Object),
            clorec: expect.any(Object),
            demntvers: expect.any(Object),
          })
        );
      });

      it('passing IBordereau should create a new form with FormGroup', () => {
        const formGroup = service.createBordereauFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            exercice: expect.any(Object),
            denbord: expect.any(Object),
            det_deccent: expect.any(Object),
            det_decagenc: expect.any(Object),
            decserv: expect.any(Object),
            cdmac: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            natbord: expect.any(Object),
            detadedb: expect.any(Object),
            numb_pdat: expect.any(Object),
            deheupsr: expect.any(Object),
            demnttn: expect.any(Object),
            demntto: expect.any(Object),
            decetbr: expect.any(Object),
            decetcs: expect.any(Object),
            denumcs: expect.any(Object),
            denumbr: expect.any(Object),
            denumver: expect.any(Object),
            declote: expect.any(Object),
            date_saisie: expect.any(Object),
            clorec: expect.any(Object),
            demntvers: expect.any(Object),
          })
        );
      });
    });

    describe('getBordereau', () => {
      it('should return NewBordereau for default Bordereau initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createBordereauFormGroup(sampleWithNewData);

        const bordereau = service.getBordereau(formGroup) as any;

        expect(bordereau).toMatchObject(sampleWithNewData);
      });

      it('should return NewBordereau for empty Bordereau initial value', () => {
        const formGroup = service.createBordereauFormGroup();

        const bordereau = service.getBordereau(formGroup) as any;

        expect(bordereau).toMatchObject({});
      });

      it('should return IBordereau', () => {
        const formGroup = service.createBordereauFormGroup(sampleWithRequiredData);

        const bordereau = service.getBordereau(formGroup) as any;

        expect(bordereau).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBordereau should not enable id FormControl', () => {
        const formGroup = service.createBordereauFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBordereau should disable id FormControl', () => {
        const formGroup = service.createBordereauFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
