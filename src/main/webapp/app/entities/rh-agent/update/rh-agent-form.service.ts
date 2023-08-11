import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRhAgent, NewRhAgent } from '../rh-agent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRhAgent for edit and NewRhAgentFormGroupInput for create.
 */
type RhAgentFormGroupInput = IRhAgent | PartialWithRequiredKeyOf<NewRhAgent>;

type RhAgentFormDefaults = Pick<NewRhAgent, 'id'>;

type RhAgentFormGroupContent = {
  id: FormControl<IRhAgent['id'] | NewRhAgent['id']>;
  matric: FormControl<IRhAgent['matric']>;
  decjour: FormControl<IRhAgent['decjour']>;
  dateffrh: FormControl<IRhAgent['dateffrh']>;
  decoper: FormControl<IRhAgent['decoper']>;
  deccent: FormControl<IRhAgent['deccent']>;
  decagenc: FormControl<IRhAgent['decagenc']>;
};

export type RhAgentFormGroup = FormGroup<RhAgentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RhAgentFormService {
  createRhAgentFormGroup(rhAgent: RhAgentFormGroupInput = { id: null }): RhAgentFormGroup {
    const rhAgentRawValue = {
      ...this.getFormDefaults(),
      ...rhAgent,
    };
    return new FormGroup<RhAgentFormGroupContent>({
      id: new FormControl(
        { value: rhAgentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      matric: new FormControl(rhAgentRawValue.matric),
      decjour: new FormControl(rhAgentRawValue.decjour),
      dateffrh: new FormControl(rhAgentRawValue.dateffrh),
      decoper: new FormControl(rhAgentRawValue.decoper),
      deccent: new FormControl(rhAgentRawValue.deccent),
      decagenc: new FormControl(rhAgentRawValue.decagenc),
    });
  }

  getRhAgent(form: RhAgentFormGroup): IRhAgent | NewRhAgent {
    return form.getRawValue() as IRhAgent | NewRhAgent;
  }

  resetForm(form: RhAgentFormGroup, rhAgent: RhAgentFormGroupInput): void {
    const rhAgentRawValue = { ...this.getFormDefaults(), ...rhAgent };
    form.reset(
      {
        ...rhAgentRawValue,
        id: { value: rhAgentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RhAgentFormDefaults {
    return {
      id: null,
    };
  }
}
