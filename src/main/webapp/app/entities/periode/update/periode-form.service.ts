import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IPeriode, NewPeriode } from '../periode.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPeriode for edit and NewPeriodeFormGroupInput for create.
 */
type PeriodeFormGroupInput = IPeriode | PartialWithRequiredKeyOf<NewPeriode>;

type PeriodeFormDefaults = Pick<NewPeriode, 'id'>;

type PeriodeFormGroupContent = {
  id: FormControl<IPeriode['id'] | NewPeriode['id']>;
  decoper: FormControl<IPeriode['decoper']>;
  denoper: FormControl<IPeriode['denoper']>;
  primaire: FormControl<IPeriode['primaire']>;
  startdate: FormControl<IPeriode['startdate']>;
  enddate: FormControl<IPeriode['enddate']>;
};

export type PeriodeFormGroup = FormGroup<PeriodeFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PeriodeFormService {
  createPeriodeFormGroup(periode: PeriodeFormGroupInput = { id: null }): PeriodeFormGroup {
    const periodeRawValue = {
      ...this.getFormDefaults(),
      ...periode,
    };
    return new FormGroup<PeriodeFormGroupContent>({
      id: new FormControl(
        { value: periodeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      decoper: new FormControl(periodeRawValue.decoper),
      denoper: new FormControl(periodeRawValue.denoper),
      primaire: new FormControl(periodeRawValue.primaire),
      startdate: new FormControl(periodeRawValue.startdate),
      enddate: new FormControl(periodeRawValue.enddate),
    });
  }

  getPeriode(form: PeriodeFormGroup): IPeriode | NewPeriode {
    return form.getRawValue() as IPeriode | NewPeriode;
  }

  resetForm(form: PeriodeFormGroup, periode: PeriodeFormGroupInput): void {
    const periodeRawValue = { ...this.getFormDefaults(), ...periode };
    form.reset(
      {
        ...periodeRawValue,
        id: { value: periodeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): PeriodeFormDefaults {
    return {
      id: null,
    };
  }
}
