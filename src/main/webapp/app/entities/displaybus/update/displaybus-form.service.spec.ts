import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../displaybus.test-samples';

import { DisplaybusFormService } from './displaybus-form.service';

describe('Displaybus Form Service', () => {
  let service: DisplaybusFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DisplaybusFormService);
  });

  describe('Service methods', () => {
    describe('createDisplaybusFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createDisplaybusFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lang: expect.any(Object),
            vehicule: expect.any(Object),
            num_appel: expect.any(Object),
            detail_ligne: expect.any(Object),
            ligne: expect.any(Object),
            direction: expect.any(Object),
            denumli: expect.any(Object),
            deltyli: expect.any(Object),
          })
        );
      });

      it('passing IDisplaybus should create a new form with FormGroup', () => {
        const formGroup = service.createDisplaybusFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            lang: expect.any(Object),
            vehicule: expect.any(Object),
            num_appel: expect.any(Object),
            detail_ligne: expect.any(Object),
            ligne: expect.any(Object),
            direction: expect.any(Object),
            denumli: expect.any(Object),
            deltyli: expect.any(Object),
          })
        );
      });
    });

    describe('getDisplaybus', () => {
      it('should return NewDisplaybus for default Displaybus initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createDisplaybusFormGroup(sampleWithNewData);

        const displaybus = service.getDisplaybus(formGroup) as any;

        expect(displaybus).toMatchObject(sampleWithNewData);
      });

      it('should return NewDisplaybus for empty Displaybus initial value', () => {
        const formGroup = service.createDisplaybusFormGroup();

        const displaybus = service.getDisplaybus(formGroup) as any;

        expect(displaybus).toMatchObject({});
      });

      it('should return IDisplaybus', () => {
        const formGroup = service.createDisplaybusFormGroup(sampleWithRequiredData);

        const displaybus = service.getDisplaybus(formGroup) as any;

        expect(displaybus).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IDisplaybus should not enable id FormControl', () => {
        const formGroup = service.createDisplaybusFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewDisplaybus should disable id FormControl', () => {
        const formGroup = service.createDisplaybusFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
