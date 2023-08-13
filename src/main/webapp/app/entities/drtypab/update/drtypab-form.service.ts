import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDrtypab, NewDrtypab } from '../drtypab.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDrtypab for edit and NewDrtypabFormGroupInput for create.
 */
type DrtypabFormGroupInput = IDrtypab | PartialWithRequiredKeyOf<NewDrtypab>;

type DrtypabFormDefaults = Pick<NewDrtypab, 'id'>;

type DrtypabFormGroupContent = {
  id: FormControl<IDrtypab['id'] | NewDrtypab['id']>;
  cdtypab: FormControl<IDrtypab['cdtypab']>;
  lbtypab: FormControl<IDrtypab['lbtypab']>;
  dabsjt: FormControl<IDrtypab['dabsjt']>;
  dabsjp: FormControl<IDrtypab['dabsjp']>;
};

export type DrtypabFormGroup = FormGroup<DrtypabFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DrtypabFormService {
  createDrtypabFormGroup(drtypab: DrtypabFormGroupInput = { id: null }): DrtypabFormGroup {
    const drtypabRawValue = {
      ...this.getFormDefaults(),
      ...drtypab,
    };
    return new FormGroup<DrtypabFormGroupContent>({
      id: new FormControl(
        { value: drtypabRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdtypab: new FormControl(drtypabRawValue.cdtypab),
      lbtypab: new FormControl(drtypabRawValue.lbtypab),
      dabsjt: new FormControl(drtypabRawValue.dabsjt),
      dabsjp: new FormControl(drtypabRawValue.dabsjp),
    });
  }

  getDrtypab(form: DrtypabFormGroup): IDrtypab | NewDrtypab {
    return form.getRawValue() as IDrtypab | NewDrtypab;
  }

  resetForm(form: DrtypabFormGroup, drtypab: DrtypabFormGroupInput): void {
    const drtypabRawValue = { ...this.getFormDefaults(), ...drtypab };
    form.reset(
      {
        ...drtypabRawValue,
        id: { value: drtypabRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DrtypabFormDefaults {
    return {
      id: null,
    };
  }
}
