import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ITrafic, NewTrafic } from '../trafic.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ITrafic for edit and NewTraficFormGroupInput for create.
 */
type TraficFormGroupInput = ITrafic | PartialWithRequiredKeyOf<NewTrafic>;

type TraficFormDefaults = Pick<NewTrafic, 'id'>;

type TraficFormGroupContent = {
  id: FormControl<ITrafic['id'] | NewTrafic['id']>;
  deccent: FormControl<ITrafic['deccent']>;
  decagenc: FormControl<ITrafic['decagenc']>;
  dedated: FormControl<ITrafic['dedated']>;
  ancien: FormControl<ITrafic['ancien']>;
  vldtrafic: FormControl<ITrafic['vldtrafic']>;
  clotrafic: FormControl<ITrafic['clotrafic']>;
};

export type TraficFormGroup = FormGroup<TraficFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class TraficFormService {
  createTraficFormGroup(trafic: TraficFormGroupInput = { id: null }): TraficFormGroup {
    const traficRawValue = {
      ...this.getFormDefaults(),
      ...trafic,
    };
    return new FormGroup<TraficFormGroupContent>({
      id: new FormControl(
        { value: traficRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(traficRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(traficRawValue.decagenc, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(traficRawValue.dedated, {
        validators: [Validators.required],
      }),
      ancien: new FormControl(traficRawValue.ancien),
      vldtrafic: new FormControl(traficRawValue.vldtrafic),
      clotrafic: new FormControl(traficRawValue.clotrafic),
    });
  }

  getTrafic(form: TraficFormGroup): ITrafic | NewTrafic {
    return form.getRawValue() as ITrafic | NewTrafic;
  }

  resetForm(form: TraficFormGroup, trafic: TraficFormGroupInput): void {
    const traficRawValue = { ...this.getFormDefaults(), ...trafic };
    form.reset(
      {
        ...traficRawValue,
        id: { value: traficRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): TraficFormDefaults {
    return {
      id: null,
    };
  }
}
