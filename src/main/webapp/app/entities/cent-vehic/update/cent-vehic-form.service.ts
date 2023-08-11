import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { ICentVehic, NewCentVehic } from '../cent-vehic.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ICentVehic for edit and NewCentVehicFormGroupInput for create.
 */
type CentVehicFormGroupInput = ICentVehic | PartialWithRequiredKeyOf<NewCentVehic>;

type CentVehicFormDefaults = Pick<NewCentVehic, 'id'>;

type CentVehicFormGroupContent = {
  id: FormControl<ICentVehic['id'] | NewCentVehic['id']>;
  cdmac: FormControl<ICentVehic['cdmac']>;
  dateff: FormControl<ICentVehic['dateff']>;
  deccent: FormControl<ICentVehic['deccent']>;
  decagenc: FormControl<ICentVehic['decagenc']>;
};

export type CentVehicFormGroup = FormGroup<CentVehicFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class CentVehicFormService {
  createCentVehicFormGroup(centVehic: CentVehicFormGroupInput = { id: null }): CentVehicFormGroup {
    const centVehicRawValue = {
      ...this.getFormDefaults(),
      ...centVehic,
    };
    return new FormGroup<CentVehicFormGroupContent>({
      id: new FormControl(
        { value: centVehicRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdmac: new FormControl(centVehicRawValue.cdmac),
      dateff: new FormControl(centVehicRawValue.dateff),
      deccent: new FormControl(centVehicRawValue.deccent),
      decagenc: new FormControl(centVehicRawValue.decagenc),
    });
  }

  getCentVehic(form: CentVehicFormGroup): ICentVehic | NewCentVehic {
    return form.getRawValue() as ICentVehic | NewCentVehic;
  }

  resetForm(form: CentVehicFormGroup, centVehic: CentVehicFormGroupInput): void {
    const centVehicRawValue = { ...this.getFormDefaults(), ...centVehic };
    form.reset(
      {
        ...centVehicRawValue,
        id: { value: centVehicRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): CentVehicFormDefaults {
    return {
      id: null,
    };
  }
}
