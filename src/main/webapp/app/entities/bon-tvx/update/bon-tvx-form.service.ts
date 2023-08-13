import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IBonTvx, NewBonTvx } from '../bon-tvx.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IBonTvx for edit and NewBonTvxFormGroupInput for create.
 */
type BonTvxFormGroupInput = IBonTvx | PartialWithRequiredKeyOf<NewBonTvx>;

type BonTvxFormDefaults = Pick<NewBonTvx, 'id'>;

type BonTvxFormGroupContent = {
  id: FormControl<IBonTvx['id'] | NewBonTvx['id']>;
  cdexerc: FormControl<IBonTvx['cdexerc']>;
  numbt: FormControl<IBonTvx['numbt']>;
  cdtier: FormControl<IBonTvx['cdtier']>;
  cdmac: FormControl<IBonTvx['cdmac']>;
  maccdmac: FormControl<IBonTvx['maccdmac']>;
  cdserv: FormControl<IBonTvx['cdserv']>;
  decagen: FormControl<IBonTvx['decagen']>;
  dradecagen: FormControl<IBonTvx['dradecagen']>;
  cdorga: FormControl<IBonTvx['cdorga']>;
  refbt: FormControl<IBonTvx['refbt']>;
  datbt: FormControl<IBonTvx['datbt']>;
  datdt: FormControl<IBonTvx['datdt']>;
  datft: FormControl<IBonTvx['datft']>;
  vld: FormControl<IBonTvx['vld']>;
  typtvx: FormControl<IBonTvx['typtvx']>;
  heurdb: FormControl<IBonTvx['heurdb']>;
  heurfi: FormControl<IBonTvx['heurfi']>;
  observ: FormControl<IBonTvx['observ']>;
  datsrt: FormControl<IBonTvx['datsrt']>;
  heursr: FormControl<IBonTvx['heursr']>;
  obstest: FormControl<IBonTvx['obstest']>;
  indexdep: FormControl<IBonTvx['indexdep']>;
  indexarr: FormControl<IBonTvx['indexarr']>;
  immatex: FormControl<IBonTvx['immatex']>;
  nomchauff: FormControl<IBonTvx['nomchauff']>;
  numpermis: FormControl<IBonTvx['numpermis']>;
  etab: FormControl<IBonTvx['etab']>;
  compteur: FormControl<IBonTvx['compteur']>;
  cptorg: FormControl<IBonTvx['cptorg']>;
  cdtyptr: FormControl<IBonTvx['cdtyptr']>;
  decstat: FormControl<IBonTvx['decstat']>;
  testeur: FormControl<IBonTvx['testeur']>;
  motifdep: FormControl<IBonTvx['motifdep']>;
  cdtypmnt: FormControl<IBonTvx['cdtypmnt']>;
  datsorprev: FormControl<IBonTvx['datsorprev']>;
  datmnqdu: FormControl<IBonTvx['datmnqdu']>;
  datmnqau: FormControl<IBonTvx['datmnqau']>;
  datentant: FormControl<IBonTvx['datentant']>;
  codstat: FormControl<IBonTvx['codstat']>;
  datvld: FormControl<IBonTvx['datvld']>;
  observ1: FormControl<IBonTvx['observ1']>;
  testeur1: FormControl<IBonTvx['testeur1']>;
  validag: FormControl<IBonTvx['validag']>;
  datsais: FormControl<IBonTvx['datsais']>;
};

export type BonTvxFormGroup = FormGroup<BonTvxFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class BonTvxFormService {
  createBonTvxFormGroup(bonTvx: BonTvxFormGroupInput = { id: null }): BonTvxFormGroup {
    const bonTvxRawValue = {
      ...this.getFormDefaults(),
      ...bonTvx,
    };
    return new FormGroup<BonTvxFormGroupContent>({
      id: new FormControl(
        { value: bonTvxRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      cdexerc: new FormControl(bonTvxRawValue.cdexerc),
      numbt: new FormControl(bonTvxRawValue.numbt),
      cdtier: new FormControl(bonTvxRawValue.cdtier),
      cdmac: new FormControl(bonTvxRawValue.cdmac),
      maccdmac: new FormControl(bonTvxRawValue.maccdmac),
      cdserv: new FormControl(bonTvxRawValue.cdserv),
      decagen: new FormControl(bonTvxRawValue.decagen),
      dradecagen: new FormControl(bonTvxRawValue.dradecagen),
      cdorga: new FormControl(bonTvxRawValue.cdorga),
      refbt: new FormControl(bonTvxRawValue.refbt),
      datbt: new FormControl(bonTvxRawValue.datbt),
      datdt: new FormControl(bonTvxRawValue.datdt),
      datft: new FormControl(bonTvxRawValue.datft),
      vld: new FormControl(bonTvxRawValue.vld),
      typtvx: new FormControl(bonTvxRawValue.typtvx),
      heurdb: new FormControl(bonTvxRawValue.heurdb),
      heurfi: new FormControl(bonTvxRawValue.heurfi),
      observ: new FormControl(bonTvxRawValue.observ),
      datsrt: new FormControl(bonTvxRawValue.datsrt),
      heursr: new FormControl(bonTvxRawValue.heursr),
      obstest: new FormControl(bonTvxRawValue.obstest),
      indexdep: new FormControl(bonTvxRawValue.indexdep),
      indexarr: new FormControl(bonTvxRawValue.indexarr),
      immatex: new FormControl(bonTvxRawValue.immatex),
      nomchauff: new FormControl(bonTvxRawValue.nomchauff),
      numpermis: new FormControl(bonTvxRawValue.numpermis),
      etab: new FormControl(bonTvxRawValue.etab),
      compteur: new FormControl(bonTvxRawValue.compteur),
      cptorg: new FormControl(bonTvxRawValue.cptorg),
      cdtyptr: new FormControl(bonTvxRawValue.cdtyptr),
      decstat: new FormControl(bonTvxRawValue.decstat),
      testeur: new FormControl(bonTvxRawValue.testeur),
      motifdep: new FormControl(bonTvxRawValue.motifdep),
      cdtypmnt: new FormControl(bonTvxRawValue.cdtypmnt),
      datsorprev: new FormControl(bonTvxRawValue.datsorprev),
      datmnqdu: new FormControl(bonTvxRawValue.datmnqdu),
      datmnqau: new FormControl(bonTvxRawValue.datmnqau),
      datentant: new FormControl(bonTvxRawValue.datentant),
      codstat: new FormControl(bonTvxRawValue.codstat),
      datvld: new FormControl(bonTvxRawValue.datvld),
      observ1: new FormControl(bonTvxRawValue.observ1),
      testeur1: new FormControl(bonTvxRawValue.testeur1),
      validag: new FormControl(bonTvxRawValue.validag),
      datsais: new FormControl(bonTvxRawValue.datsais),
    });
  }

  getBonTvx(form: BonTvxFormGroup): IBonTvx | NewBonTvx {
    return form.getRawValue() as IBonTvx | NewBonTvx;
  }

  resetForm(form: BonTvxFormGroup, bonTvx: BonTvxFormGroupInput): void {
    const bonTvxRawValue = { ...this.getFormDefaults(), ...bonTvx };
    form.reset(
      {
        ...bonTvxRawValue,
        id: { value: bonTvxRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): BonTvxFormDefaults {
    return {
      id: null,
    };
  }
}
