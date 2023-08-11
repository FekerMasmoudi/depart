import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IItineraire, NewItineraire } from '../itineraire.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IItineraire for edit and NewItineraireFormGroupInput for create.
 */
type ItineraireFormGroupInput = IItineraire | PartialWithRequiredKeyOf<NewItineraire>;

type ItineraireFormDefaults = Pick<NewItineraire, 'id'>;

type ItineraireFormGroupContent = {
  id: FormControl<IItineraire['id'] | NewItineraire['id']>;
  deccent: FormControl<IItineraire['deccent']>;
  decagenc: FormControl<IItineraire['decagenc']>;
  denumli: FormControl<IItineraire['denumli']>;
  decstat: FormControl<IItineraire['decstat']>;
  denumlg: FormControl<IItineraire['denumlg']>;
  dekmsta: FormControl<IItineraire['dekmsta']>;
  dedurtr: FormControl<IItineraire['dedurtr']>;
  deescale: FormControl<IItineraire['deescale']>;
  embra: FormControl<IItineraire['embra']>;
  section: FormControl<IItineraire['section']>;
  sens: FormControl<IItineraire['sens']>;
  dectyst: FormControl<IItineraire['dectyst']>;
};

export type ItineraireFormGroup = FormGroup<ItineraireFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class ItineraireFormService {
  createItineraireFormGroup(itineraire: ItineraireFormGroupInput = { id: null }): ItineraireFormGroup {
    const itineraireRawValue = {
      ...this.getFormDefaults(),
      ...itineraire,
    };
    return new FormGroup<ItineraireFormGroupContent>({
      id: new FormControl(
        { value: itineraireRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      deccent: new FormControl(itineraireRawValue.deccent),
      decagenc: new FormControl(itineraireRawValue.decagenc, {
        validators: [Validators.required],
      }),
      denumli: new FormControl(itineraireRawValue.denumli, {
        validators: [Validators.required],
      }),
      decstat: new FormControl(itineraireRawValue.decstat, {
        validators: [Validators.required],
      }),
      denumlg: new FormControl(itineraireRawValue.denumlg, {
        validators: [Validators.required],
      }),
      dekmsta: new FormControl(itineraireRawValue.dekmsta),
      dedurtr: new FormControl(itineraireRawValue.dedurtr),
      deescale: new FormControl(itineraireRawValue.deescale),
      embra: new FormControl(itineraireRawValue.embra),
      section: new FormControl(itineraireRawValue.section),
      sens: new FormControl(itineraireRawValue.sens),
      dectyst: new FormControl(itineraireRawValue.dectyst),
    });
  }

  getItineraire(form: ItineraireFormGroup): IItineraire | NewItineraire {
    return form.getRawValue() as IItineraire | NewItineraire;
  }

  resetForm(form: ItineraireFormGroup, itineraire: ItineraireFormGroupInput): void {
    const itineraireRawValue = { ...this.getFormDefaults(), ...itineraire };
    form.reset(
      {
        ...itineraireRawValue,
        id: { value: itineraireRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): ItineraireFormDefaults {
    return {
      id: null,
    };
  }
}
