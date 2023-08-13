import { TestBed } from '@angular/core/testing';

import { sampleWithRequiredData, sampleWithNewData } from '../machine.test-samples';

import { MachineFormService } from './machine-form.service';

describe('Machine Form Service', () => {
  let service: MachineFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MachineFormService);
  });

  describe('Service methods', () => {
    describe('createMachineFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createMachineFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdmac: expect.any(Object),
            cdmod: expect.any(Object),
            cdmarque: expect.any(Object),
            lbmac: expect.any(Object),
            refmac: expect.any(Object),
            serie: expect.any(Object),
            datfab: expect.any(Object),
            datacq: expect.any(Object),
            datmes: expect.any(Object),
            valacq: expect.any(Object),
            obs: expect.any(Object),
            numplan: expect.any(Object),
            cdlipro: expect.any(Object),
            immat: expect.any(Object),
            marque: expect.any(Object),
            typev: expect.any(Object),
            numser: expect.any(Object),
            puiss: expect.any(Object),
            nrj: expect.any(Object),
            genre: expect.any(Object),
            cylind: expect.any(Object),
            pdsvid: expect.any(Object),
            charge: expect.any(Object),
            plcass: expect.any(Object),
            plcdeb: expect.any(Object),
            cpt: expect.any(Object),
            cptmnt: expect.any(Object),
            actif: expect.any(Object),
            datact: expect.any(Object),
            cdcatvh: expect.any(Object),
            taux: expect.any(Object),
            kmmoy: expect.any(Object),
            codstat: expect.any(Object),
            edition: expect.any(Object),
            valassur: expect.any(Object),
            valamort: expect.any(Object),
            consommodel: expect.any(Object),
            decetat: expect.any(Object),
            codtypvoit: expect.any(Object),
            cdtyp: expect.any(Object),
            cdnat: expect.any(Object),
            typbv: expect.any(Object),
            cdtypbv: expect.any(Object),
            pneu: expect.any(Object),
            gps: expect.any(Object),
            marquebv: expect.any(Object),
            typboite: expect.any(Object),
          })
        );
      });

      it('passing IMachine should create a new form with FormGroup', () => {
        const formGroup = service.createMachineFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            cdmac: expect.any(Object),
            cdmod: expect.any(Object),
            cdmarque: expect.any(Object),
            lbmac: expect.any(Object),
            refmac: expect.any(Object),
            serie: expect.any(Object),
            datfab: expect.any(Object),
            datacq: expect.any(Object),
            datmes: expect.any(Object),
            valacq: expect.any(Object),
            obs: expect.any(Object),
            numplan: expect.any(Object),
            cdlipro: expect.any(Object),
            immat: expect.any(Object),
            marque: expect.any(Object),
            typev: expect.any(Object),
            numser: expect.any(Object),
            puiss: expect.any(Object),
            nrj: expect.any(Object),
            genre: expect.any(Object),
            cylind: expect.any(Object),
            pdsvid: expect.any(Object),
            charge: expect.any(Object),
            plcass: expect.any(Object),
            plcdeb: expect.any(Object),
            cpt: expect.any(Object),
            cptmnt: expect.any(Object),
            actif: expect.any(Object),
            datact: expect.any(Object),
            cdcatvh: expect.any(Object),
            taux: expect.any(Object),
            kmmoy: expect.any(Object),
            codstat: expect.any(Object),
            edition: expect.any(Object),
            valassur: expect.any(Object),
            valamort: expect.any(Object),
            consommodel: expect.any(Object),
            decetat: expect.any(Object),
            codtypvoit: expect.any(Object),
            cdtyp: expect.any(Object),
            cdnat: expect.any(Object),
            typbv: expect.any(Object),
            cdtypbv: expect.any(Object),
            pneu: expect.any(Object),
            gps: expect.any(Object),
            marquebv: expect.any(Object),
            typboite: expect.any(Object),
          })
        );
      });
    });

    describe('getMachine', () => {
      it('should return NewMachine for default Machine initial value', () => {
        // eslint-disable-next-line @typescript-eslint/no-unused-vars
        const formGroup = service.createMachineFormGroup(sampleWithNewData);

        const machine = service.getMachine(formGroup) as any;

        expect(machine).toMatchObject(sampleWithNewData);
      });

      it('should return NewMachine for empty Machine initial value', () => {
        const formGroup = service.createMachineFormGroup();

        const machine = service.getMachine(formGroup) as any;

        expect(machine).toMatchObject({});
      });

      it('should return IMachine', () => {
        const formGroup = service.createMachineFormGroup(sampleWithRequiredData);

        const machine = service.getMachine(formGroup) as any;

        expect(machine).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IMachine should not enable id FormControl', () => {
        const formGroup = service.createMachineFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewMachine should disable id FormControl', () => {
        const formGroup = service.createMachineFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
