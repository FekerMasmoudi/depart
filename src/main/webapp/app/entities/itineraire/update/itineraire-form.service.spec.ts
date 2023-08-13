import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../itineraire.test-samples';

import { ItineraireFormService } from './itineraire-form.service';

describe('Itineraire Form Service', () => {
  let service: ItineraireFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ItineraireFormService);
  });

  describe('Service methods', () => {
    describe('createItineraireFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createItineraireFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            denumli: expect.any(Object),
            decstat: expect.any(Object),
            denumlg: expect.any(Object),
            dekmsta: expect.any(Object),
            dedurtr: expect.any(Object),
            deescale: expect.any(Object),
            embra: expect.any(Object),
            section: expect.any(Object),
            sens: expect.any(Object),
            dectyst: expect.any(Object),
          })
        );
      });

      it('passing IItineraire should create a new form with FormGroup', () => {
        const formGroup = service.createItineraireFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            denumli: expect.any(Object),
            decstat: expect.any(Object),
            denumlg: expect.any(Object),
            dekmsta: expect.any(Object),
            dedurtr: expect.any(Object),
            deescale: expect.any(Object),
            embra: expect.any(Object),
            section: expect.any(Object),
            sens: expect.any(Object),
            dectyst: expect.any(Object),
          })
        );
      });
    });

    describe('getItineraire', () => {
      it('should return NewItineraire for default Itineraire initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createItineraireFormGroup(sampleWithNewData);

        const itineraire = service.getItineraire(formGroup) as any;

        expect(itineraire).toMatchObject(sampleWithNewData);
      });

      it('should return NewItineraire for empty Itineraire initial value', () => {
        const formGroup = service.createItineraireFormGroup();

        const itineraire = service.getItineraire(formGroup) as any;

        expect(itineraire).toMatchObject({});
      });

      it('should return IItineraire', () => {
        const formGroup = service.createItineraireFormGroup(sampleWithRequiredData);

        const itineraire = service.getItineraire(formGroup) as any;

        expect(itineraire).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IItineraire should not enable id FormControl', () => {
        const formGroup = service.createItineraireFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewItineraire should disable id FormControl', () => {
        const formGroup = service.createItineraireFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
