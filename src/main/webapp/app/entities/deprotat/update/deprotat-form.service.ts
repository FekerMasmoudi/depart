import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';
import { IDeprotat, NewDeprotat } from '../deprotat.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IDeprotat for edit and NewDeprotatFormGroupInput for create.
 */
type DeprotatFormGroupInput = IDeprotat | PartialWithRequiredKeyOf<NewDeprotat>;

/**
 * Type that converts some properties for forms.
 */
type FormValueOf<T extends IDeprotat | NewDeprotat> = Omit<T, 'hdeparte' | 'hretoure' | 'harralle' | 'harrrete'> & {
  hdeparte?: string | null;
  hretoure?: string | null;
  harralle?: string | null;
  harrrete?: string | null;
};

type DeprotatFormRawValue = FormValueOf<IDeprotat>;

type NewDeprotatFormRawValue = FormValueOf<NewDeprotat>;

type DeprotatFormDefaults = Pick<NewDeprotat, 'id' | 'hdeparte' | 'hretoure' | 'harralle' | 'harrrete'>;

type DeprotatFormGroupContent = {
  id: FormControl<DeprotatFormRawValue['id'] | NewDeprotat['id']>;
  deccent: FormControl<DeprotatFormRawValue['deccent']>;
  decagenc: FormControl<DeprotatFormRawValue['decagenc']>;
  dedated: FormControl<DeprotatFormRawValue['dedated']>;
  denumdp: FormControl<DeprotatFormRawValue['denumdp']>;
  decserv: FormControl<DeprotatFormRawValue['decserv']>;
  decoper: FormControl<DeprotatFormRawValue['decoper']>;
  decsean: FormControl<DeprotatFormRawValue['decsean']>;
  numrotat: FormControl<DeprotatFormRawValue['numrotat']>;
  ligdeccent: FormControl<DeprotatFormRawValue['ligdeccent']>;
  ligdecagenc: FormControl<DeprotatFormRawValue['ligdecagenc']>;
  denumli: FormControl<DeprotatFormRawValue['denumli']>;
  decstat: FormControl<DeprotatFormRawValue['decstat']>;
  decsta1: FormControl<DeprotatFormRawValue['decsta1']>;
  matric: FormControl<DeprotatFormRawValue['matric']>;
  matric1: FormControl<DeprotatFormRawValue['matric1']>;
  cdmac: FormControl<DeprotatFormRawValue['cdmac']>;
  hdeparte: FormControl<DeprotatFormRawValue['hdeparte']>;
  hretoure: FormControl<DeprotatFormRawValue['hretoure']>;
  harralle: FormControl<DeprotatFormRawValue['harralle']>;
  harrrete: FormControl<DeprotatFormRawValue['harrrete']>;
  rannul: FormControl<DeprotatFormRawValue['rannul']>;
  km: FormControl<DeprotatFormRawValue['km']>;
  motifa: FormControl<DeprotatFormRawValue['motifa']>;
  observ: FormControl<DeprotatFormRawValue['observ']>;
  recettesvoy: FormControl<DeprotatFormRawValue['recettesvoy']>;
  nbrevoy: FormControl<DeprotatFormRawValue['nbrevoy']>;
  paye: FormControl<DeprotatFormRawValue['paye']>;
  cd1: FormControl<DeprotatFormRawValue['cd1']>;
  cd2: FormControl<DeprotatFormRawValue['cd2']>;
  cd3: FormControl<DeprotatFormRawValue['cd3']>;
  decmotifcha: FormControl<DeprotatFormRawValue['decmotifcha']>;
  decmotifrea: FormControl<DeprotatFormRawValue['decmotifrea']>;
  idapex: FormControl<DeprotatFormRawValue['idapex']>;
  plusmoins: FormControl<DeprotatFormRawValue['plusmoins']>;
  a: FormControl<DeprotatFormRawValue['a']>;
  r: FormControl<DeprotatFormRawValue['r']>;
};

