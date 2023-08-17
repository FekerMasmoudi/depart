import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IExternalApi, NewExternalApi } from '../external-api.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IExternalApi for edit and NewExternalApiFormGroupInput for create.
 */
type ExternalApiFormGroupInput = IExternalApi | PartialWithRequiredKeyOf<NewExternalApi>;

type ExternalApiFormDefaults = Pick<NewExternalApi, 'id' | 'satausgetapi'>;

type ExternalApiFormGroupContent = {
  id: FormControl<IExternalApi['id'] | NewExternalApi['id']>;
  idm: FormControl<IExternalApi['idm']>;
  name: FormControl<IExternalApi['name']>;
  status: FormControl<IExternalApi['status']>;
  comments: FormControl<IExternalApi['comments']>;
  idschema: FormControl<IExternalApi['idschema']>;
  datecreatedt: FormControl<IExternalApi['datecreatedt']>;
  lastupdatedm: FormControl<IExternalApi['lastupdatedm']>;
  origin: FormControl<IExternalApi['origin']>;
  templateid: FormControl<IExternalApi['templateid']>;
  idmodule: FormControl<IExternalApi['idmodule']>;
  uritemplate: FormControl<IExternalApi['uritemplate']>;
  priority: FormControl<IExternalApi['priority']>;
  schemaidt: FormControl<IExternalApi['schemaidt']>;
  createdatet: FormControl<IExternalApi['createdatet']>;
  lastupdatete: FormControl<IExternalApi['lastupdatete']>;
  entity: FormControl<IExternalApi['entity']>;
  parametre: FormControl<IExternalApi['parametre']>;
  countrowsreq: FormControl<IExternalApi['countrowsreq']>;
  countrowsres: FormControl<IExternalApi['countrowsres']>;
  frequency: FormControl<IExternalApi['frequency']>;
  emergencycode: FormControl<IExternalApi['emergencycode']>;
  satausgetapi: FormControl<IExternalApi['satausgetapi']>;
};

export type ExternalApiFormGroup = FormGroup<ExternalApiFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ExternalApiFormService {
  createExternalApiFormGroup(externalApi: ExternalApiFormGroupInput = { id: null }): ExternalApiFormGroup {
    const externalApiRawValue = {
      ...this.getFormDefaults(),
      ...externalApi,
    };
    return new FormGroup<ExternalApiFormGroupContent>({
      id: new FormControl(
        { value: externalApiRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      idm: new FormControl(externalApiRawValue.idm),
      name: new FormControl(externalApiRawValue.name),
      status: new FormControl(externalApiRawValue.status),
      comments: new FormControl(externalApiRawValue.comments),
      idschema: new FormControl(externalApiRawValue.idschema),
      datecreatedt: new FormControl(externalApiRawValue.datecreatedt),
      lastupdatedm: new FormControl(externalApiRawValue.lastupdatedm),
      origin: new FormControl(externalApiRawValue.origin),
      templateid: new FormControl(externalApiRawValue.templateid),
      idmodule: new FormControl(externalApiRawValue.idmodule),
      uritemplate: new FormControl(externalApiRawValue.uritemplate),
      priority: new FormControl(externalApiRawValue.priority),
      schemaidt: new FormControl(externalApiRawValue.schemaidt),
      createdatet: new FormControl(externalApiRawValue.createdatet),
      lastupdatete: new FormControl(externalApiRawValue.lastupdatete),
      entity: new FormControl(externalApiRawValue.entity),
      parametre: new FormControl(externalApiRawValue.parametre),
      countrowsreq: new FormControl(externalApiRawValue.countrowsreq),
      countrowsres: new FormControl(externalApiRawValue.countrowsres),
      frequency: new FormControl(externalApiRawValue.frequency),
      emergencycode: new FormControl(externalApiRawValue.emergencycode),
      satausgetapi: new FormControl(externalApiRawValue.satausgetapi),
    });
  }

  getExternalApi(form: ExternalApiFormGroup): IExternalApi | NewExternalApi {
    return form.getRawValue() as IExternalApi | NewExternalApi;
  }

  resetForm(form: ExternalApiFormGroup, externalApi: ExternalApiFormGroupInput): void {
    const externalApiRawValue = { ...this.getFormDefaults(), ...externalApi };
    form.reset(
      {
        ...externalApiRawValue,
        id: { value: externalApiRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ExternalApiFormDefaults {
    return {
      id: null,
      satausgetapi: false,
    };
  }
}
