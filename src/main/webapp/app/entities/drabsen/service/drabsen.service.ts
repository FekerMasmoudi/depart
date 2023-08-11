import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDrabsen, NewDrabsen } from '../drabsen.model';

export type PartialUpdateDrabsen = Partial<IDrabsen> & Pick<IDrabsen, 'id'>;

type RestOf<T extends IDrabsen | NewDrabsen> = Omit<T, 'databs'> & {
  databs?: string | null;
};

export type RestDrabsen = RestOf<IDrabsen>;

export type NewRestDrabsen = RestOf<NewDrabsen>;

export type PartialUpdateRestDrabsen = RestOf<PartialUpdateDrabsen>;

export type EntityResponseType = HttpResponse<IDrabsen>;
export type EntityArrayResponseType = HttpResponse<IDrabsen[]>;

@Injectable({ providedIn: 'root' })
export class DrabsenService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/drabsens');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(drabsen: NewDrabsen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(drabsen);
    return this.http
      .post<RestDrabsen>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(drabsen: IDrabsen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(drabsen);
    return this.http
      .put<RestDrabsen>(`${this.resourceUrl}/${this.getDrabsenIdentifier(drabsen)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(drabsen: PartialUpdateDrabsen): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(drabsen);
    return this.http
      .patch<RestDrabsen>(`${this.resourceUrl}/${this.getDrabsenIdentifier(drabsen)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDrabsen>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDrabsen[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDrabsenIdentifier(drabsen: Pick<IDrabsen, 'id'>): string {
    return drabsen.id;
  }

  compareDrabsen(o1: Pick<IDrabsen, 'id'> | null, o2: Pick<IDrabsen, 'id'> | null): boolean {
    return o1 && o2 ? this.getDrabsenIdentifier(o1) === this.getDrabsenIdentifier(o2) : o1 === o2;
  }

  addDrabsenToCollectionIfMissing<Type extends Pick<IDrabsen, 'id'>>(
    drabsenCollection: Type[],
    ...drabsensToCheck: (Type | null | undefined)[]
  ): Type[] {
    const drabsens: Type[] = drabsensToCheck.filter(isPresent);
    if (drabsens.length > 0) {
      const drabsenCollectionIdentifiers = drabsenCollection.map(drabsenItem => this.getDrabsenIdentifier(drabsenItem)!);
      const drabsensToAdd = drabsens.filter(drabsenItem => {
        const drabsenIdentifier = this.getDrabsenIdentifier(drabsenItem);
        if (drabsenCollectionIdentifiers.includes(drabsenIdentifier)) {
          return false;
        }
        drabsenCollectionIdentifiers.push(drabsenIdentifier);
        return true;
      });
      return [...drabsensToAdd, ...drabsenCollection];
    }
    return drabsenCollection;
  }

  protected convertDateFromClient<T extends IDrabsen | NewDrabsen | PartialUpdateDrabsen>(drabsen: T): RestOf<T> {
    return {
      ...drabsen,
      databs: drabsen.databs?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restDrabsen: RestDrabsen): IDrabsen {
    return {
      ...restDrabsen,
      databs: restDrabsen.databs ? dayjs(restDrabsen.databs) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDrabsen>): HttpResponse<IDrabsen> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDrabsen[]>): HttpResponse<IDrabsen[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
