import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DrtypabFormService, DrtypabFormGroup } from './drtypab-form.service';
import { IDrtypab } from '../drtypab.model';
import { DrtypabService } from '../service/drtypab.service';

@Component({
  selector: 'jhi-drtypab-update',
  templateUrl: './drtypab-update.component.html',
})
export class DrtypabUpdateComponent implements OnInit {
  isSaving = false;
  drtypab: IDrtypab | null = null;

  editForm: DrtypabFormGroup = this.drtypabFormService.createDrtypabFormGroup();

  constructor(
    protected drtypabService: DrtypabService,
    protected drtypabFormService: DrtypabFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ drtypab }) => {
      this.drtypab = drtypab;
      if (drtypab) {
        this.updateForm(drtypab);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const drtypab = this.drtypabFormService.getDrtypab(this.editForm);
    if (drtypab.id !== null) {
      this.subscribeToSaveResponse(this.drtypabService.update(drtypab));
    } else {
      this.subscribeToSaveResponse(this.drtypabService.create(drtypab));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDrtypab>>): void {
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

  protected updateForm(drtypab: IDrtypab): void {
    this.drtypab = drtypab;
    this.drtypabFormService.resetForm(this.editForm, drtypab);
  }
}
