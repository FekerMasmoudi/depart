import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Data, ParamMap, Router } from '@angular/router';
import { combineLatest, filter, finalize, Observable, switchMap, tap } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IDepart } from '../depart.model';

import { ITEMS_PER_PAGE, PAGE_HEADER, TOTAL_COUNT_RESPONSE_HEADER } from 'app/config/pagination.constants';
import { ASC, DESC, SORT, ITEM_DELETED_EVENT, DEFAULT_SORT_DATA } from 'app/config/navigation.constants';
import { EntityArrayResponseType, DepartService } from '../service/depart.service';
import { DepartDeleteDialogComponent } from '../delete/depart-delete-dialog.component';
import { AffectagentService } from 'app/entities/affectagent/service/affectagent.service';
import { MachineService } from 'app/entities/machine/service/machine.service';
import { MotifaService } from 'app/entities/motifa/service/motifa.service';
import { DrabsenService } from 'app/entities/drabsen/service/drabsen.service';
import { ModifService } from 'app/entities/modif/service/modif.service';
import { DepartFormGroup, DepartFormService } from '../update/depart-form.service';
import { ListMotifaComponent } from '../list-motifa/list-motifa.component';
import { MatDialog } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { FormsModule } from '@angular/forms';
import { IMotifa } from 'app/entities/motifa/motifa.model';
import { IDeprotat } from 'app/entities/deprotat/deprotat.model';
import { DeprotatService } from 'app/entities/deprotat/service/deprotat.service';

import { DeprotatUpdateComponent } from 'app/entities/deprotat/update/deprotat-update.component';

@Component({
  selector: 'jhi-depart',
  templateUrl: './depart.component.html',
})
export class DepartComponent implements OnInit {
  @ViewChild(DeprotatUpdateComponent) y!: DeprotatUpdateComponent;
  departs?: IDepart[];
  isLoading = false;

  predicate = 'id';
  ascending = true;

  itemsPerPage = ITEMS_PER_PAGE;
  totalItems = 0;
  page = 1;

  isSaving = false;
  depart: IDepart | null = null;
  takaza!: IDepart;
  takaza1!: IDeprotat;
  arrayInput: IDepart[] = [];

  id!: String;
  libmotif!: String;

  editForm: DepartFormGroup = this.departFormService.createDepartFormGroup();

  constructor(
    protected departService: DepartService,
    public dialog: MatDialog,
    protected departFormService: DepartFormService,
    protected affectagentService: AffectagentService,
    protected machineService: MachineService,
    protected motifaService: MotifaService,
    protected drabsenService: DrabsenService,
    protected modifService: ModifService,
    protected activatedRoute: ActivatedRoute,
    protected deprotatService: DeprotatService,
    public router: Router,
    protected modalService: NgbModal
  ) {}

  trackId = (_index: number, item: IDepart): string => this.departService.getDepartIdentifier(item);

  ngOnInit(): void {
    this.load();
  }

