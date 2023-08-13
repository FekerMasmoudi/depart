import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IServiceRot, NewServiceRot } from '../service-rot.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IServiceRot for edit and NewServiceRotFormGroupInput for create.
 */
type ServiceRotFormGroupInput = IServiceRot | PartialWithRequiredKeyOf<NewServiceRot>;

type ServiceRotFormDefaults = Pick<NewServiceRot, 'id'>;

type ServiceRotFormGroupContent = {
  id: FormControl<IServiceRot['id'] | NewServiceRot['id']>;
  deccent: FormControl<IServiceRot['deccent']>;
  decagenc: FormControl<IServiceRot['decagenc']>;
  decserv: FormControl<IServiceRot['decserv']>;
  codgrp: FormControl<IServiceRot['codgrp']>;
  delserv: FormControl<IServiceRot['delserv']>;
  ordserv: FormControl<IServiceRot['ordserv']>;
};

export type ServiceRotFormGroup = FormGroup<ServiceRotFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ServiceRotFormService {
  createServiceRotFormGroup(serviceRot: ServiceRotFormGroupInput = { id: null }): ServiceRotFormGroup {
    const serviceRotRawValue = {
      ...this.getFormDefaults(),
      ...serviceRot,
    };
    return new FormGroup<ServiceRotFormGroupContent>({
      id: new FormControl(
        { value: serviceRotRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(serviceRotRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(serviceRotRawValue.decagenc, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(serviceRotRawValue.decserv, {
        validators: [Validators.required],
      }),
      codgrp: new FormControl(serviceRotRawValue.codgrp, {
        validators: [Validators.required],
      }),
      delserv: new FormControl(serviceRotRawValue.delserv),
      ordserv: new FormControl(serviceRotRawValue.ordserv),
    });
  }

  getServiceRot(form: ServiceRotFormGroup): IServiceRot | NewServiceRot {
    return form.getRawValue() as IServiceRot | NewServiceRot;
  }

  resetForm(form: ServiceRotFormGroup, serviceRot: ServiceRotFormGroupInput): void {
    const serviceRotRawValue = { ...this.getFormDefaults(), ...serviceRot };
    form.reset(
      {
        ...serviceRotRawValue,
        id: { value: serviceRotRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ServiceRotFormDefaults {
    return {
      id: null,
    };
  }
}
