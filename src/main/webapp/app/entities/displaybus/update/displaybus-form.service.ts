import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDisplaybus, NewDisplaybus } from '../displaybus.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDisplaybus for edit and NewDisplaybusFormGroupInput for create.
 */
type DisplaybusFormGroupInput = IDisplaybus | PartialWithRequiredKeyOf<NewDisplaybus>;

type DisplaybusFormDefaults = Pick<NewDisplaybus, 'id'>;

type DisplaybusFormGroupContent = {
  id: FormControl<IDisplaybus['id'] | NewDisplaybus['id']>;
  lang: FormControl<IDisplaybus['lang']>;
  vehicule: FormControl<IDisplaybus['vehicule']>;
  num_appel: FormControl<IDisplaybus['num_appel']>;
  detail_ligne: FormControl<IDisplaybus['detail_ligne']>;
  ligne: FormControl<IDisplaybus['ligne']>;
  direction: FormControl<IDisplaybus['direction']>;
  denumli: FormControl<IDisplaybus['denumli']>;
  deltyli: FormControl<IDisplaybus['deltyli']>;
};

export type DisplaybusFormGroup = FormGroup<DisplaybusFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DisplaybusFormService {
  createDisplaybusFormGroup(displaybus: DisplaybusFormGroupInput = { id: null }): DisplaybusFormGroup {
    const displaybusRawValue = {
      ...this.getFormDefaults(),
      ...displaybus,
    };
    return new FormGroup<DisplaybusFormGroupContent>({
      id: new FormControl(
        { value: displaybusRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      lang: new FormControl(displaybusRawValue.lang),
      vehicule: new FormControl(displaybusRawValue.vehicule),
      num_appel: new FormControl(displaybusRawValue.num_appel),
      detail_ligne: new FormControl(displaybusRawValue.detail_ligne),
      ligne: new FormControl(displaybusRawValue.ligne),
      direction: new FormControl(displaybusRawValue.direction),
      denumli: new FormControl(displaybusRawValue.denumli),
      deltyli: new FormControl(displaybusRawValue.deltyli),
    });
  }

  getDisplaybus(form: DisplaybusFormGroup): IDisplaybus | NewDisplaybus {
    return form.getRawValue() as IDisplaybus | NewDisplaybus;
  }

  resetForm(form: DisplaybusFormGroup, displaybus: DisplaybusFormGroupInput): void {
    const displaybusRawValue = { ...this.getFormDefaults(), ...displaybus };
    form.reset(
      {
        ...displaybusRawValue,
        id: { value: displaybusRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DisplaybusFormDefaults {
    return {
      id: null,
    };
  }
}
