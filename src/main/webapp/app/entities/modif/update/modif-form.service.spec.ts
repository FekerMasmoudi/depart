import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../modif.test-samples';

import { ModifFormService } from './modif-form.service';

describe('Modif Form Service', () => {
  let service: ModifFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ModifFormService);
  });

  describe('Service methods', () => {
    describe('createModifFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createModifFormGroup();

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
            matric: expect.any(Object),
            cd1: expect.any(Object),
            decmotif: expect.any(Object),
            heur: expect.any(Object),
            chre: expect.any(Object),
            typ: expect.any(Object),
          })
        );
      });

      it('passing IModif should create a new form with FormGroup', () => {
        const formGroup = service.createModifFormGroup(sampleWithRequiredData);

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
            matric: expect.any(Object),
            cd1: expect.any(Object),
            decmotif: expect.any(Object),
            heur: expect.any(Object),
            chre: expect.any(Object),
            typ: expect.any(Object),
          })
        );
      });
    });

    describe('getModif', () => {
      it('should return NewModif for default Modif initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createModifFormGroup(sampleWithNewData);

        const modif = service.getModif(formGroup) as any;

        expect(modif).toMatchObject(sampleWithNewData);
      });

      it('should return NewModif for empty Modif initial value', () => {
        const formGroup = service.createModifFormGroup();

        const modif = service.getModif(formGroup) as any;

        expect(modif).toMatchObject({});
      });

      it('should return IModif', () => {
        const formGroup = service.createModifFormGroup(sampleWithRequiredData);

        const modif = service.getModif(formGroup) as any;

        expect(modif).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IModif should not enable id FormControl', () => {
        const formGroup = service.createModifFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewModif should disable id FormControl', () => {
        const formGroup = service.createModifFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
