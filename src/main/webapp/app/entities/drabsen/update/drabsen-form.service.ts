import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IDrabsen, NewDrabsen } from '../drabsen.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDrabsen for edit and NewDrabsenFormGroupInput for create.
 */
type DrabsenFormGroupInput = IDrabsen | PartialWithRequiredKeyOf<NewDrabsen>;

type DrabsenFormDefaults = Pick<NewDrabsen, 'id'>;

type DrabsenFormGroupContent = {
  id: FormControl<IDrabsen['id'] | NewDrabsen['id']>;
  cdtypab: FormControl<IDrabsen['cdtypab']>;
  matric: FormControl<IDrabsen['matric']>;
  databs: FormControl<IDrabsen['databs']>;
  numabs: FormControl<IDrabsen['numabs']>;
  nbrabs: FormControl<IDrabsen['nbrabs']>;
  validabs: FormControl<IDrabsen['validabs']>;
  observaabs: FormControl<IDrabsen['observaabs']>;
  cd1: FormControl<IDrabsen['cd1']>;
  cd2: FormControl<IDrabsen['cd2']>;
  cd3: FormControl<IDrabsen['cd3']>;
};

export type DrabsenFormGroup = FormGroup<DrabsenFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DrabsenFormService {
  createDrabsenFormGroup(drabsen: DrabsenFormGroupInput = { id: null }): DrabsenFormGroup {
    const drabsenRawValue = {
      ...this.getFormDefaults(),
      ...drabsen,
    };
    return new FormGroup<DrabsenFormGroupContent>({
      id: new FormControl(
        { value: drabsenRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdtypab: new FormControl(drabsenRawValue.cdtypab),
      matric: new FormControl(drabsenRawValue.matric),
      databs: new FormControl(drabsenRawValue.databs),
      numabs: new FormControl(drabsenRawValue.numabs),
      nbrabs: new FormControl(drabsenRawValue.nbrabs),
      validabs: new FormControl(drabsenRawValue.validabs),
      observaabs: new FormControl(drabsenRawValue.observaabs),
      cd1: new FormControl(drabsenRawValue.cd1),
      cd2: new FormControl(drabsenRawValue.cd2),
      cd3: new FormControl(drabsenRawValue.cd3),
    });
  }

  getDrabsen(form: DrabsenFormGroup): IDrabsen | NewDrabsen {
    return form.getRawValue() as IDrabsen | NewDrabsen;
  }

  resetForm(form: DrabsenFormGroup, drabsen: DrabsenFormGroupInput): void {
    const drabsenRawValue = { ...this.getFormDefaults(), ...drabsen };
    form.reset(
      {
        ...drabsenRawValue,
        id: { value: drabsenRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DrabsenFormDefaults {
    return {
      id: null,
    };
  }
}
