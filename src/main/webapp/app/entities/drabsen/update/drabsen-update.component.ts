import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DrabsenFormService, DrabsenFormGroup } from './drabsen-form.service';
import { IDrabsen } from '../drabsen.model';
import { DrabsenService } from '../service/drabsen.service';

@Component({
  selector: 'jhi-drabsen-update',
  templateUrl: './drabsen-update.component.html',
})
export class DrabsenUpdateComponent implements OnInit {
  isSaving = false;
  drabsen: IDrabsen | null = null;

  editForm: DrabsenFormGroup = this.drabsenFormService.createDrabsenFormGroup();

  constructor(
    protected drabsenService: DrabsenService,
    protected drabsenFormService: DrabsenFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ drabsen }) => {
      this.drabsen = drabsen;
      if (drabsen) {
        this.updateForm(drabsen);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const drabsen = this.drabsenFormService.getDrabsen(this.editForm);
    if (drabsen.id !== null) {
      this.subscribeToSaveResponse(this.drabsenService.update(drabsen));
    } else {
      this.subscribeToSaveResponse(this.drabsenService.create(drabsen));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDrabsen>>): void {
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

  protected updateForm(drabsen: IDrabsen): void {
    this.drabsen = drabsen;
    this.drabsenFormService.resetForm(this.editForm, drabsen);
  }
}
