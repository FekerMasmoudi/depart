import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../rh-agent.test-samples';

import { RhAgentFormService } from './rh-agent-form.service';

describe('RhAgent Form Service', () => {
  let service: RhAgentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RhAgentFormService);
  });

  describe('Service methods', () => {
    describe('createRhAgentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createRhAgentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            matric: expect.any(Object),
            decjour: expect.any(Object),
            dateffrh: expect.any(Object),
            decoper: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
          })
        );
      });

      it('passing IRhAgent should create a new form with FormGroup', () => {
        const formGroup = service.createRhAgentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            matric: expect.any(Object),
            decjour: expect.any(Object),
            dateffrh: expect.any(Object),
            decoper: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
          })
        );
      });
    });

    describe('getRhAgent', () => {
      it('should return NewRhAgent for default RhAgent initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createRhAgentFormGroup(sampleWithNewData);

        const rhAgent = service.getRhAgent(formGroup) as any;

        expect(rhAgent).toMatchObject(sampleWithNewData);
      });

      it('should return NewRhAgent for empty RhAgent initial value', () => {
        const formGroup = service.createRhAgentFormGroup();

        const rhAgent = service.getRhAgent(formGroup) as any;

        expect(rhAgent).toMatchObject({});
      });

      it('should return IRhAgent', () => {
        const formGroup = service.createRhAgentFormGroup(sampleWithRequiredData);

        const rhAgent = service.getRhAgent(formGroup) as any;

        expect(rhAgent).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IRhAgent should not enable id FormControl', () => {
        const formGroup = service.createRhAgentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewRhAgent should disable id FormControl', () => {
        const formGroup = service.createRhAgentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
