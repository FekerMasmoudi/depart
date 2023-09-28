import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDepart, NewDepart } from '../depart.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDepart for edit and NewDepartFormGroupInput for create.
 */
type DepartFormGroupInput = IDepart | PartialWithRequiredKeyOf<NewDepart>;

type DepartFormDefaults = Pick<NewDepart, 'id'>;

type DepartFormGroupContent = {
  id: FormControl<IDepart['id'] | NewDepart['id']>;
  deccent: FormControl<IDepart['deccent']>;
  decagenc: FormControl<IDepart['decagenc']>;
  decserv: FormControl<IDepart['decserv']>;
  decoper: FormControl<IDepart['decoper']>;
  decsean: FormControl<IDepart['decsean']>;
  dedated: FormControl<IDepart['dedated']>;
  denumdp: FormControl<IDepart['denumdp']>;
  matric: FormControl<IDepart['matric']>;
  matric1: FormControl<IDepart['matric1']>;
  cdmac: FormControl<IDepart['cdmac']>;
  deheups: FormControl<IDepart['deheups']>;
  deheufs: FormControl<IDepart['deheufs']>;
  denbrro: FormControl<IDepart['denbrro']>;
  deheuaa: FormControl<IDepart['deheuaa']>;
  deheudr: FormControl<IDepart['deheudr']>;
  deheupd: FormControl<IDepart['deheupd']>;
  deampli: FormControl<IDepart['deampli']>;
  obsind: FormControl<IDepart['obsind']>;
  vldroul: FormControl<IDepart['vldroul']>;
  deetat: FormControl<IDepart['deetat']>;
  deannul: FormControl<IDepart['deannul']>;
  decclot: FormControl<IDepart['decclot']>;
  execute: FormControl<IDepart['execute']>;
  motifa: FormControl<IDepart['motif_a']>;
  observ: FormControl<IDepart['observ']>;
  recettes: FormControl<IDepart['recettes']>;
  nbrevoy: FormControl<IDepart['nbrevoy']>;
  decmotifch: FormControl<IDepart['decmotifch']>;
  decmotifre: FormControl<IDepart['decmotifre']>;
  cd1: FormControl<IDepart['cd1']>;
  cd2: FormControl<IDepart['cd2']>;
  cd3: FormControl<IDepart['cd3']>;
  decmotifcha: FormControl<IDepart['decmotifcha']>;
  decmotifrea: FormControl<IDepart['decmotifrea']>;
};

export type DepartFormGroup = FormGroup<DepartFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DepartFormService {
  createDepartFormGroup(depart: DepartFormGroupInput = { id: null }): DepartFormGroup {
    const departRawValue = {
      ...this.getFormDefaults(),
      ...depart,
    };
    return new FormGroup<DepartFormGroupContent>({
      id: new FormControl(
        { value: departRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(departRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(departRawValue.decagenc, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(departRawValue.decserv, {
        validators: [Validators.required],
      }),
      decoper: new FormControl(departRawValue.decoper, {
        validators: [Validators.required],
      }),
      decsean: new FormControl(departRawValue.decsean, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(departRawValue.dedated, {
        validators: [Validators.required],
      }),
      denumdp: new FormControl(departRawValue.denumdp, {
        validators: [Validators.required],
      }),
      matric: new FormControl(departRawValue.matric),
      matric1: new FormControl(departRawValue.matric1),
      cdmac: new FormControl(departRawValue.cdmac),
      deheups: new FormControl(departRawValue.deheups),
      deheufs: new FormControl(departRawValue.deheufs),
      denbrro: new FormControl(departRawValue.denbrro),
      deheuaa: new FormControl(departRawValue.deheuaa),
      deheudr: new FormControl(departRawValue.deheudr),
      deheupd: new FormControl(departRawValue.deheupd),
      deampli: new FormControl(departRawValue.deampli),
      obsind: new FormControl(departRawValue.obsind),
      vldroul: new FormControl(departRawValue.vldroul),
      deetat: new FormControl(departRawValue.deetat),
      deannul: new FormControl(departRawValue.deannul),
      decclot: new FormControl(departRawValue.decclot),
      execute: new FormControl(departRawValue.execute),
      motifa: new FormControl(departRawValue.motif_a),
      observ: new FormControl(departRawValue.observ),
      recettes: new FormControl(departRawValue.recettes),
      nbrevoy: new FormControl(departRawValue.nbrevoy),
      decmotifch: new FormControl(departRawValue.decmotifch),
      decmotifre: new FormControl(departRawValue.decmotifre),
      cd1: new FormControl(departRawValue.cd1),
      cd2: new FormControl(departRawValue.cd2),
      cd3: new FormControl(departRawValue.cd3),
      decmotifcha: new FormControl(departRawValue.decmotifcha),
      decmotifrea: new FormControl(departRawValue.decmotifrea),
    });
  }

  getDepart(form: DepartFormGroup): IDepart | NewDepart {
    return form.getRawValue() as IDepart | NewDepart;
  }

  resetForm(form: DepartFormGroup, depart: DepartFormGroupInput): void {
    const departRawValue = { ...this.getFormDefaults(), ...depart };
    form.reset(
      {
        ...departRawValue,
        id: { value: departRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DepartFormDefaults {
    return {
      id: null,
    };
  }
}
