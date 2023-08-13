import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITypStat, NewTypStat } from '../typ-stat.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITypStat for edit and NewTypStatFormGroupInput for create.
 */
type TypStatFormGroupInput = ITypStat | PartialWithRequiredKeyOf<NewTypStat>;

type TypStatFormDefaults = Pick<NewTypStat, 'id'>;

type TypStatFormGroupContent = {
  id: FormControl<ITypStat['id'] | NewTypStat['id']>;
  dectyst: FormControl<ITypStat['dectyst']>;
  deltyst: FormControl<ITypStat['deltyst']>;
};

export type TypStatFormGroup = FormGroup<TypStatFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TypStatFormService {
  createTypStatFormGroup(typStat: TypStatFormGroupInput = { id: null }): TypStatFormGroup {
    const typStatRawValue = {
      ...this.getFormDefaults(),
      ...typStat,
    };
    return new FormGroup<TypStatFormGroupContent>({
      id: new FormControl(
        { value: typStatRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      dectyst: new FormControl(typStatRawValue.dectyst, {
        validators: [Validators.required],
      }),
      deltyst: new FormControl(typStatRawValue.deltyst),
    });
  }

  getTypStat(form: TypStatFormGroup): ITypStat | NewTypStat {
    return form.getRawValue() as ITypStat | NewTypStat;
  }

  resetForm(form: TypStatFormGroup, typStat: TypStatFormGroupInput): void {
    const typStatRawValue = { ...this.getFormDefaults(), ...typStat };
    form.reset(
      {
        ...typStatRawValue,
        id: { value: typStatRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TypStatFormDefaults {
    return {
      id: null,
    };
  }
}
