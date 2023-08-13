import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { BordereauFormService, BordereauFormGroup } from './bordereau-form.service';
import { IBordereau } from '../bordereau.model';
import { BordereauService } from '../service/bordereau.service';

@Component({
  selector: 'jhi-bordereau-update',
  templateUrl: './bordereau-update.component.html',
})
export class BordereauUpdateComponent implements OnInit {
  isSaving = false;
  bordereau: IBordereau | null = null;

  editForm: BordereauFormGroup = this.bordereauFormService.createBordereauFormGroup();

  constructor(
    protected bordereauService: BordereauService,
    protected bordereauFormService: BordereauFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bordereau }) => {
      this.bordereau = bordereau;
      if (bordereau) {
        this.updateForm(bordereau);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bordereau = this.bordereauFormService.getBordereau(this.editForm);
    if (bordereau.id !== null) {
      this.subscribeToSaveResponse(this.bordereauService.update(bordereau));
    } else {
      this.subscribeToSaveResponse(this.bordereauService.create(bordereau));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBordereau>>): void {
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

  protected updateForm(bordereau: IBordereau): void {
    this.bordereau = bordereau;
    this.bordereauFormService.resetForm(this.editForm, bordereau);
  }
}
