import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ModifFormService, ModifFormGroup } from './modif-form.service';
import { IModif } from '../modif.model';
import { ModifService } from '../service/modif.service';

@Component({
  selector: 'jhi-modif-update',
  templateUrl: './modif-update.component.html',
})
export class ModifUpdateComponent implements OnInit {
  isSaving = false;
  modif: IModif | null = null;

  editForm: ModifFormGroup = this.modifFormService.createModifFormGroup();

  constructor(
    protected modifService: ModifService,
    protected modifFormService: ModifFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ modif }) => {
      this.modif = modif;
      if (modif) {
        this.updateForm(modif);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const modif = this.modifFormService.getModif(this.editForm);
    if (modif.id !== null) {
      this.subscribeToSaveResponse(this.modifService.update(modif));
    } else {
      this.subscribeToSaveResponse(this.modifService.create(modif));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IModif>>): void {
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

  protected updateForm(modif: IModif): void {
    this.modif = modif;
    this.modifFormService.resetForm(this.editForm, modif);
  }
}
