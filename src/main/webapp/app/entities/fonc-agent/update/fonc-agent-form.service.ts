import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IFoncAgent, NewFoncAgent } from '../fonc-agent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFoncAgent for edit and NewFoncAgentFormGroupInput for create.
 */
type FoncAgentFormGroupInput = IFoncAgent | PartialWithRequiredKeyOf<NewFoncAgent>;

type FoncAgentFormDefaults = Pick<NewFoncAgent, 'id'>;

type FoncAgentFormGroupContent = {
  id: FormControl<IFoncAgent['id'] | NewFoncAgent['id']>;
  cdfonc: FormControl<IFoncAgent['cdfonc']>;
  matric: FormControl<IFoncAgent['matric']>;
  nom: FormControl<IFoncAgent['nom']>;
  prenom: FormControl<IFoncAgent['prenom']>;
  dateff: FormControl<IFoncAgent['dateff']>;
  valide: FormControl<IFoncAgent['valide']>;
};

export type FoncAgentFormGroup = FormGroup<FoncAgentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FoncAgentFormService {
  createFoncAgentFormGroup(foncAgent: FoncAgentFormGroupInput = { id: null }): FoncAgentFormGroup {
    const foncAgentRawValue = {
      ...this.getFormDefaults(),
      ...foncAgent,
    };
    return new FormGroup<FoncAgentFormGroupContent>({
      id: new FormControl(
        { value: foncAgentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdfonc: new FormControl(foncAgentRawValue.cdfonc),
      matric: new FormControl(foncAgentRawValue.matric),
      nom: new FormControl(foncAgentRawValue.nom),
      prenom: new FormControl(foncAgentRawValue.prenom),
      dateff: new FormControl(foncAgentRawValue.dateff),
      valide: new FormControl(foncAgentRawValue.valide),
    });
  }

  getFoncAgent(form: FoncAgentFormGroup): IFoncAgent | NewFoncAgent {
    return form.getRawValue() as IFoncAgent | NewFoncAgent;
  }

  resetForm(form: FoncAgentFormGroup, foncAgent: FoncAgentFormGroupInput): void {
    const foncAgentRawValue = { ...this.getFormDefaults(), ...foncAgent };
    form.reset(
      {
        ...foncAgentRawValue,
        id: { value: foncAgentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): FoncAgentFormDefaults {
    return {
      id: null,
    };
  }
}
