import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IAffectagent, NewAffectagent } from '../affectagent.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IAffectagent for edit and NewAffectagentFormGroupInput for create.
 */
type AffectagentFormGroupInput = IAffectagent | PartialWithRequiredKeyOf<NewAffectagent>;

type AffectagentFormDefaults = Pick<NewAffectagent, 'id'>;

type AffectagentFormGroupContent = {
  id: FormControl<IAffectagent['id'] | NewAffectagent['id']>;
  deccent: FormControl<IAffectagent['deccent']>;
  decagenc: FormControl<IAffectagent['decagenc']>;
  decserv: FormControl<IAffectagent['decserv']>;
  decoper: FormControl<IAffectagent['decoper']>;
  decsean: FormControl<IAffectagent['decsean']>;
  cdmois: FormControl<IAffectagent['cdmois']>;
  cdsocie: FormControl<IAffectagent['cdsocie']>;
  decexer: FormControl<IAffectagent['decexer']>;
  matric: FormControl<IAffectagent['matric']>;
  matric2: FormControl<IAffectagent['matric2']>;
  cdmac: FormControl<IAffectagent['cdmac']>;
};

export type AffectagentFormGroup = FormGroup<AffectagentFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class AffectagentFormService {
  createAffectagentFormGroup(affectagent: AffectagentFormGroupInput = { id: null }): AffectagentFormGroup {
    const affectagentRawValue = {
      ...this.getFormDefaults(),
      ...affectagent,
    };
    return new FormGroup<AffectagentFormGroupContent>({
      id: new FormControl(
        { value: affectagentRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(affectagentRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(affectagentRawValue.decagenc, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(affectagentRawValue.decserv, {
        validators: [Validators.required],
      }),
      decoper: new FormControl(affectagentRawValue.decoper, {
        validators: [Validators.required],
      }),
      decsean: new FormControl(affectagentRawValue.decsean, {
        validators: [Validators.required],
      }),
      cdmois: new FormControl(affectagentRawValue.cdmois, {
        validators: [Validators.required],
      }),
      cdsocie: new FormControl(affectagentRawValue.cdsocie, {
        validators: [Validators.required],
      }),
      decexer: new FormControl(affectagentRawValue.decexer, {
        validators: [Validators.required],
      }),
      matric: new FormControl(affectagentRawValue.matric, {
        validators: [Validators.required],
      }),
      matric2: new FormControl(affectagentRawValue.matric2, {
        validators: [Validators.required],
      }),
      cdmac: new FormControl(affectagentRawValue.cdmac, {
        validators: [Validators.required],
      }),
    });
  }

  getAffectagent(form: AffectagentFormGroup): IAffectagent | NewAffectagent {
    return form.getRawValue() as IAffectagent | NewAffectagent;
  }

  resetForm(form: AffectagentFormGroup, affectagent: AffectagentFormGroupInput): void {
    const affectagentRawValue = { ...this.getFormDefaults(), ...affectagent };
    form.reset(
      {
        ...affectagentRawValue,
        id: { value: affectagentRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): AffectagentFormDefaults {
    return {
      id: null,
    };
  }
}