  delete(depart: IDepart): void {
    const modalRef = this.modalService.open(DepartDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.depart = depart;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed
      .pipe(
        filter(reason => reason === ITEM_DELETED_EVENT),
        switchMap(() => this.loadFromBackendWithRouteInformations())
      )
      .subscribe({
        next: (res: EntityArrayResponseType) => {
          this.onResponseSuccess(res);
        },
      });
  }

  changeMatric(event: any): void {
    //alert(event.matric);

    this.isSaving = true;
    const lineDepart: IDepart = {
      id: event.id,
      cd1: event.cd1,
      cd2: event.cd2,
      cd3: event.cd3,
      deccent: event.deccent,
      decagenc: event.decagenc,
      decserv: event.decserv,
      decsean: event.decsean,
      decoper: event.decoper,
      dedated: event.dedated,
      matric: event.matric,
      matric1: event.matric1,
      cdmac: event.cdmac,
      deampli: event.deampli,
      deannul: event.deannul,
      denumdp: event.denumdp,
      decclot: event.decclot,
      deheuaa: event.deheuaa,
      deheudr: event.deheudr,
      deheufs: event.deheufs,
      deheupd: event.deheupd,
      deheups: event.deheups,
      decmotifch: event.decmotifch,
      decmotifcha: event.decmotifcha,
      decmotifre: event.decmotifre,
      decmotifrea: event.decmotifrea,
      deetat: event.deetat,
      denbrro: event.denbrro,
      execute: event.execute,
      motifa: event.motifa,
      nbrevoy: event.nbrevoy,
      observ: event.observ,
      obsind: event.obsind,
      recettes: event.recettes,
      vldroul: event.vldroul,
    };

    if (lineDepart.id != null) {
      this.subscribeToSaveResponse(this.departService.update(lineDepart));
    } /*else {
      this.subscribeToSaveResponse(this.departService.create(depart));
    }*/
    console.log(this.arrayInput);
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

  previousState(): void {
    window.history.back();
  }

  load(): void {
    this.loadFromBackendWithRouteInformations().subscribe({
      next: (res: EntityArrayResponseType) => {
        this.onResponseSuccess(res);
      },
    });
  }

  navigateToWithComponentValues(): void {
    this.handleNavigation(this.page, this.predicate, this.ascending);
  }

  navigateToPage(page = this.page): void {
    this.handleNavigation(page, this.predicate, this.ascending);
  }

  protected loadFromBackendWithRouteInformations(): Observable<EntityArrayResponseType> {
    return combineLatest([this.activatedRoute.queryParamMap, this.activatedRoute.data]).pipe(
      tap(([params, data]) => this.fillComponentAttributeFromRoute(params, data)),
      switchMap(() => this.queryBackend(this.page, this.predicate, this.ascending))
    );
  }

  protected fillComponentAttributeFromRoute(params: ParamMap, data: Data): void {
    const page = params.get(PAGE_HEADER);
    this.page = +(page ?? 1);
    const sort = (params.get(SORT) ?? data[DEFAULT_SORT_DATA]).split(',');
    this.predicate = sort[0];
    this.ascending = sort[1] === ASC;
  }

  protected onResponseSuccess(response: EntityArrayResponseType): void {
    this.fillComponentAttributesFromResponseHeader(response.headers);
    const dataFromBody = this.fillComponentAttributesFromResponseBody(response.body);
    this.departs = dataFromBody;
  }

  protected fillComponentAttributesFromResponseBody(data: IDepart[] | null): IDepart[] {
    return data ?? [];
  }

  protected fillComponentAttributesFromResponseHeader(headers: HttpHeaders): void {
    this.totalItems = Number(headers.get(TOTAL_COUNT_RESPONSE_HEADER));
  }

  protected queryBackend(page?: number, predicate?: string, ascending?: boolean): Observable<EntityArrayResponseType> {
    this.isLoading = true;
    const pageToLoad: number = page ?? 1;
    const queryObject = {
      page: pageToLoad - 1,
      size: this.itemsPerPage,
      sort: this.getSortQueryParam(predicate, ascending),
    };
    return this.departService.query(queryObject).pipe(tap(() => (this.isLoading = false)));
  }

  protected handleNavigation(page = this.page, predicate?: string, ascending?: boolean): void {
    const queryParamsObj = {
      page,
      size: this.itemsPerPage,
      sort: this.getSortQueryParam(predicate, ascending),
    };

    this.router.navigate(['./'], {
      relativeTo: this.activatedRoute,
      queryParams: queryParamsObj,
    });
  }

  protected getSortQueryParam(predicate = this.predicate, ascending = this.ascending): string[] {
    const ascendingQueryParam = ascending ? ASC : DESC;
    if (predicate === '') {
      return [];
    } else {
      return [predicate + ',' + ascendingQueryParam];
    }
  }
  openDialog(event: IDepart): void {
    this.takaza = event;
    const dialogRef = this.dialog.open(ListMotifaComponent, {
      data: {
        id: this.id,
        libmotif: this.libmotif,
      },
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //this.type = result;

      if (result.id != null) {
        this.takaza.deannul = '1';
        this.takaza.execute = '0';
        //this.takaza1.rannul = '1';
        //this.takaza1.motifa = 1;
        this.takaza.motifa = result.id;
        if (this.takaza.id != null) {
          //&& this.takaza1.id != null
          this.subscribeToSaveResponse(this.departService.update(this.takaza));
          //this.y.subscribeToSaveResponse(this.deprotatService.update(this.takaza1));
        }
        this.ngOnInit();
      }
      console.log(result);
    });
  }

  checkValid(event: IDepart): void {
    this.takaza = event;

    if (this.takaza.id != null) {
      this.takaza.deannul = '0';
      this.takaza.execute = '1';
      this.takaza.motifa = null;
      this.subscribeToSaveResponse(this.departService.update(this.takaza));
      this.ngOnInit();
    }
  }
}
