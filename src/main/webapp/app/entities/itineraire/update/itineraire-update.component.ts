import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ItineraireFormService, ItineraireFormGroup } from './itineraire-form.service';
import { IItineraire } from '../itineraire.model';
import { ItineraireService } from '../service/itineraire.service';

@Component({
  selector: 'jhi-itineraire-update',
  templateUrl: './itineraire-update.component.html',
})
export class ItineraireUpdateComponent implements OnInit {
  isSaving = false;
  itineraire: IItineraire | null = null;

  editForm: ItineraireFormGroup = this.itineraireFormService.createItineraireFormGroup();

  constructor(
    protected itineraireService: ItineraireService,
    protected itineraireFormService: ItineraireFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ itineraire }) => {
      this.itineraire = itineraire;
      if (itineraire) {
        this.updateForm(itineraire);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const itineraire = this.itineraireFormService.getItineraire(this.editForm);
    if (itineraire.id !== null) {
      this.subscribeToSaveResponse(this.itineraireService.update(itineraire));
    } else {
      this.subscribeToSaveResponse(this.itineraireService.create(itineraire));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IItineraire>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(itineraire: IItineraire): void {
    this.itineraire = itineraire;
    this.itineraireFormService.resetForm(this.editForm, itineraire);
  }
}
