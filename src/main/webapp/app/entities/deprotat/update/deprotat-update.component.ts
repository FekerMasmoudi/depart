import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { DeprotatFormService, DeprotatFormGroup } from './deprotat-form.service';
import { IDeprotat } from '../deprotat.model';
import { DeprotatService } from '../service/deprotat.service';
import { IDepart } from 'app/entities/depart/depart.model';
import { DepartService } from 'app/entities/depart/service/depart.service';

@Component({
  selector: 'jhi-deprotat-update',
  templateUrl: './deprotat-update.component.html',
})
export class DeprotatUpdateComponent implements OnInit {
  isSaving = false;
  deprotat: IDeprotat | null = null;

  departsSharedCollection: IDepart[] = [];

  editForm: DeprotatFormGroup = this.deprotatFormService.createDeprotatFormGroup();

  constructor(
    protected deprotatService: DeprotatService,
    protected deprotatFormService: DeprotatFormService,
    protected departService: DepartService,
    protected activatedRoute: ActivatedRoute
  ) {}

  compareDepart = (o1: IDepart | null, o2: IDepart | null): boolean => this.departService.compareDepart(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ deprotat }) => {
      this.deprotat = deprotat;
      if (deprotat) {
        this.updateForm(deprotat);
      }

      this.loadRelationshipsOptions();
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

    this.departsSharedCollection = this.departService.addDepartToCollectionIfMissing<IDepart>(
      this.departsSharedCollection,
      deprotat.depart
    );
  }

  protected loadRelationshipsOptions(): void {
    this.departService
      .query()
      .pipe(map((res: HttpResponse<IDepart[]>) => res.body ?? []))
      .pipe(map((departs: IDepart[]) => this.departService.addDepartToCollectionIfMissing<IDepart>(departs, this.deprotat?.depart)))
      .subscribe((departs: IDepart[]) => (this.departsSharedCollection = departs));
  }
}
