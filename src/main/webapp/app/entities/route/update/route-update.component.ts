import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { RouteFormService, RouteFormGroup } from './route-form.service';
import { IRoute } from '../route.model';
import { RouteService } from '../service/route.service';

@Component({
  selector: 'jhi-route-update',
  templateUrl: './route-update.component.html',
})
export class RouteUpdateComponent implements OnInit {
  isSaving = false;
  route: IRoute | null = null;

  editForm: RouteFormGroup = this.routeFormService.createRouteFormGroup();

  constructor(
    protected routeService: RouteService,
    protected routeFormService: RouteFormService,
    protected activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ route }) => {
      this.route = route;
      if (route) {
        this.updateForm(route);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const route = this.routeFormService.getRoute(this.editForm);
    if (route.id !== null) {
      this.subscribeToSaveResponse(this.routeService.update(route));
    } else {
      this.subscribeToSaveResponse(this.routeService.create(route));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRoute>>): void {
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

  protected updateForm(route: IRoute): void {
    this.route = route;
    this.routeFormService.resetForm(this.editForm, route);
  }
}
