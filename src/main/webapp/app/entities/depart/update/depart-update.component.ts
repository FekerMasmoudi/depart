import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DepartFormService, DepartFormGroup } from './depart-form.service';
import { IDepart } from '../depart.model';
import { DepartService } from '../service/depart.service';

@Component({
  selector: 'jhi-depart-update',
  templateUrl: './depart-update.component.html',
})
export class DepartUpdateComponent implements OnInit {
  isSaving = false;
  depart: IDepart | null = null;

  editForm: DepartFormGroup = this.departFormService.createDepartFormGroup();

  constructor(
    protected departService: DepartService,
    protected departFormService: DepartFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ depart }) => {
      this.depart = depart;
      if (depart) {
        this.updateForm(depart);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const depart = this.departFormService.getDepart(this.editForm);
    if (depart.id !== null) {
      this.subscribeToSaveResponse(this.departService.update(depart));
    } else {
      this.subscribeToSaveResponse(this.departService.create(depart));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDepart>>): void {
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

  protected updateForm(depart: IDepart): void {
    this.depart = depart;
    this.departFormService.resetForm(this.editForm, depart);
  }
}
