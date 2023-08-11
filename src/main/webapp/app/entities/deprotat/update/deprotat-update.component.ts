import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DeprotatFormService, DeprotatFormGroup } from './deprotat-form.service';
import { IDeprotat } from '../deprotat.model';
import { DeprotatService } from '../service/deprotat.service';

@Component({
  selector: 'jhi-deprotat-update',
  templateUrl: './deprotat-update.component.html',
})
export class DeprotatUpdateComponent implements OnInit {
  isSaving = false;
  deprotat: IDeprotat | null = null;

  editForm: DeprotatFormGroup = this.deprotatFormService.createDeprotatFormGroup();

  constructor(
    protected deprotatService: DeprotatService,
    protected deprotatFormService: DeprotatFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deprotat }) => {
      this.deprotat = deprotat;
      if (deprotat) {
        this.updateForm(deprotat);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const deprotat = this.deprotatFormService.getDeprotat(this.editForm);
    if (deprotat.id !== null) {
      this.subscribeToSaveResponse(this.deprotatService.update(deprotat));
    } else {
      this.subscribeToSaveResponse(this.deprotatService.create(deprotat));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDeprotat>>): void {
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

  protected updateForm(deprotat: IDeprotat): void {
    this.deprotat = deprotat;
    this.deprotatFormService.resetForm(this.editForm, deprotat);
  }
}
