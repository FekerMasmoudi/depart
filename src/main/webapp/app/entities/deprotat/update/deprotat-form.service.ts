import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDeprotat, NewDeprotat } from '../deprotat.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDeprotat for edit and NewDeprotatFormGroupInput for create.
 */
type DeprotatFormGroupInput = IDeprotat | PartialWithRequiredKeyOf<NewDeprotat>;

type DeprotatFormDefaults = Pick<NewDeprotat, 'id'>;

type DeprotatFormGroupContent = {
  id: FormControl<IDeprotat['id'] | NewDeprotat['id']>;
  deccent: FormControl<IDeprotat['deccent']>;
  decagenc: FormControl<IDeprotat['decagenc']>;
  dedated: FormControl<IDeprotat['dedated']>;
  denumdp: FormControl<IDeprotat['denumdp']>;
  decserv: FormControl<IDeprotat['decserv']>;
  decoper: FormControl<IDeprotat['decoper']>;
  decsean: FormControl<IDeprotat['decsean']>;
  numrotat: FormControl<IDeprotat['numrotat']>;
  ligdeccent: FormControl<IDeprotat['ligdeccent']>;
  ligdecagenc: FormControl<IDeprotat['ligdecagenc']>;
  denumli: FormControl<IDeprotat['denumli']>;
  decstat: FormControl<IDeprotat['decstat']>;
  decsta1: FormControl<IDeprotat['decsta1']>;
  matric: FormControl<IDeprotat['matric']>;
  matric1: FormControl<IDeprotat['matric1']>;
  cdmac: FormControl<IDeprotat['cdmac']>;
  hdeparte: FormControl<IDeprotat['hdeparte']>;
  hretoure: FormControl<IDeprotat['hretoure']>;
  harralle: FormControl<IDeprotat['harralle']>;
  harrrete: FormControl<IDeprotat['harrrete']>;
  rannul: FormControl<IDeprotat['rannul']>;
  km: FormControl<IDeprotat['km']>;
  motifa: FormControl<IDeprotat['motifa']>;
  observ: FormControl<IDeprotat['observ']>;
  recettesvoy: FormControl<IDeprotat['recettesvoy']>;
  nbrevoy: FormControl<IDeprotat['nbrevoy']>;
  paye: FormControl<IDeprotat['paye']>;
  cd1: FormControl<IDeprotat['cd1']>;
  cd2: FormControl<IDeprotat['cd2']>;
  cd3: FormControl<IDeprotat['cd3']>;
  decmotifcha: FormControl<IDeprotat['decmotifcha']>;
  decmotifrea: FormControl<IDeprotat['decmotifrea']>;
  idapex: FormControl<IDeprotat['idapex']>;
  plusmoins: FormControl<IDeprotat['plusmoins']>;
  a: FormControl<IDeprotat['a']>;
  r: FormControl<IDeprotat['r']>;
  depart: FormControl<IDeprotat['depart']>;
};

export type DeprotatFormGroup = FormGroup<DeprotatFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DeprotatFormService {
  createDeprotatFormGroup(deprotat: DeprotatFormGroupInput = { id: null }): DeprotatFormGroup {
    const deprotatRawValue = {
      ...this.getFormDefaults(),
      ...deprotat,
    };
    return new FormGroup<DeprotatFormGroupContent>({
      id: new FormControl(
        { value: deprotatRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(deprotatRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(deprotatRawValue.decagenc, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(deprotatRawValue.dedated, {
        validators: [Validators.required],
      }),
      denumdp: new FormControl(deprotatRawValue.denumdp, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(deprotatRawValue.decserv, {
        validators: [Validators.required],
      }),
      decoper: new FormControl(deprotatRawValue.decoper, {
        validators: [Validators.required],
      }),
      decsean: new FormControl(deprotatRawValue.decsean, {
        validators: [Validators.required],
      }),
      numrotat: new FormControl(deprotatRawValue.numrotat),
      ligdeccent: new FormControl(deprotatRawValue.ligdeccent),
      ligdecagenc: new FormControl(deprotatRawValue.ligdecagenc),
      denumli: new FormControl(deprotatRawValue.denumli),
      decstat: new FormControl(deprotatRawValue.decstat),
      decsta1: new FormControl(deprotatRawValue.decsta1),
      matric: new FormControl(deprotatRawValue.matric),
      matric1: new FormControl(deprotatRawValue.matric1),
      cdmac: new FormControl(deprotatRawValue.cdmac),
      hdeparte: new FormControl(deprotatRawValue.hdeparte),
      hretoure: new FormControl(deprotatRawValue.hretoure),
      harralle: new FormControl(deprotatRawValue.harralle),
      harrrete: new FormControl(deprotatRawValue.harrrete),
      rannul: new FormControl(deprotatRawValue.rannul),
      km: new FormControl(deprotatRawValue.km),
      motifa: new FormControl(deprotatRawValue.motifa),
      observ: new FormControl(deprotatRawValue.observ),
      recettesvoy: new FormControl(deprotatRawValue.recettesvoy),
      nbrevoy: new FormControl(deprotatRawValue.nbrevoy),
      paye: new FormControl(deprotatRawValue.paye),
      cd1: new FormControl(deprotatRawValue.cd1),
      cd2: new FormControl(deprotatRawValue.cd2),
      cd3: new FormControl(deprotatRawValue.cd3),
      decmotifcha: new FormControl(deprotatRawValue.decmotifcha),
      decmotifrea: new FormControl(deprotatRawValue.decmotifrea),
      idapex: new FormControl(deprotatRawValue.idapex),
      plusmoins: new FormControl(deprotatRawValue.plusmoins),
      a: new FormControl(deprotatRawValue.a),
      r: new FormControl(deprotatRawValue.r),
      depart: new FormControl(deprotatRawValue.depart),
    });
  }

  getDeprotat(form: DeprotatFormGroup): IDeprotat | NewDeprotat {
    return form.getRawValue() as IDeprotat | NewDeprotat;
  }

  resetForm(form: DeprotatFormGroup, deprotat: DeprotatFormGroupInput): void {
    const deprotatRawValue = { ...this.getFormDefaults(), ...deprotat };
    form.reset(
      {
        ...deprotatRawValue,
        id: { value: deprotatRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DeprotatFormDefaults {
    return {
      id: null,
    };
  }
}
