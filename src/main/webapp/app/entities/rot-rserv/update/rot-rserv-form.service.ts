import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IRotRserv, NewRotRserv } from '../rot-rserv.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRotRserv for edit and NewRotRservFormGroupInput for create.
 */
type RotRservFormGroupInput = IRotRserv | PartialWithRequiredKeyOf<NewRotRserv>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IRotRserv | NewRotRserv> = Omit<T, 'heurdeb' | 'heurfin' | 'lieedeb' | 'lieefin'> & {
  heurdeb?: string | null;
  heurfin?: string | null;
  lieedeb?: string | null;
  lieefin?: string | null;
};

type RotRservFormRawValue = FormValueOf<IRotRserv>;

type NewRotRservFormRawValue = FormValueOf<NewRotRserv>;

type RotRservFormDefaults = Pick<NewRotRserv, 'heurdeb' | 'heurfin' | 'lieedeb' | 'lieefin' | 'id'>;

type RotRservFormGroupContent = {
  deccent: FormControl<RotRservFormRawValue['deccent']>;
  decagenc: FormControl<RotRservFormRawValue['decagenc']>;
  dedated: FormControl<RotRservFormRawValue['dedated']>;
  matric: FormControl<RotRservFormRawValue['matric']>;
  heurdeb: FormControl<RotRservFormRawValue['heurdeb']>;
  heurfin: FormControl<RotRservFormRawValue['heurfin']>;
  statut: FormControl<RotRservFormRawValue['statut']>;
  lieedeb: FormControl<RotRservFormRawValue['lieedeb']>;
  lieefin: FormControl<RotRservFormRawValue['lieefin']>;
  program: FormControl<RotRservFormRawValue['program']>;
  cd1: FormControl<RotRservFormRawValue['cd1']>;
  cd2: FormControl<RotRservFormRawValue['cd2']>;
  cd3: FormControl<RotRservFormRawValue['cd3']>;
  id: FormControl<RotRservFormRawValue['id'] | NewRotRserv['id']>;
  annul: FormControl<RotRservFormRawValue['annul']>;
};

export type RotRservFormGroup = FormGroup<RotRservFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RotRservFormService {
  createRotRservFormGroup(rotRserv: RotRservFormGroupInput = { id: null }): RotRservFormGroup {
    const rotRservRawValue = this.convertRotRservToRotRservRawValue({
      ...this.getFormDefaults(),
      ...rotRserv,
    });
    return new FormGroup<RotRservFormGroupContent>({
      deccent: new FormControl(rotRservRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(rotRservRawValue.decagenc, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(rotRservRawValue.dedated, {
        validators: [Validators.required],
      }),
      matric: new FormControl(rotRservRawValue.matric, {
        validators: [Validators.required],
      }),
      heurdeb: new FormControl(rotRservRawValue.heurdeb, {
        validators: [Validators.required],
      }),
      heurfin: new FormControl(rotRservRawValue.heurfin, {
        validators: [Validators.required],
      }),
      statut: new FormControl(rotRservRawValue.statut),
      lieedeb: new FormControl(rotRservRawValue.lieedeb),
      lieefin: new FormControl(rotRservRawValue.lieefin),
      program: new FormControl(rotRservRawValue.program),
      cd1: new FormControl(rotRservRawValue.cd1),
      cd2: new FormControl(rotRservRawValue.cd2),
      cd3: new FormControl(rotRservRawValue.cd3),
      id: new FormControl(
        { value: rotRservRawValue.id, disabled: rotRservRawValue.id !== null },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      annul: new FormControl(rotRservRawValue.annul),
    });
  }

  getRotRserv(form: RotRservFormGroup): IRotRserv | NewRotRserv {
    return this.convertRotRservRawValueToRotRserv(form.getRawValue() as RotRservFormRawValue | NewRotRservFormRawValue);
  }

  resetForm(form: RotRservFormGroup, rotRserv: RotRservFormGroupInput): void {
    const rotRservRawValue = this.convertRotRservToRotRservRawValue({ ...this.getFormDefaults(), ...rotRserv });
    form.reset(
      {
        ...rotRservRawValue,
        id: { value: rotRservRawValue.id, disabled: rotRservRawValue.id !== null },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RotRservFormDefaults {
    const currentTime = dayjs();

    return {
      heurdeb: currentTime,
      heurfin: currentTime,
      lieedeb: currentTime,
      lieefin: currentTime,
      id: null,
    };
  }

  private convertRotRservRawValueToRotRserv(rawRotRserv: RotRservFormRawValue | NewRotRservFormRawValue): IRotRserv | NewRotRserv {
    return {
      ...rawRotRserv,
      heurdeb: dayjs(rawRotRserv.heurdeb, DATE_TIME_FORMAT),
      heurfin: dayjs(rawRotRserv.heurfin, DATE_TIME_FORMAT),
      lieedeb: dayjs(rawRotRserv.lieedeb, DATE_TIME_FORMAT),
      lieefin: dayjs(rawRotRserv.lieefin, DATE_TIME_FORMAT),
    };
  }

  private convertRotRservToRotRservRawValue(
    rotRserv: IRotRserv | (Partial<NewRotRserv> & RotRservFormDefaults)
  ): RotRservFormRawValue | PartialWithRequiredKeyOf<NewRotRservFormRawValue> {
    return {
      ...rotRserv,
      heurdeb: rotRserv.heurdeb ? rotRserv.heurdeb.format(DATE_TIME_FORMAT) : undefined,
      heurfin: rotRserv.heurfin ? rotRserv.heurfin.format(DATE_TIME_FORMAT) : undefined,
      lieedeb: rotRserv.lieedeb ? rotRserv.lieedeb.format(DATE_TIME_FORMAT) : undefined,
      lieefin: rotRserv.lieefin ? rotRserv.lieefin.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
