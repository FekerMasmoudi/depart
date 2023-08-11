import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMotifa, NewMotifa } from '../motifa.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMotifa for edit and NewMotifaFormGroupInput for create.
 */
type MotifaFormGroupInput = IMotifa | PartialWithRequiredKeyOf<NewMotifa>;

type MotifaFormDefaults = Pick<NewMotifa, 'id'>;

type MotifaFormGroupContent = {
  id: FormControl<IMotifa['id'] | NewMotifa['id']>;
  decmotif: FormControl<IMotifa['decmotif']>;
  libmotif: FormControl<IMotifa['libmotif']>;
};

export type MotifaFormGroup = FormGroup<MotifaFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MotifaFormService {
  createMotifaFormGroup(motifa: MotifaFormGroupInput = { id: null }): MotifaFormGroup {
    const motifaRawValue = {
      ...this.getFormDefaults(),
      ...motifa,
    };
    return new FormGroup<MotifaFormGroupContent>({
      id: new FormControl(
        { value: motifaRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      decmotif: new FormControl(motifaRawValue.decmotif, {
        validators: [Validators.required],
      }),
      libmotif: new FormControl(motifaRawValue.libmotif),
    });
  }

  getMotifa(form: MotifaFormGroup): IMotifa | NewMotifa {
    return form.getRawValue() as IMotifa | NewMotifa;
  }

  resetForm(form: MotifaFormGroup, motifa: MotifaFormGroupInput): void {
    const motifaRawValue = { ...this.getFormDefaults(), ...motifa };
    form.reset(
      {
        ...motifaRawValue,
        id: { value: motifaRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MotifaFormDefaults {
    return {
      id: null,
    };
  }
}
