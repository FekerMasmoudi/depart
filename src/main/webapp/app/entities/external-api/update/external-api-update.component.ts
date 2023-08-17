import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ExternalApiFormService, ExternalApiFormGroup } from './external-api-form.service';
import { IExternalApi } from '../external-api.model';
import { ExternalApiService } from '../service/external-api.service';

@Component({
  selector: 'jhi-external-api-update',
  templateUrl: './external-api-update.component.html',
})
export class ExternalApiUpdateComponent implements OnInit {
  isSaving = false;
  externalApi: IExternalApi | null = null;

  editForm: ExternalApiFormGroup = this.externalApiFormService.createExternalApiFormGroup();

  constructor(
    protected externalApiService: ExternalApiService,
    protected externalApiFormService: ExternalApiFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ externalApi }) => {
      this.externalApi = externalApi;
      if (externalApi) {
        this.updateForm(externalApi);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const externalApi = this.externalApiFormService.getExternalApi(this.editForm);
    if (externalApi.id !== null) {
      this.subscribeToSaveResponse(this.externalApiService.update(externalApi));
    } else {
      this.subscribeToSaveResponse(this.externalApiService.create(externalApi));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IExternalApi>>): void {
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

  protected updateForm(externalApi: IExternalApi): void {
    this.externalApi = externalApi;
    this.externalApiFormService.resetForm(this.editForm, externalApi);
  }
}
