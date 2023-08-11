import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../bon-tvx.test-samples';

import { BonTvxFormService } from './bon-tvx-form.service';

describe('BonTvx Form Service', () => {
  let service: BonTvxFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BonTvxFormService);
  });

  describe('Service methods', () => {
    describe('createBonTvxFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createBonTvxFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdexerc: expect.any(Object),
            numbt: expect.any(Object),
            cdtier: expect.any(Object),
            cdmac: expect.any(Object),
            maccdmac: expect.any(Object),
            cdserv: expect.any(Object),
            decagen: expect.any(Object),
            dradecagen: expect.any(Object),
            cdorga: expect.any(Object),
            refbt: expect.any(Object),
            datbt: expect.any(Object),
            datdt: expect.any(Object),
            datft: expect.any(Object),
            vld: expect.any(Object),
            typtvx: expect.any(Object),
            heurdb: expect.any(Object),
            heurfi: expect.any(Object),
            observ: expect.any(Object),
            datsrt: expect.any(Object),
            heursr: expect.any(Object),
            obstest: expect.any(Object),
            indexdep: expect.any(Object),
            indexarr: expect.any(Object),
            immatex: expect.any(Object),
            nomchauff: expect.any(Object),
            numpermis: expect.any(Object),
            etab: expect.any(Object),
            compteur: expect.any(Object),
            cptorg: expect.any(Object),
            cdtyptr: expect.any(Object),
            decstat: expect.any(Object),
            testeur: expect.any(Object),
            motifdep: expect.any(Object),
            cdtypmnt: expect.any(Object),
            datsorprev: expect.any(Object),
            datmnqdu: expect.any(Object),
            datmnqau: expect.any(Object),
            datentant: expect.any(Object),
            codstat: expect.any(Object),
            datvld: expect.any(Object),
            observ1: expect.any(Object),
            testeur1: expect.any(Object),
            validag: expect.any(Object),
            datsais: expect.any(Object),
          })
        );
      });

      it('passing IBonTvx should create a new form with FormGroup', () => {
        const formGroup = service.createBonTvxFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdexerc: expect.any(Object),
            numbt: expect.any(Object),
            cdtier: expect.any(Object),
            cdmac: expect.any(Object),
            maccdmac: expect.any(Object),
            cdserv: expect.any(Object),
            decagen: expect.any(Object),
            dradecagen: expect.any(Object),
            cdorga: expect.any(Object),
            refbt: expect.any(Object),
            datbt: expect.any(Object),
            datdt: expect.any(Object),
            datft: expect.any(Object),
            vld: expect.any(Object),
            typtvx: expect.any(Object),
            heurdb: expect.any(Object),
            heurfi: expect.any(Object),
            observ: expect.any(Object),
            datsrt: expect.any(Object),
            heursr: expect.any(Object),
            obstest: expect.any(Object),
            indexdep: expect.any(Object),
            indexarr: expect.any(Object),
            immatex: expect.any(Object),
            nomchauff: expect.any(Object),
            numpermis: expect.any(Object),
            etab: expect.any(Object),
            compteur: expect.any(Object),
            cptorg: expect.any(Object),
            cdtyptr: expect.any(Object),
            decstat: expect.any(Object),
            testeur: expect.any(Object),
            motifdep: expect.any(Object),
            cdtypmnt: expect.any(Object),
            datsorprev: expect.any(Object),
            datmnqdu: expect.any(Object),
            datmnqau: expect.any(Object),
            datentant: expect.any(Object),
            codstat: expect.any(Object),
            datvld: expect.any(Object),
            observ1: expect.any(Object),
            testeur1: expect.any(Object),
            validag: expect.any(Object),
            datsais: expect.any(Object),
          })
        );
      });
    });

    describe('getBonTvx', () => {
      it('should return NewBonTvx for default BonTvx initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createBonTvxFormGroup(sampleWithNewData);

        const bonTvx = service.getBonTvx(formGroup) as any;

        expect(bonTvx).toMatchObject(sampleWithNewData);
      });

      it('should return NewBonTvx for empty BonTvx initial value', () => {
        const formGroup = service.createBonTvxFormGroup();

        const bonTvx = service.getBonTvx(formGroup) as any;

        expect(bonTvx).toMatchObject({});
      });

      it('should return IBonTvx', () => {
        const formGroup = service.createBonTvxFormGroup(sampleWithRequiredData);

        const bonTvx = service.getBonTvx(formGroup) as any;

        expect(bonTvx).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IBonTvx should not enable id FormControl', () => {
        const formGroup = service.createBonTvxFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewBonTvx should disable id FormControl', () => {
        const formGroup = service.createBonTvxFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
