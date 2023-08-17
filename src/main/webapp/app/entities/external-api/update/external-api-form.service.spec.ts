import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../external-api.test-samples';

import { ExternalApiFormService } from './external-api-form.service';

describe('ExternalApi Form Service', () => {
  let service: ExternalApiFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExternalApiFormService);
  });

  describe('Service methods', () => {
    describe('createExternalApiFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createExternalApiFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idm: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            comments: expect.any(Object),
            idschema: expect.any(Object),
            datecreatedt: expect.any(Object),
            lastupdatedm: expect.any(Object),
            origin: expect.any(Object),
            templateid: expect.any(Object),
            idmodule: expect.any(Object),
            uritemplate: expect.any(Object),
            priority: expect.any(Object),
            schemaidt: expect.any(Object),
            createdatet: expect.any(Object),
            lastupdatete: expect.any(Object),
            entity: expect.any(Object),
            parametre: expect.any(Object),
            countrowsreq: expect.any(Object),
            countrowsres: expect.any(Object),
            frequency: expect.any(Object),
            emergencycode: expect.any(Object),
            satausgetapi: expect.any(Object),
          })
        );
      });

      it('passing IExternalApi should create a new form with FormGroup', () => {
        const formGroup = service.createExternalApiFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            idm: expect.any(Object),
            name: expect.any(Object),
            status: expect.any(Object),
            comments: expect.any(Object),
            idschema: expect.any(Object),
            datecreatedt: expect.any(Object),
            lastupdatedm: expect.any(Object),
            origin: expect.any(Object),
            templateid: expect.any(Object),
            idmodule: expect.any(Object),
            uritemplate: expect.any(Object),
            priority: expect.any(Object),
            schemaidt: expect.any(Object),
            createdatet: expect.any(Object),
            lastupdatete: expect.any(Object),
            entity: expect.any(Object),
            parametre: expect.any(Object),
            countrowsreq: expect.any(Object),
            countrowsres: expect.any(Object),
            frequency: expect.any(Object),
            emergencycode: expect.any(Object),
            satausgetapi: expect.any(Object),
          })
        );
      });
    });

    describe('getExternalApi', () => {
      it('should return NewExternalApi for default ExternalApi initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createExternalApiFormGroup(sampleWithNewData);

        const externalApi = service.getExternalApi(formGroup) as any;

        expect(externalApi).toMatchObject(sampleWithNewData);
      });

      it('should return NewExternalApi for empty ExternalApi initial value', () => {
        const formGroup = service.createExternalApiFormGroup();

        const externalApi = service.getExternalApi(formGroup) as any;

        expect(externalApi).toMatchObject({});
      });

      it('should return IExternalApi', () => {
        const formGroup = service.createExternalApiFormGroup(sampleWithRequiredData);

        const externalApi = service.getExternalApi(formGroup) as any;

        expect(externalApi).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IExternalApi should not enable id FormControl', () => {
        const formGroup = service.createExternalApiFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewExternalApi should disable id FormControl', () => {
        const formGroup = service.createExternalApiFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
