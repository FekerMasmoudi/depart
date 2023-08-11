import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAffecAgent, NewAffecAgent } from '../affec-agent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAffecAgent for edit and NewAffecAgentFormGroupInput for create.
 */
type AffecAgentFormGroupInput = IAffecAgent | PartialWithRequiredKeyOf<NewAffecAgent>;

type AffecAgentFormDefaults = Pick<NewAffecAgent, 'id'>;

type AffecAgentFormGroupContent = {
  id: FormControl<IAffecAgent['id'] | NewAffecAgent['id']>;
  deccent: FormControl<IAffecAgent['deccent']>;
  decagenc: FormControl<IAffecAgent['decagenc']>;
  decserv: FormControl<IAffecAgent['decserv']>;
  decoper: FormControl<IAffecAgent['decoper']>;
  decsean: FormControl<IAffecAgent['decsean']>;
  cdsocie: FormControl<IAffecAgent['cdsocie']>;
  decexer: FormControl<IAffecAgent['decexer']>;
  cdmois: FormControl<IAffecAgent['cdmois']>;
  matric: FormControl<IAffecAgent['matric']>;
  matric2: FormControl<IAffecAgent['matric2']>;
  cdmac: FormControl<IAffecAgent['cdmac']>;
};

export type AffecAgentFormGroup = FormGroup<AffecAgentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AffecAgentFormService {
  createAffecAgentFormGroup(affecAgent: AffecAgentFormGroupInput = { id: null }): AffecAgentFormGroup {
    const affecAgentRawValue = {
      ...this.getFormDefaults(),
      ...affecAgent,
    };
    return new FormGroup<AffecAgentFormGroupContent>({
      id: new FormControl(
        { value: affecAgentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(affecAgentRawValue.deccent),
      decagenc: new FormControl(affecAgentRawValue.decagenc),
      decserv: new FormControl(affecAgentRawValue.decserv),
      decoper: new FormControl(affecAgentRawValue.decoper),
      decsean: new FormControl(affecAgentRawValue.decsean),
      cdsocie: new FormControl(affecAgentRawValue.cdsocie),
      decexer: new FormControl(affecAgentRawValue.decexer),
      cdmois: new FormControl(affecAgentRawValue.cdmois),
      matric: new FormControl(affecAgentRawValue.matric),
      matric2: new FormControl(affecAgentRawValue.matric2),
      cdmac: new FormControl(affecAgentRawValue.cdmac),
    });
  }

  getAffecAgent(form: AffecAgentFormGroup): IAffecAgent | NewAffecAgent {
    return form.getRawValue() as IAffecAgent | NewAffecAgent;
  }

  resetForm(form: AffecAgentFormGroup, affecAgent: AffecAgentFormGroupInput): void {
    const affecAgentRawValue = { ...this.getFormDefaults(), ...affecAgent };
    form.reset(
      {
        ...affecAgentRawValue,
        id: { value: affecAgentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AffecAgentFormDefaults {
    return {
      id: null,
    };
  }
}
