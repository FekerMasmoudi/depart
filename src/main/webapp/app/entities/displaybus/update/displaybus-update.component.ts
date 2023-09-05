import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { DisplaybusFormService, DisplaybusFormGroup } from './displaybus-form.service';
import { IDisplaybus } from '../displaybus.model';
import { DisplaybusService } from '../service/displaybus.service';

@Component({
  selector: 'jhi-displaybus-update',
  templateUrl: './displaybus-update.component.html',
})
export class DisplaybusUpdateComponent implements OnInit {
  isSaving = false;
  displaybus: IDisplaybus | null = null;

  editForm: DisplaybusFormGroup = this.displaybusFormService.createDisplaybusFormGroup();

  constructor(
    protected displaybusService: DisplaybusService,
    protected displaybusFormService: DisplaybusFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ displaybus }) => {
      this.displaybus = displaybus;
      if (displaybus) {
        this.updateForm(displaybus);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const displaybus = this.displaybusFormService.getDisplaybus(this.editForm);
    if (displaybus.id !== null) {
      this.subscribeToSaveResponse(this.displaybusService.update(displaybus));
    } else {
      this.subscribeToSaveResponse(this.displaybusService.create(displaybus));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDisplaybus>>): void {
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

  protected updateForm(displaybus: IDisplaybus): void {
    this.displaybus = displaybus;
    this.displaybusFormService.resetForm(this.editForm, displaybus);
  }
}
