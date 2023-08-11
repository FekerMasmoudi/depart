import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { RotRservFormService, RotRservFormGroup } from './rot-rserv-form.service';
import { IRotRserv } from '../rot-rserv.model';
import { RotRservService } from '../service/rot-rserv.service';

@Component({
  selector: 'jhi-rot-rserv-update',
  templateUrl: './rot-rserv-update.component.html',
})
export class RotRservUpdateComponent implements OnInit {
  isSaving = false;
  rotRserv: IRotRserv | null = null;

  editForm: RotRservFormGroup = this.rotRservFormService.createRotRservFormGroup();

  constructor(
    protected rotRservService: RotRservService,
    protected rotRservFormService: RotRservFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ rotRserv }) => {
      this.rotRserv = rotRserv;
      if (rotRserv) {
        this.updateForm(rotRserv);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const rotRserv = this.rotRservFormService.getRotRserv(this.editForm);
    if (rotRserv.id !== null) {
      this.subscribeToSaveResponse(this.rotRservService.update(rotRserv));
    } else {
      this.subscribeToSaveResponse(this.rotRservService.create(rotRserv));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRotRserv>>): void {
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

  protected updateForm(rotRserv: IRotRserv): void {
    this.rotRserv = rotRserv;
    this.rotRservFormService.resetForm(this.editForm, rotRserv);
  }
}
