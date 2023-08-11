import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../fonc-agent.test-samples';

import { FoncAgentFormService } from './fonc-agent-form.service';

describe('FoncAgent Form Service', () => {
  let service: FoncAgentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FoncAgentFormService);
  });

  describe('Service methods', () => {
    describe('createFoncAgentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFoncAgentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdfonc: expect.any(Object),
            matric: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            dateff: expect.any(Object),
            valide: expect.any(Object),
          })
        );
      });

      it('passing IFoncAgent should create a new form with FormGroup', () => {
        const formGroup = service.createFoncAgentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdfonc: expect.any(Object),
            matric: expect.any(Object),
            nom: expect.any(Object),
            prenom: expect.any(Object),
            dateff: expect.any(Object),
            valide: expect.any(Object),
          })
        );
      });
    });

    describe('getFoncAgent', () => {
      it('should return NewFoncAgent for default FoncAgent initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createFoncAgentFormGroup(sampleWithNewData);

        const foncAgent = service.getFoncAgent(formGroup) as any;

        expect(foncAgent).toMatchObject(sampleWithNewData);
      });

      it('should return NewFoncAgent for empty FoncAgent initial value', () => {
        const formGroup = service.createFoncAgentFormGroup();

        const foncAgent = service.getFoncAgent(formGroup) as any;

        expect(foncAgent).toMatchObject({});
      });

      it('should return IFoncAgent', () => {
        const formGroup = service.createFoncAgentFormGroup(sampleWithRequiredData);

        const foncAgent = service.getFoncAgent(formGroup) as any;

        expect(foncAgent).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFoncAgent should not enable id FormControl', () => {
        const formGroup = service.createFoncAgentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFoncAgent should disable id FormControl', () => {
        const formGroup = service.createFoncAgentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
