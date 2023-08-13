import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMachine, NewMachine } from '../machine.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMachine for edit and NewMachineFormGroupInput for create.
 */
type MachineFormGroupInput = IMachine | PartialWithRequiredKeyOf<NewMachine>;

type MachineFormDefaults = Pick<NewMachine, 'id'>;

type MachineFormGroupContent = {
  id: FormControl<IMachine['id'] | NewMachine['id']>;
  cdmac: FormControl<IMachine['cdmac']>;
  cdmod: FormControl<IMachine['cdmod']>;
  cdmarque: FormControl<IMachine['cdmarque']>;
  lbmac: FormControl<IMachine['lbmac']>;
  refmac: FormControl<IMachine['refmac']>;
  serie: FormControl<IMachine['serie']>;
  datfab: FormControl<IMachine['datfab']>;
  datacq: FormControl<IMachine['datacq']>;
  datmes: FormControl<IMachine['datmes']>;
  valacq: FormControl<IMachine['valacq']>;
  obs: FormControl<IMachine['obs']>;
  numplan: FormControl<IMachine['numplan']>;
  cdlipro: FormControl<IMachine['cdlipro']>;
  immat: FormControl<IMachine['immat']>;
  marque: FormControl<IMachine['marque']>;
  typev: FormControl<IMachine['typev']>;
  numser: FormControl<IMachine['numser']>;
  puiss: FormControl<IMachine['puiss']>;
  nrj: FormControl<IMachine['nrj']>;
  genre: FormControl<IMachine['genre']>;
  cylind: FormControl<IMachine['cylind']>;
  pdsvid: FormControl<IMachine['pdsvid']>;
  charge: FormControl<IMachine['charge']>;
  plcass: FormControl<IMachine['plcass']>;
  plcdeb: FormControl<IMachine['plcdeb']>;
  cpt: FormControl<IMachine['cpt']>;
  cptmnt: FormControl<IMachine['cptmnt']>;
  actif: FormControl<IMachine['actif']>;
  datact: FormControl<IMachine['datact']>;
  cdcatvh: FormControl<IMachine['cdcatvh']>;
  taux: FormControl<IMachine['taux']>;
  kmmoy: FormControl<IMachine['kmmoy']>;
  codstat: FormControl<IMachine['codstat']>;
  edition: FormControl<IMachine['edition']>;
  valassur: FormControl<IMachine['valassur']>;
  valamort: FormControl<IMachine['valamort']>;
  consommodel: FormControl<IMachine['consommodel']>;
  decetat: FormControl<IMachine['decetat']>;
  codtypvoit: FormControl<IMachine['codtypvoit']>;
  cdtyp: FormControl<IMachine['cdtyp']>;
  cdnat: FormControl<IMachine['cdnat']>;
  typbv: FormControl<IMachine['typbv']>;
  cdtypbv: FormControl<IMachine['cdtypbv']>;
  pneu: FormControl<IMachine['pneu']>;
  gps: FormControl<IMachine['gps']>;
  marquebv: FormControl<IMachine['marquebv']>;
  typboite: FormControl<IMachine['typboite']>;
};

export type MachineFormGroup = FormGroup<MachineFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MachineFormService {
  createMachineFormGroup(machine: MachineFormGroupInput = { id: null }): MachineFormGroup {
    const machineRawValue = {
      ...this.getFormDefaults(),
      ...machine,
    };
    return new FormGroup<MachineFormGroupContent>({
      id: new FormControl(
        { value: machineRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdmac: new FormControl(machineRawValue.cdmac),
      cdmod: new FormControl(machineRawValue.cdmod),
      cdmarque: new FormControl(machineRawValue.cdmarque),
      lbmac: new FormControl(machineRawValue.lbmac),
      refmac: new FormControl(machineRawValue.refmac),
      serie: new FormControl(machineRawValue.serie),
      datfab: new FormControl(machineRawValue.datfab),
      datacq: new FormControl(machineRawValue.datacq),
      datmes: new FormControl(machineRawValue.datmes),
      valacq: new FormControl(machineRawValue.valacq),
      obs: new FormControl(machineRawValue.obs),
      numplan: new FormControl(machineRawValue.numplan),
      cdlipro: new FormControl(machineRawValue.cdlipro),
      immat: new FormControl(machineRawValue.immat),
      marque: new FormControl(machineRawValue.marque),
      typev: new FormControl(machineRawValue.typev),
      numser: new FormControl(machineRawValue.numser),
      puiss: new FormControl(machineRawValue.puiss),
      nrj: new FormControl(machineRawValue.nrj),
      genre: new FormControl(machineRawValue.genre),
      cylind: new FormControl(machineRawValue.cylind),
      pdsvid: new FormControl(machineRawValue.pdsvid),
      charge: new FormControl(machineRawValue.charge),
      plcass: new FormControl(machineRawValue.plcass),
      plcdeb: new FormControl(machineRawValue.plcdeb),
      cpt: new FormControl(machineRawValue.cpt),
      cptmnt: new FormControl(machineRawValue.cptmnt),
      actif: new FormControl(machineRawValue.actif),
      datact: new FormControl(machineRawValue.datact),
      cdcatvh: new FormControl(machineRawValue.cdcatvh),
      taux: new FormControl(machineRawValue.taux),
      kmmoy: new FormControl(machineRawValue.kmmoy),
      codstat: new FormControl(machineRawValue.codstat),
      edition: new FormControl(machineRawValue.edition),
      valassur: new FormControl(machineRawValue.valassur),
      valamort: new FormControl(machineRawValue.valamort),
      consommodel: new FormControl(machineRawValue.consommodel),
      decetat: new FormControl(machineRawValue.decetat),
      codtypvoit: new FormControl(machineRawValue.codtypvoit),
      cdtyp: new FormControl(machineRawValue.cdtyp),
      cdnat: new FormControl(machineRawValue.cdnat),
      typbv: new FormControl(machineRawValue.typbv),
      cdtypbv: new FormControl(machineRawValue.cdtypbv),
      pneu: new FormControl(machineRawValue.pneu),
      gps: new FormControl(machineRawValue.gps),
      marquebv: new FormControl(machineRawValue.marquebv),
      typboite: new FormControl(machineRawValue.typboite),
    });
  }

  getMachine(form: MachineFormGroup): IMachine | NewMachine {
    return form.getRawValue() as IMachine | NewMachine;
  }

  resetForm(form: MachineFormGroup, machine: MachineFormGroupInput): void {
    const machineRawValue = { ...this.getFormDefaults(), ...machine };
    form.reset(
      {
        ...machineRawValue,
        id: { value: machineRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MachineFormDefaults {
    return {
      id: null,
    };
  }
}
