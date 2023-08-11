import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../deprotat.test-samples';

import { DeprotatFormService } from './deprotat-form.service';

describe('Deprotat Form Service', () => {
  let service: DeprotatFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeprotatFormService);
  });

  describe('Service methods', () => {
    describe('createDeprotatFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDeprotatFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            numrotat: expect.any(Object),
            ligdeccent: expect.any(Object),
            ligdecagenc: expect.any(Object),
            denumli: expect.any(Object),
            decstat: expect.any(Object),
            decsta1: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            cdmac: expect.any(Object),
            hdeparte: expect.any(Object),
            hretoure: expect.any(Object),
            harralle: expect.any(Object),
            harrrete: expect.any(Object),
            rannul: expect.any(Object),
            km: expect.any(Object),
            motifa: expect.any(Object),
            observ: expect.any(Object),
            recettesvoy: expect.any(Object),
            nbrevoy: expect.any(Object),
            paye: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            decmotifcha: expect.any(Object),
            decmotifrea: expect.any(Object),
            idapex: expect.any(Object),
            plusmoins: expect.any(Object),
            a: expect.any(Object),
            r: expect.any(Object),
          })
        );
      });

      it('passing IDeprotat should create a new form with FormGroup', () => {
        const formGroup = service.createDeprotatFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            dedated: expect.any(Object),
            denumdp: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            numrotat: expect.any(Object),
            ligdeccent: expect.any(Object),
            ligdecagenc: expect.any(Object),
            denumli: expect.any(Object),
            decstat: expect.any(Object),
            decsta1: expect.any(Object),
            matric: expect.any(Object),
            matric1: expect.any(Object),
            cdmac: expect.any(Object),
            hdeparte: expect.any(Object),
            hretoure: expect.any(Object),
            harralle: expect.any(Object),
            harrrete: expect.any(Object),
            rannul: expect.any(Object),
            km: expect.any(Object),
            motifa: expect.any(Object),
            observ: expect.any(Object),
            recettesvoy: expect.any(Object),
            nbrevoy: expect.any(Object),
            paye: expect.any(Object),
            cd1: expect.any(Object),
            cd2: expect.any(Object),
            cd3: expect.any(Object),
            decmotifcha: expect.any(Object),
            decmotifrea: expect.any(Object),
            idapex: expect.any(Object),
            plusmoins: expect.any(Object),
            a: expect.any(Object),
            r: expect.any(Object),
          })
        );
      });
    });

    describe('getDeprotat', () => {
      it('should return NewDeprotat for default Deprotat initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDeprotatFormGroup(sampleWithNewData);

        const deprotat = service.getDeprotat(formGroup) as any;

        expect(deprotat).toMatchObject(sampleWithNewData);
      });

      it('should return NewDeprotat for empty Deprotat initial value', () => {
        const formGroup = service.createDeprotatFormGroup();

        const deprotat = service.getDeprotat(formGroup) as any;

        expect(deprotat).toMatchObject({});
      });

      it('should return IDeprotat', () => {
        const formGroup = service.createDeprotatFormGroup(sampleWithRequiredData);

        const deprotat = service.getDeprotat(formGroup) as any;

        expect(deprotat).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDeprotat should not enable id FormControl', () => {
        const formGroup = service.createDeprotatFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDeprotat should disable id FormControl', () => {
        const formGroup = service.createDeprotatFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
