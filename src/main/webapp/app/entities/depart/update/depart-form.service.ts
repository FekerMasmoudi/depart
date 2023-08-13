import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
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

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDepart | NewDepart> = Omit<T, 'deheups' | 'deheufs' | 'deheuaa' | 'deheudr' | 'deheupd' | 'deampli'> & {
  deheups?: string | null;
  deheufs?: string | null;
  deheuaa?: string | null;
  deheudr?: string | null;
  deheupd?: string | null;
  deampli?: string | null;
};

type DepartFormRawValue = FormValueOf<IDepart>;

type NewDepartFormRawValue = FormValueOf<NewDepart>;

type DepartFormDefaults = Pick<NewDepart, 'id' | 'deheups' | 'deheufs' | 'deheuaa' | 'deheudr' | 'deheupd' | 'deampli'>;

type DepartFormGroupContent = {
  id: FormControl<DepartFormRawValue['id'] | NewDepart['id']>;
  deccent: FormControl<DepartFormRawValue['deccent']>;
  decagenc: FormControl<DepartFormRawValue['decagenc']>;
  decserv: FormControl<DepartFormRawValue['decserv']>;
  decoper: FormControl<DepartFormRawValue['decoper']>;
  decsean: FormControl<DepartFormRawValue['decsean']>;
  dedated: FormControl<DepartFormRawValue['dedated']>;
  denumdp: FormControl<DepartFormRawValue['denumdp']>;
  matric: FormControl<DepartFormRawValue['matric']>;
  matric1: FormControl<DepartFormRawValue['matric1']>;
  cdmac: FormControl<DepartFormRawValue['cdmac']>;
  deheups: FormControl<DepartFormRawValue['deheups']>;
  deheufs: FormControl<DepartFormRawValue['deheufs']>;
  denbrro: FormControl<DepartFormRawValue['denbrro']>;
  deheuaa: FormControl<DepartFormRawValue['deheuaa']>;
  deheudr: FormControl<DepartFormRawValue['deheudr']>;
  deheupd: FormControl<DepartFormRawValue['deheupd']>;
  deampli: FormControl<DepartFormRawValue['deampli']>;
  obsind: FormControl<DepartFormRawValue['obsind']>;
  vldroul: FormControl<DepartFormRawValue['vldroul']>;
  deetat: FormControl<DepartFormRawValue['deetat']>;
  deannul: FormControl<DepartFormRawValue['deannul']>;
  decclot: FormControl<DepartFormRawValue['decclot']>;
  execute: FormControl<DepartFormRawValue['execute']>;
  motifa: FormControl<DepartFormRawValue['motifa']>;
  observ: FormControl<DepartFormRawValue['observ']>;
  recettes: FormControl<DepartFormRawValue['recettes']>;
  nbrevoy: FormControl<DepartFormRawValue['nbrevoy']>;
  decmotifch: FormControl<DepartFormRawValue['decmotifch']>;
  decmotifre: FormControl<DepartFormRawValue['decmotifre']>;
  cd1: FormControl<DepartFormRawValue['cd1']>;
  cd2: FormControl<DepartFormRawValue['cd2']>;
  cd3: FormControl<DepartFormRawValue['cd3']>;
  decmotifcha: FormControl<DepartFormRawValue['decmotifcha']>;
  decmotifrea: FormControl<DepartFormRawValue['decmotifrea']>;
};

export type DepartFormGroup = FormGroup<DepartFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DepartFormService {
  createDepartFormGroup(depart: DepartFormGroupInput = { id: null }): DepartFormGroup {
    const departRawValue = this.convertDepartToDepartRawValue({
      ...this.getFormDefaults(),
      ...depart,
    });
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
      motifa: new FormControl(departRawValue.motifa),
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
    return this.convertDepartRawValueToDepart(form.getRawValue() as DepartFormRawValue | NewDepartFormRawValue);
  }

  resetForm(form: DepartFormGroup, depart: DepartFormGroupInput): void {
    const departRawValue = this.convertDepartToDepartRawValue({ ...this.getFormDefaults(), ...depart });
    form.reset(
      {
        ...departRawValue,
        id: { value: departRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DepartFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      deheups: currentTime,
      deheufs: currentTime,
      deheuaa: currentTime,
      deheudr: currentTime,
      deheupd: currentTime,
      deampli: currentTime,
    };
  }

  private convertDepartRawValueToDepart(rawDepart: DepartFormRawValue | NewDepartFormRawValue): IDepart | NewDepart {
    return {
      ...rawDepart,
      deheups: dayjs(rawDepart.deheups, DATE_TIME_FORMAT),
      deheufs: dayjs(rawDepart.deheufs, DATE_TIME_FORMAT),
      deheuaa: dayjs(rawDepart.deheuaa, DATE_TIME_FORMAT),
      deheudr: dayjs(rawDepart.deheudr, DATE_TIME_FORMAT),
      deheupd: dayjs(rawDepart.deheupd, DATE_TIME_FORMAT),
      deampli: dayjs(rawDepart.deampli, DATE_TIME_FORMAT),
    };
  }

  private convertDepartToDepartRawValue(
    depart: IDepart | (Partial<NewDepart> & DepartFormDefaults)
  ): DepartFormRawValue | PartialWithRequiredKeyOf<NewDepartFormRawValue> {
    return {
      ...depart,
      deheups: depart.deheups ? depart.deheups.format(DATE_TIME_FORMAT) : undefined,
      deheufs: depart.deheufs ? depart.deheufs.format(DATE_TIME_FORMAT) : undefined,
      deheuaa: depart.deheuaa ? depart.deheuaa.format(DATE_TIME_FORMAT) : undefined,
      deheudr: depart.deheudr ? depart.deheudr.format(DATE_TIME_FORMAT) : undefined,
      deheupd: depart.deheupd ? depart.deheupd.format(DATE_TIME_FORMAT) : undefined,
      deampli: depart.deampli ? depart.deampli.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
