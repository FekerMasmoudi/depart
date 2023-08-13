import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBordereau, NewBordereau } from '../bordereau.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBordereau for edit and NewBordereauFormGroupInput for create.
 */
type BordereauFormGroupInput = IBordereau | PartialWithRequiredKeyOf<NewBordereau>;

type BordereauFormDefaults = Pick<NewBordereau, 'id'>;

type BordereauFormGroupContent = {
  id: FormControl<IBordereau['id'] | NewBordereau['id']>;
  deccent: FormControl<IBordereau['deccent']>;
  decagenc: FormControl<IBordereau['decagenc']>;
  exercice: FormControl<IBordereau['exercice']>;
  denbord: FormControl<IBordereau['denbord']>;
  det_deccent: FormControl<IBordereau['det_deccent']>;
  det_decagenc: FormControl<IBordereau['det_decagenc']>;
  decserv: FormControl<IBordereau['decserv']>;
  cdmac: FormControl<IBordereau['cdmac']>;
  decoper: FormControl<IBordereau['decoper']>;
  decsean: FormControl<IBordereau['decsean']>;
  dedated: FormControl<IBordereau['dedated']>;
  denumdp: FormControl<IBordereau['denumdp']>;
  matric: FormControl<IBordereau['matric']>;
  matric1: FormControl<IBordereau['matric1']>;
  natbord: FormControl<IBordereau['natbord']>;
  detadedb: FormControl<IBordereau['detadedb']>;
  numb_pdat: FormControl<IBordereau['numb_pdat']>;
  deheupsr: FormControl<IBordereau['deheupsr']>;
  demnttn: FormControl<IBordereau['demnttn']>;
  demntto: FormControl<IBordereau['demntto']>;
  decetbr: FormControl<IBordereau['decetbr']>;
  decetcs: FormControl<IBordereau['decetcs']>;
  denumcs: FormControl<IBordereau['denumcs']>;
  denumbr: FormControl<IBordereau['denumbr']>;
  denumver: FormControl<IBordereau['denumver']>;
  declote: FormControl<IBordereau['declote']>;
  date_saisie: FormControl<IBordereau['date_saisie']>;
  clorec: FormControl<IBordereau['clorec']>;
  demntvers: FormControl<IBordereau['demntvers']>;
};

export type BordereauFormGroup = FormGroup<BordereauFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BordereauFormService {
  createBordereauFormGroup(bordereau: BordereauFormGroupInput = { id: null }): BordereauFormGroup {
    const bordereauRawValue = {
      ...this.getFormDefaults(),
      ...bordereau,
    };
    return new FormGroup<BordereauFormGroupContent>({
      id: new FormControl(
        { value: bordereauRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(bordereauRawValue.deccent, {
        validators: [Validators.required],
      }),
      decagenc: new FormControl(bordereauRawValue.decagenc, {
        validators: [Validators.required],
      }),
      exercice: new FormControl(bordereauRawValue.exercice, {
        validators: [Validators.required],
      }),
      denbord: new FormControl(bordereauRawValue.denbord, {
        validators: [Validators.required],
      }),
      det_deccent: new FormControl(bordereauRawValue.det_deccent, {
        validators: [Validators.required],
      }),
      det_decagenc: new FormControl(bordereauRawValue.det_decagenc, {
        validators: [Validators.required],
      }),
      decserv: new FormControl(bordereauRawValue.decserv, {
        validators: [Validators.required],
      }),
      cdmac: new FormControl(bordereauRawValue.cdmac, {
        validators: [Validators.required],
      }),
      decoper: new FormControl(bordereauRawValue.decoper, {
        validators: [Validators.required],
      }),
      decsean: new FormControl(bordereauRawValue.decsean, {
        validators: [Validators.required],
      }),
      dedated: new FormControl(bordereauRawValue.dedated, {
        validators: [Validators.required],
      }),
      denumdp: new FormControl(bordereauRawValue.denumdp, {
        validators: [Validators.required],
      }),
      matric: new FormControl(bordereauRawValue.matric, {
        validators: [Validators.required],
      }),
      matric1: new FormControl(bordereauRawValue.matric1, {
        validators: [Validators.required],
      }),
      natbord: new FormControl(bordereauRawValue.natbord, {
        validators: [Validators.required],
      }),
      detadedb: new FormControl(bordereauRawValue.detadedb, {
        validators: [Validators.required],
      }),
      numb_pdat: new FormControl(bordereauRawValue.numb_pdat, {
        validators: [Validators.required],
      }),
      deheupsr: new FormControl(bordereauRawValue.deheupsr, {
        validators: [Validators.required],
      }),
      demnttn: new FormControl(bordereauRawValue.demnttn, {
        validators: [Validators.required],
      }),
      demntto: new FormControl(bordereauRawValue.demntto, {
        validators: [Validators.required],
      }),
      decetbr: new FormControl(bordereauRawValue.decetbr, {
        validators: [Validators.required],
      }),
      decetcs: new FormControl(bordereauRawValue.decetcs, {
        validators: [Validators.required],
      }),
      denumcs: new FormControl(bordereauRawValue.denumcs, {
        validators: [Validators.required],
      }),
      denumbr: new FormControl(bordereauRawValue.denumbr, {
        validators: [Validators.required],
      }),
      denumver: new FormControl(bordereauRawValue.denumver, {
        validators: [Validators.required],
      }),
      declote: new FormControl(bordereauRawValue.declote, {
        validators: [Validators.required],
      }),
      date_saisie: new FormControl(bordereauRawValue.date_saisie, {
        validators: [Validators.required],
      }),
      clorec: new FormControl(bordereauRawValue.clorec, {
        validators: [Validators.required],
      }),
      demntvers: new FormControl(bordereauRawValue.demntvers, {
        validators: [Validators.required],
      }),
    });
  }

  getBordereau(form: BordereauFormGroup): IBordereau | NewBordereau {
    return form.getRawValue() as IBordereau | NewBordereau;
  }

  resetForm(form: BordereauFormGroup, bordereau: BordereauFormGroupInput): void {
    const bordereauRawValue = { ...this.getFormDefaults(), ...bordereau };
    form.reset(
      {
        ...bordereauRawValue,
        id: { value: bordereauRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BordereauFormDefaults {
    return {
      id: null,
    };
  }
}
