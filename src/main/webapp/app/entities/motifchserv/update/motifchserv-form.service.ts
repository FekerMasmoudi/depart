import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IMotifchserv, NewMotifchserv } from '../motifchserv.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IMotifchserv for edit and NewMotifchservFormGroupInput for create.
 */
type MotifchservFormGroupInput = IMotifchserv | PartialWithRequiredKeyOf<NewMotifchserv>;

type MotifchservFormDefaults = Pick<NewMotifchserv, 'id'>;

type MotifchservFormGroupContent = {
  id: FormControl<IMotifchserv['id'] | NewMotifchserv['id']>;
  decmotif: FormControl<IMotifchserv['decmotif']>;
  delmotif: FormControl<IMotifchserv['delmotif']>;
  x: FormControl<IMotifchserv['x']>;
  vs: FormControl<IMotifchserv['vs']>;
};

export type MotifchservFormGroup = FormGroup<MotifchservFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class MotifchservFormService {
  createMotifchservFormGroup(motifchserv: MotifchservFormGroupInput = { id: null }): MotifchservFormGroup {
    const motifchservRawValue = {
      ...this.getFormDefaults(),
      ...motifchserv,
    };
    return new FormGroup<MotifchservFormGroupContent>({
      id: new FormControl(
        { value: motifchservRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      decmotif: new FormControl(motifchservRawValue.decmotif, {
        validators: [Validators.required],
      }),
      delmotif: new FormControl(motifchservRawValue.delmotif),
      x: new FormControl(motifchservRawValue.x),
      vs: new FormControl(motifchservRawValue.vs),
    });
  }

  getMotifchserv(form: MotifchservFormGroup): IMotifchserv | NewMotifchserv {
    return form.getRawValue() as IMotifchserv | NewMotifchserv;
  }

  resetForm(form: MotifchservFormGroup, motifchserv: MotifchservFormGroupInput): void {
    const motifchservRawValue = { ...this.getFormDefaults(), ...motifchserv };
    form.reset(
      {
        ...motifchservRawValue,
        id: { value: motifchservRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): MotifchservFormDefaults {
    return {
      id: null,
    };
  }
}
