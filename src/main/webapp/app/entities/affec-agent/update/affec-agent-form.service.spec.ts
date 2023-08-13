import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../affec-agent.test-samples';

import { AffecAgentFormService } from './affec-agent-form.service';

describe('AffecAgent Form Service', () => {
  let service: AffecAgentFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AffecAgentFormService);
  });

  describe('Service methods', () => {
    describe('createAffecAgentFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createAffecAgentFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            cdsocie: expect.any(Object),
            decexer: expect.any(Object),
            cdmois: expect.any(Object),
            matric: expect.any(Object),
            matric2: expect.any(Object),
            cdmac: expect.any(Object),
          })
        );
      });

      it('passing IAffecAgent should create a new form with FormGroup', () => {
        const formGroup = service.createAffecAgentFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            deccent: expect.any(Object),
            decagenc: expect.any(Object),
            decserv: expect.any(Object),
            decoper: expect.any(Object),
            decsean: expect.any(Object),
            cdsocie: expect.any(Object),
            decexer: expect.any(Object),
            cdmois: expect.any(Object),
            matric: expect.any(Object),
            matric2: expect.any(Object),
            cdmac: expect.any(Object),
          })
        );
      });
    });

    describe('getAffecAgent', () => {
      it('should return NewAffecAgent for default AffecAgent initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createAffecAgentFormGroup(sampleWithNewData);

        const affecAgent = service.getAffecAgent(formGroup) as any;

        expect(affecAgent).toMatchObject(sampleWithNewData);
      });

      it('should return NewAffecAgent for empty AffecAgent initial value', () => {
        const formGroup = service.createAffecAgentFormGroup();

        const affecAgent = service.getAffecAgent(formGroup) as any;

        expect(affecAgent).toMatchObject({});
      });

      it('should return IAffecAgent', () => {
        const formGroup = service.createAffecAgentFormGroup(sampleWithRequiredData);

        const affecAgent = service.getAffecAgent(formGroup) as any;

        expect(affecAgent).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IAffecAgent should not enable id FormControl', () => {
        const formGroup = service.createAffecAgentFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewAffecAgent should disable id FormControl', () => {
        const formGroup = service.createAffecAgentFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
