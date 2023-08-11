import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { MotifchservFormService, MotifchservFormGroup } from './motifchserv-form.service';
import { IMotifchserv } from '../motifchserv.model';
import { MotifchservService } from '../service/motifchserv.service';

@Component({
  selector: 'jhi-motifchserv-update',
  templateUrl: './motifchserv-update.component.html',
})
export class MotifchservUpdateComponent implements OnInit {
  isSaving = false;
  motifchserv: IMotifchserv | null = null;

  editForm: MotifchservFormGroup = this.motifchservFormService.createMotifchservFormGroup();

  constructor(
    protected motifchservService: MotifchservService,
    protected motifchservFormService: MotifchservFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ motifchserv }) => {
      this.motifchserv = motifchserv;
      if (motifchserv) {
        this.updateForm(motifchserv);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const motifchserv = this.motifchservFormService.getMotifchserv(this.editForm);
    if (motifchserv.id !== null) {
      this.subscribeToSaveResponse(this.motifchservService.update(motifchserv));
    } else {
      this.subscribeToSaveResponse(this.motifchservService.create(motifchserv));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMotifchserv>>): void {
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

  protected updateForm(motifchserv: IMotifchserv): void {
    this.motifchserv = motifchserv;
    this.motifchservFormService.resetForm(this.editForm, motifchserv);
  }
}
