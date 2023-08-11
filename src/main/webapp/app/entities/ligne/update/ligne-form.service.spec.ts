import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../ligne.test-samples';

import { LigneFormService } from './ligne-form.service';

describe('Ligne Form Service', () => {
  let service: LigneFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LigneFormService);
  });

  describe('Service methods', () => {
    describe('createLigneFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createLigneFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            denumli: expect.any(Object),
            dectyli: expect.any(Object),
            dectyta: expect.any(Object),
            denomli: expect.any(Object),
            dectyeq: expect.any(Object),
            denbrkm: expect.any(Object),
            detparc: expect.any(Object),
            dedural: expect.any(Object),
            dedurrt: expect.any(Object),
            detrjva: expect.any(Object),
            detrjvr: expect.any(Object),
            depiste: expect.any(Object),
            statlig: expect.any(Object),
            lig: expect.any(Object),
            lig1: expect.any(Object),
            valide: expect.any(Object),
            denumli2: expect.any(Object),
            kml: expect.any(Object),
            description: expect.any(Object),
            mimtype: expect.any(Object),
            filename: expect.any(Object),
            charset: expect.any(Object),
            lastupdate: expect.any(Object),
          })
        );
      });

      it('passing ILigne should create a new form with FormGroup', () => {
        const formGroup = service.createLigneFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            denumli: expect.any(Object),
            dectyli: expect.any(Object),
            dectyta: expect.any(Object),
            denomli: expect.any(Object),
            dectyeq: expect.any(Object),
            denbrkm: expect.any(Object),
            detparc: expect.any(Object),
            dedural: expect.any(Object),
            dedurrt: expect.any(Object),
            detrjva: expect.any(Object),
            detrjvr: expect.any(Object),
            depiste: expect.any(Object),
            statlig: expect.any(Object),
            lig: expect.any(Object),
            lig1: expect.any(Object),
            valide: expect.any(Object),
            denumli2: expect.any(Object),
            kml: expect.any(Object),
            description: expect.any(Object),
            mimtype: expect.any(Object),
            filename: expect.any(Object),
            charset: expect.any(Object),
            lastupdate: expect.any(Object),
          })
        );
      });
    });

    describe('getLigne', () => {
      it('should return NewLigne for default Ligne initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createLigneFormGroup(sampleWithNewData);

        const ligne = service.getLigne(formGroup) as any;

        expect(ligne).toMatchObject(sampleWithNewData);
      });

      it('should return NewLigne for empty Ligne initial value', () => {
        const formGroup = service.createLigneFormGroup();

        const ligne = service.getLigne(formGroup) as any;

        expect(ligne).toMatchObject({});
      });

      it('should return ILigne', () => {
        const formGroup = service.createLigneFormGroup(sampleWithRequiredData);

        const ligne = service.getLigne(formGroup) as any;

        expect(ligne).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing ILigne should not enable id FormControl', () => {
        const formGroup = service.createLigneFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewLigne should disable id FormControl', () => {
        const formGroup = service.createLigneFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
