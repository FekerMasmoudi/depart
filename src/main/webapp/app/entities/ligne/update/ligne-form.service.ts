import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { ILigne, NewLigne } from '../ligne.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts ILigne for edit and NewLigneFormGroupInput for create.
 */
type LigneFormGroupInput = ILigne | PartialWithRequiredKeyOf<NewLigne>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends ILigne | NewLigne> = Omit<T, 'lastupdate'> & {
  lastupdate?: string | null;
};

type LigneFormRawValue = FormValueOf<ILigne>;

type NewLigneFormRawValue = FormValueOf<NewLigne>;

type LigneFormDefaults = Pick<NewLigne, 'id' | 'lastupdate'>;

type LigneFormGroupContent = {
  id: FormControl<LigneFormRawValue['id'] | NewLigne['id']>;
  deccent: FormControl<LigneFormRawValue['deccent']>;
  decagenc: FormControl<LigneFormRawValue['decagenc']>;
  denumli: FormControl<LigneFormRawValue['denumli']>;
  dectyli: FormControl<LigneFormRawValue['dectyli']>;
  dectyta: FormControl<LigneFormRawValue['dectyta']>;
  denomli: FormControl<LigneFormRawValue['denomli']>;
  dectyeq: FormControl<LigneFormRawValue['dectyeq']>;
  denbrkm: FormControl<LigneFormRawValue['denbrkm']>;
  detparc: FormControl<LigneFormRawValue['detparc']>;
  dedural: FormControl<LigneFormRawValue['dedural']>;
  dedurrt: FormControl<LigneFormRawValue['dedurrt']>;
  detrjva: FormControl<LigneFormRawValue['detrjva']>;
  detrjvr: FormControl<LigneFormRawValue['detrjvr']>;
  depiste: FormControl<LigneFormRawValue['depiste']>;
  statlig: FormControl<LigneFormRawValue['statlig']>;
  lig: FormControl<LigneFormRawValue['lig']>;
  lig1: FormControl<LigneFormRawValue['lig1']>;
  valide: FormControl<LigneFormRawValue['valide']>;
  denumli2: FormControl<LigneFormRawValue['denumli2']>;
  kml: FormControl<LigneFormRawValue['kml']>;
  kmlContentType: FormControl<LigneFormRawValue['kmlContentType']>;
  description: FormControl<LigneFormRawValue['description']>;
  mimtype: FormControl<LigneFormRawValue['mimtype']>;
  filename: FormControl<LigneFormRawValue['filename']>;
  charset: FormControl<LigneFormRawValue['charset']>;
  lastupdate: FormControl<LigneFormRawValue['lastupdate']>;
};

export type LigneFormGroup = FormGroup<LigneFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class LigneFormService {
  createLigneFormGroup(ligne: LigneFormGroupInput = { id: null }): LigneFormGroup {
    const ligneRawValue = this.convertLigneToLigneRawValue({
      ...this.getFormDefaults(),
      ...ligne,
    });
    return new FormGroup<LigneFormGroupContent>({
      id: new FormControl(
        { value: ligneRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(ligneRawValue.deccent),
      decagenc: new FormControl(ligneRawValue.decagenc),
      denumli: new FormControl(ligneRawValue.denumli),
      dectyli: new FormControl(ligneRawValue.dectyli),
      dectyta: new FormControl(ligneRawValue.dectyta),
      denomli: new FormControl(ligneRawValue.denomli),
      dectyeq: new FormControl(ligneRawValue.dectyeq),
      denbrkm: new FormControl(ligneRawValue.denbrkm),
      detparc: new FormControl(ligneRawValue.detparc),
      dedural: new FormControl(ligneRawValue.dedural),
      dedurrt: new FormControl(ligneRawValue.dedurrt),
      detrjva: new FormControl(ligneRawValue.detrjva),
      detrjvr: new FormControl(ligneRawValue.detrjvr),
      depiste: new FormControl(ligneRawValue.depiste),
      statlig: new FormControl(ligneRawValue.statlig),
      lig: new FormControl(ligneRawValue.lig),
      lig1: new FormControl(ligneRawValue.lig1),
      valide: new FormControl(ligneRawValue.valide),
      denumli2: new FormControl(ligneRawValue.denumli2),
      kml: new FormControl(ligneRawValue.kml),
      kmlContentType: new FormControl(ligneRawValue.kmlContentType),
      description: new FormControl(ligneRawValue.description),
      mimtype: new FormControl(ligneRawValue.mimtype),
      filename: new FormControl(ligneRawValue.filename),
      charset: new FormControl(ligneRawValue.charset),
      lastupdate: new FormControl(ligneRawValue.lastupdate),
    });
  }

  getLigne(form: LigneFormGroup): ILigne | NewLigne {
    return this.convertLigneRawValueToLigne(form.getRawValue() as LigneFormRawValue | NewLigneFormRawValue);
  }

  resetForm(form: LigneFormGroup, ligne: LigneFormGroupInput): void {
    const ligneRawValue = this.convertLigneToLigneRawValue({ ...this.getFormDefaults(), ...ligne });
    form.reset(
      {
        ...ligneRawValue,
        id: { value: ligneRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): LigneFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      lastupdate: currentTime,
    };
  }

  private convertLigneRawValueToLigne(rawLigne: LigneFormRawValue | NewLigneFormRawValue): ILigne | NewLigne {
    return {
      ...rawLigne,
      lastupdate: dayjs(rawLigne.lastupdate, DATE_TIME_FORMAT),
    };
  }

  private convertLigneToLigneRawValue(
    ligne: ILigne | (Partial<NewLigne> & LigneFormDefaults)
  ): LigneFormRawValue | PartialWithRequiredKeyOf<NewLigneFormRawValue> {
    return {
      ...ligne,
      lastupdate: ligne.lastupdate ? ligne.lastupdate.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
