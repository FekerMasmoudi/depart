import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IModif, NewModif } from '../modif.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IModif for edit and NewModifFormGroupInput for create.
 */
type ModifFormGroupInput = IModif | PartialWithRequiredKeyOf<NewModif>;

type ModifFormDefaults = Pick<NewModif, 'id'>;

type ModifFormGroupContent = {
  id: FormControl<IModif['id'] | NewModif['id']>;
  deccent: FormControl<IModif['deccent']>;
  decagenc: FormControl<IModif['decagenc']>;
  dedated: FormControl<IModif['dedated']>;
  denumdp: FormControl<IModif['denumdp']>;
  decserv: FormControl<IModif['decserv']>;
  decoper: FormControl<IModif['decoper']>;
  decsean: FormControl<IModif['decsean']>;
  numrotat: FormControl<IModif['numrotat']>;
  matric: FormControl<IModif['matric']>;
  cd1: FormControl<IModif['cd1']>;
  decmotif: FormControl<IModif['decmotif']>;
  heur: FormControl<IModif['heur']>;
  chre: FormControl<IModif['chre']>;
  typ: FormControl<IModif['typ']>;
};

export type ModifFormGroup = FormGroup<ModifFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ModifFormService {
  createModifFormGroup(modif: ModifFormGroupInput = { id: null }): ModifFormGroup {
    const modifRawValue = {
      ...this.getFormDefaults(),
      ...modif,
    };
    return new FormGroup<ModifFormGroupContent>({
      id: new FormControl(
        { value: modifRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(modifRawValue.deccent),
      decagenc: new FormControl(modifRawValue.decagenc),
      dedated: new FormControl(modifRawValue.dedated),
      denumdp: new FormControl(modifRawValue.denumdp),
      decserv: new FormControl(modifRawValue.decserv),
      decoper: new FormControl(modifRawValue.decoper),
      decsean: new FormControl(modifRawValue.decsean),
      numrotat: new FormControl(modifRawValue.numrotat),
      matric: new FormControl(modifRawValue.matric),
      cd1: new FormControl(modifRawValue.cd1),
      decmotif: new FormControl(modifRawValue.decmotif),
      heur: new FormControl(modifRawValue.heur),
      chre: new FormControl(modifRawValue.chre),
      typ: new FormControl(modifRawValue.typ),
    });
  }

  getModif(form: ModifFormGroup): IModif | NewModif {
    return form.getRawValue() as IModif | NewModif;
  }

  resetForm(form: ModifFormGroup, modif: ModifFormGroupInput): void {
    const modifRawValue = { ...this.getFormDefaults(), ...modif };
    form.reset(
      {
        ...modifRawValue,
        id: { value: modifRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ModifFormDefaults {
    return {
      id: null,
    };
  }
}
