import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { LigneFormService, LigneFormGroup } from './ligne-form.service';
import { ILigne } from '../ligne.model';
import { LigneService } from '../service/ligne.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-ligne-update',
  templateUrl: './ligne-update.component.html',
})
export class LigneUpdateComponent implements OnInit {
  isSaving = false;
  ligne: ILigne | null = null;

  editForm: LigneFormGroup = this.ligneFormService.createLigneFormGroup();

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected ligneService: LigneService,
    protected ligneFormService: LigneFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ligne }) => {
      this.ligne = ligne;
      if (ligne) {
        this.updateForm(ligne);
      }
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('departdbApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ligne = this.ligneFormService.getLigne(this.editForm);
    if (ligne.id !== null) {
      this.subscribeToSaveResponse(this.ligneService.update(ligne));
    } else {
      this.subscribeToSaveResponse(this.ligneService.create(ligne));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILigne>>): void {
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

  protected updateForm(ligne: ILigne): void {
    this.ligne = ligne;
    this.ligneFormService.resetForm(this.editForm, ligne);
  }
}
