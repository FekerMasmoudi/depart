import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAgence, NewAgence } from '../agence.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAgence for edit and NewAgenceFormGroupInput for create.
 */
type AgenceFormGroupInput = IAgence | PartialWithRequiredKeyOf<NewAgence>;

type AgenceFormDefaults = Pick<NewAgence, 'id'>;

type AgenceFormGroupContent = {
  id: FormControl<IAgence['id'] | NewAgence['id']>;
  deccent: FormControl<IAgence['deccent']>;
  decagenc: FormControl<IAgence['decagenc']>;
  delagenc: FormControl<IAgence['delagenc']>;
  defaultagenc: FormControl<IAgence['defaultagenc']>;
};

export type AgenceFormGroup = FormGroup<AgenceFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AgenceFormService {
  createAgenceFormGroup(agence: AgenceFormGroupInput = { id: null }): AgenceFormGroup {
    const agenceRawValue = {
      ...this.getFormDefaults(),
      ...agence,
    };
    return new FormGroup<AgenceFormGroupContent>({
      id: new FormControl(
        { value: agenceRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(agenceRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(agenceRawValue.decagenc, {
        validators: [Validators.required],
      }),
      delagenc: new FormControl(agenceRawValue.delagenc),
      defaultagenc: new FormControl(agenceRawValue.defaultagenc),
    });
  }

  getAgence(form: AgenceFormGroup): IAgence | NewAgence {
    return form.getRawValue() as IAgence | NewAgence;
  }

  resetForm(form: AgenceFormGroup, agence: AgenceFormGroupInput): void {
    const agenceRawValue = { ...this.getFormDefaults(), ...agence };
    form.reset(
      {
        ...agenceRawValue,
        id: { value: agenceRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AgenceFormDefaults {
    return {
      id: null,
    };
  }
}