export type DeprotatFormGroup = FormGroup<DeprotatFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class DeprotatFormService {
  createDeprotatFormGroup(deprotat: DeprotatFormGroupInput = { id: null }): DeprotatFormGroup {
    const deprotatRawValue = this.convertDeprotatToDeprotatRawValue({
      ...this.getFormDefaults(),
      ...deprotat,
    });
    return new FormGroup<DeprotatFormGroupContent>({
      id: new FormControl(
        { value: deprotatRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(deprotatRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(deprotatRawValue.decagenc, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(deprotatRawValue.dedated, {
        validators: [Validators.required],
      }),
      denumdp: new FormControl(deprotatRawValue.denumdp, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(deprotatRawValue.decserv, {
        validators: [Validators.required],
      }),
      decoper: new FormControl(deprotatRawValue.decoper, {
        validators: [Validators.required],
      }),
      decsean: new FormControl(deprotatRawValue.decsean, {
        validators: [Validators.required],
      }),
      numrotat: new FormControl(deprotatRawValue.numrotat),
      ligdeccent: new FormControl(deprotatRawValue.ligdeccent),
      ligdecagenc: new FormControl(deprotatRawValue.ligdecagenc),
      denumli: new FormControl(deprotatRawValue.denumli),
      decstat: new FormControl(deprotatRawValue.decstat),
      decsta1: new FormControl(deprotatRawValue.decsta1),
      matric: new FormControl(deprotatRawValue.matric),
      matric1: new FormControl(deprotatRawValue.matric1),
      cdmac: new FormControl(deprotatRawValue.cdmac),
      hdeparte: new FormControl(deprotatRawValue.hdeparte),
      hretoure: new FormControl(deprotatRawValue.hretoure),
      harralle: new FormControl(deprotatRawValue.harralle),
      harrrete: new FormControl(deprotatRawValue.harrrete),
      rannul: new FormControl(deprotatRawValue.rannul),
      km: new FormControl(deprotatRawValue.km),
      motifa: new FormControl(deprotatRawValue.motifa),
      observ: new FormControl(deprotatRawValue.observ),
      recettesvoy: new FormControl(deprotatRawValue.recettesvoy),
      nbrevoy: new FormControl(deprotatRawValue.nbrevoy),
      paye: new FormControl(deprotatRawValue.paye),
      cd1: new FormControl(deprotatRawValue.cd1),
      cd2: new FormControl(deprotatRawValue.cd2),
      cd3: new FormControl(deprotatRawValue.cd3),
      decmotifcha: new FormControl(deprotatRawValue.decmotifcha),
      decmotifrea: new FormControl(deprotatRawValue.decmotifrea),
      idapex: new FormControl(deprotatRawValue.idapex),
      plusmoins: new FormControl(deprotatRawValue.plusmoins),
      a: new FormControl(deprotatRawValue.a),
      r: new FormControl(deprotatRawValue.r),
    });
  }

  getDeprotat(form: DeprotatFormGroup): IDeprotat | NewDeprotat {
    return this.convertDeprotatRawValueToDeprotat(form.getRawValue() as DeprotatFormRawValue | NewDeprotatFormRawValue);
  }

  resetForm(form: DeprotatFormGroup, deprotat: DeprotatFormGroupInput): void {
    const deprotatRawValue = this.convertDeprotatToDeprotatRawValue({ ...this.getFormDefaults(), ...deprotat });
    form.reset(
      {
        ...deprotatRawValue,
        id: { value: deprotatRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): DeprotatFormDefaults {
    const currentTime = dayjs();

    return {
      id: null,
      hdeparte: currentTime,
      hretoure: currentTime,
      harralle: currentTime,
      harrrete: currentTime,
    };
  }

  private convertDeprotatRawValueToDeprotat(rawDeprotat: DeprotatFormRawValue | NewDeprotatFormRawValue): IDeprotat | NewDeprotat {
    return {
      ...rawDeprotat,
      hdeparte: dayjs(rawDeprotat.hdeparte, DATE_TIME_FORMAT),
      hretoure: dayjs(rawDeprotat.hretoure, DATE_TIME_FORMAT),
      harralle: dayjs(rawDeprotat.harralle, DATE_TIME_FORMAT),
      harrrete: dayjs(rawDeprotat.harrrete, DATE_TIME_FORMAT),
    };
  }

  private convertDeprotatToDeprotatRawValue(
    deprotat: IDeprotat | (Partial<NewDeprotat> & DeprotatFormDefaults)
  ): DeprotatFormRawValue | PartialWithRequiredKeyOf<NewDeprotatFormRawValue> {
    return {
      ...deprotat,
      hdeparte: deprotat.hdeparte ? deprotat.hdeparte.format(DATE_TIME_FORMAT) : undefined,
      hretoure: deprotat.hretoure ? deprotat.hretoure.format(DATE_TIME_FORMAT) : undefined,
      harralle: deprotat.harralle ? deprotat.harralle.format(DATE_TIME_FORMAT) : undefined,
      harrrete: deprotat.harrrete ? deprotat.harrrete.format(DATE_TIME_FORMAT) : undefined,
    };
  }
}
