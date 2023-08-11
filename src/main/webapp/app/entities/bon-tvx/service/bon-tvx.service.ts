import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBonTvx, NewBonTvx } from '../bon-tvx.model';

export type PartialUpdateBonTvx = Partial<IBonTvx> & Pick<IBonTvx, 'id'>;

type RestOf<T extends IBonTvx | NewBonTvx> = Omit<
  T,
  | 'datbt'
  | 'datdt'
  | 'datft'
  | 'heurdb'
  | 'heurfi'
  | 'datsrt'
  | 'heursr'
  | 'datsorprev'
  | 'datmnqdu'
  | 'datmnqau'
  | 'datentant'
  | 'datvld'
  | 'datsais'
> & {
  datbt?: string | null;
  datdt?: string | null;
  datft?: string | null;
  heurdb?: string | null;
  heurfi?: string | null;
  datsrt?: string | null;
  heursr?: string | null;
  datsorprev?: string | null;
  datmnqdu?: string | null;
  datmnqau?: string | null;
  datentant?: string | null;
  datvld?: string | null;
  datsais?: string | null;
};

export type RestBonTvx = RestOf<IBonTvx>;

export type NewRestBonTvx = RestOf<NewBonTvx>;

export type PartialUpdateRestBonTvx = RestOf<PartialUpdateBonTvx>;

export type EntityResponseType = HttpResponse<IBonTvx>;
export type EntityArrayResponseType = HttpResponse<IBonTvx[]>;

@Injectable({ providedIn: 'root' })
export class BonTvxService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bon-tvxes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(bonTvx: NewBonTvx): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bonTvx);
    return this.http
      .post<RestBonTvx>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(bonTvx: IBonTvx): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bonTvx);
    return this.http
      .put<RestBonTvx>(`${this.resourceUrl}/${this.getBonTvxIdentifier(bonTvx)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(bonTvx: PartialUpdateBonTvx): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bonTvx);
    return this.http
      .patch<RestBonTvx>(`${this.resourceUrl}/${this.getBonTvxIdentifier(bonTvx)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestBonTvx>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBonTvx[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBonTvxIdentifier(bonTvx: Pick<IBonTvx, 'id'>): string {
    return bonTvx.id;
  }

  compareBonTvx(o1: Pick<IBonTvx, 'id'> | null, o2: Pick<IBonTvx, 'id'> | null): boolean {
    return o1 && o2 ? this.getBonTvxIdentifier(o1) === this.getBonTvxIdentifier(o2) : o1 === o2;
  }

  addBonTvxToCollectionIfMissing<Type extends Pick<IBonTvx, 'id'>>(
    bonTvxCollection: Type[],
    ...bonTvxesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const bonTvxes: Type[] = bonTvxesToCheck.filter(isPresent);
    if (bonTvxes.length > 0) {
      const bonTvxCollectionIdentifiers = bonTvxCollection.map(bonTvxItem => this.getBonTvxIdentifier(bonTvxItem)!);
      const bonTvxesToAdd = bonTvxes.filter(bonTvxItem => {
        const bonTvxIdentifier = this.getBonTvxIdentifier(bonTvxItem);
        if (bonTvxCollectionIdentifiers.includes(bonTvxIdentifier)) {
          return false;
        }
        bonTvxCollectionIdentifiers.push(bonTvxIdentifier);
        return true;
      });
      return [...bonTvxesToAdd, ...bonTvxCollection];
    }
    return bonTvxCollection;
  }

  protected convertDateFromClient<T extends IBonTvx | NewBonTvx | PartialUpdateBonTvx>(bonTvx: T): RestOf<T> {
    return {
      ...bonTvx,
      datbt: bonTvx.datbt?.format(DATE_FORMAT) ?? null,
      datdt: bonTvx.datdt?.format(DATE_FORMAT) ?? null,
      datft: bonTvx.datft?.format(DATE_FORMAT) ?? null,
      heurdb: bonTvx.heurdb?.format(DATE_FORMAT) ?? null,
      heurfi: bonTvx.heurfi?.format(DATE_FORMAT) ?? null,
      datsrt: bonTvx.datsrt?.format(DATE_FORMAT) ?? null,
      heursr: bonTvx.heursr?.format(DATE_FORMAT) ?? null,
      datsorprev: bonTvx.datsorprev?.format(DATE_FORMAT) ?? null,
      datmnqdu: bonTvx.datmnqdu?.format(DATE_FORMAT) ?? null,
      datmnqau: bonTvx.datmnqau?.format(DATE_FORMAT) ?? null,
      datentant: bonTvx.datentant?.format(DATE_FORMAT) ?? null,
      datvld: bonTvx.datvld?.format(DATE_FORMAT) ?? null,
      datsais: bonTvx.datsais?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restBonTvx: RestBonTvx): IBonTvx {
    return {
      ...restBonTvx,
      datbt: restBonTvx.datbt ? dayjs(restBonTvx.datbt) : undefined,
      datdt: restBonTvx.datdt ? dayjs(restBonTvx.datdt) : undefined,
      datft: restBonTvx.datft ? dayjs(restBonTvx.datft) : undefined,
      heurdb: restBonTvx.heurdb ? dayjs(restBonTvx.heurdb) : undefined,
      heurfi: restBonTvx.heurfi ? dayjs(restBonTvx.heurfi) : undefined,
      datsrt: restBonTvx.datsrt ? dayjs(restBonTvx.datsrt) : undefined,
      heursr: restBonTvx.heursr ? dayjs(restBonTvx.heursr) : undefined,
      datsorprev: restBonTvx.datsorprev ? dayjs(restBonTvx.datsorprev) : undefined,
      datmnqdu: restBonTvx.datmnqdu ? dayjs(restBonTvx.datmnqdu) : undefined,
      datmnqau: restBonTvx.datmnqau ? dayjs(restBonTvx.datmnqau) : undefined,
      datentant: restBonTvx.datentant ? dayjs(restBonTvx.datentant) : undefined,
      datvld: restBonTvx.datvld ? dayjs(restBonTvx.datvld) : undefined,
      datsais: restBonTvx.datsais ? dayjs(restBonTvx.datsais) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBonTvx>): HttpResponse<IBonTvx> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBonTvx[]>): HttpResponse<IBonTvx[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
