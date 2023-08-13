import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../affectagent.test-samples';

import { AffectagentFormService } from './affectagent-form.service';

describe('Affectagent Form Service', () => {
  let service: AffectagentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AffectagentFormService);
  });

  describe('Service methods', () => {
    describe('createAffectagentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAffectagentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            cdmois: expect.any(Object),
            cdsocie: expect.any(Object),
            decexer: expect.any(Object),
            matric: expect.any(Object),
            matric2: expect.any(Object),
            cdmac: expect.any(Object),
          })
        );
      });

      it('passing IAffectagent should create a new form with FormGroup', () => {
        const formGroup = service.createAffectagentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            cdmois: expect.any(Object),
            cdsocie: expect.any(Object),
            decexer: expect.any(Object),
            matric: expect.any(Object),
            matric2: expect.any(Object),
            cdmac: expect.any(Object),
          })
        );
      });
    });

    describe('getAffectagent', () => {
      it('should return NewAffectagent for default Affectagent initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAffectagentFormGroup(sampleWithNewData);

        const affectagent = service.getAffectagent(formGroup) as any;

        expect(affectagent).toMatchObject(sampleWithNewData);
      });

      it('should return NewAffectagent for empty Affectagent initial value', () => {
        const formGroup = service.createAffectagentFormGroup();

        const affectagent = service.getAffectagent(formGroup) as any;

        expect(affectagent).toMatchObject({});
      });

      it('should return IAffectagent', () => {
        const formGroup = service.createAffectagentFormGroup(sampleWithRequiredData);

        const affectagent = service.getAffectagent(formGroup) as any;

        expect(affectagent).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAffectagent should not enable id FormControl', () => {
        const formGroup = service.createAffectagentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAffectagent should disable id FormControl', () => {
        const formGroup = service.createAffectagentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
