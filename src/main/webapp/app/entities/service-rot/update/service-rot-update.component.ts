import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ServiceRotFormService, ServiceRotFormGroup } from './service-rot-form.service';
import { IServiceRot } from '../service-rot.model';
import { ServiceRotService } from '../service/service-rot.service';

@Component({
  selector: 'jhi-service-rot-update',
  templateUrl: './service-rot-update.component.html',
})
export class ServiceRotUpdateComponent implements OnInit {
  isSaving = false;
  serviceRot: IServiceRot | null = null;

  editForm: ServiceRotFormGroup = this.serviceRotFormService.createServiceRotFormGroup();

  constructor(
    protected serviceRotService: ServiceRotService,
    protected serviceRotFormService: ServiceRotFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ serviceRot }) => {
      this.serviceRot = serviceRot;
      if (serviceRot) {
        this.updateForm(serviceRot);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const serviceRot = this.serviceRotFormService.getServiceRot(this.editForm);
    if (serviceRot.id !== null) {
      this.subscribeToSaveResponse(this.serviceRotService.update(serviceRot));
    } else {
      this.subscribeToSaveResponse(this.serviceRotService.create(serviceRot));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IServiceRot>>): void {
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

  protected updateForm(serviceRot: IServiceRot): void {
    this.serviceRot = serviceRot;
    this.serviceRotFormService.resetForm(this.editForm, serviceRot);
  }
}
