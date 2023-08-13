import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { BonTvxFormService, BonTvxFormGroup } from './bon-tvx-form.service';
import { IBonTvx } from '../bon-tvx.model';
import { BonTvxService } from '../service/bon-tvx.service';

@Component({
  selector: 'jhi-bon-tvx-update',
  templateUrl: './bon-tvx-update.component.html',
})
export class BonTvxUpdateComponent implements OnInit {
  isSaving = false;
  bonTvx: IBonTvx | null = null;

  editForm: BonTvxFormGroup = this.bonTvxFormService.createBonTvxFormGroup();

  constructor(
    protected bonTvxService: BonTvxService,
    protected bonTvxFormService: BonTvxFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bonTvx }) => {
      this.bonTvx = bonTvx;
      if (bonTvx) {
        this.updateForm(bonTvx);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bonTvx = this.bonTvxFormService.getBonTvx(this.editForm);
    if (bonTvx.id !== null) {
      this.subscribeToSaveResponse(this.bonTvxService.update(bonTvx));
    } else {
      this.subscribeToSaveResponse(this.bonTvxService.create(bonTvx));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBonTvx>>): void {
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

  protected updateForm(bonTvx: IBonTvx): void {
    this.bonTvx = bonTvx;
    this.bonTvxFormService.resetForm(this.editForm, bonTvx);
  }
}
