import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IExternalApi, NewExternalApi } from '../external-api.model';

export type PartialUpdateExternalApi = Partial<IExternalApi> & Pick<IExternalApi, 'id'>;

type RestOf<T extends IExternalApi | NewExternalApi> = Omit<T, 'datecreatedt' | 'lastupdatedm' | 'createdatet' | 'lastupdatete'> & {
  datecreatedt?: string | null;
  lastupdatedm?: string | null;
  createdatet?: string | null;
  lastupdatete?: string | null;
};

export type RestExternalApi = RestOf<IExternalApi>;

export type NewRestExternalApi = RestOf<NewExternalApi>;

export type PartialUpdateRestExternalApi = RestOf<PartialUpdateExternalApi>;

export type EntityResponseType = HttpResponse<IExternalApi>;
export type EntityArrayResponseType = HttpResponse<IExternalApi[]>;

@Injectable({ providedIn: 'root' })
export class ExternalApiService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/external-apis');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(externalApi: NewExternalApi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(externalApi);
    return this.http
      .post<RestExternalApi>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(externalApi: IExternalApi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(externalApi);
    return this.http
      .put<RestExternalApi>(`${this.resourceUrl}/${this.getExternalApiIdentifier(externalApi)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(externalApi: PartialUpdateExternalApi): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(externalApi);
    return this.http
      .patch<RestExternalApi>(`${this.resourceUrl}/${this.getExternalApiIdentifier(externalApi)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestExternalApi>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestExternalApi[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getExternalApiIdentifier(externalApi: Pick<IExternalApi, 'id'>): string {
    return externalApi.id;
  }

  compareExternalApi(o1: Pick<IExternalApi, 'id'> | null, o2: Pick<IExternalApi, 'id'> | null): boolean {
    return o1 && o2 ? this.getExternalApiIdentifier(o1) === this.getExternalApiIdentifier(o2) : o1 === o2;
  }

  addExternalApiToCollectionIfMissing<Type extends Pick<IExternalApi, 'id'>>(
    externalApiCollection: Type[],
    ...externalApisToCheck: (Type | null | undefined)[]
  ): Type[] {
    const externalApis: Type[] = externalApisToCheck.filter(isPresent);
    if (externalApis.length > 0) {
      const externalApiCollectionIdentifiers = externalApiCollection.map(
        externalApiItem => this.getExternalApiIdentifier(externalApiItem)!
      );
      const externalApisToAdd = externalApis.filter(externalApiItem => {
        const externalApiIdentifier = this.getExternalApiIdentifier(externalApiItem);
        if (externalApiCollectionIdentifiers.includes(externalApiIdentifier)) {
          return false;
        }
        externalApiCollectionIdentifiers.push(externalApiIdentifier);
        return true;
      });
      return [...externalApisToAdd, ...externalApiCollection];
    }
    return externalApiCollection;
  }

  protected convertDateFromClient<T extends IExternalApi | NewExternalApi | PartialUpdateExternalApi>(externalApi: T): RestOf<T> {
    return {
      ...externalApi,
      datecreatedt: externalApi.datecreatedt?.format(DATE_FORMAT) ?? null,
      lastupdatedm: externalApi.lastupdatedm?.format(DATE_FORMAT) ?? null,
      createdatet: externalApi.createdatet?.format(DATE_FORMAT) ?? null,
      lastupdatete: externalApi.lastupdatete?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restExternalApi: RestExternalApi): IExternalApi {
    return {
      ...restExternalApi,
      datecreatedt: restExternalApi.datecreatedt ? dayjs(restExternalApi.datecreatedt) : undefined,
      lastupdatedm: restExternalApi.lastupdatedm ? dayjs(restExternalApi.lastupdatedm) : undefined,
      createdatet: restExternalApi.createdatet ? dayjs(restExternalApi.createdatet) : undefined,
      lastupdatete: restExternalApi.lastupdatete ? dayjs(restExternalApi.lastupdatete) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestExternalApi>): HttpResponse<IExternalApi> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestExternalApi[]>): HttpResponse<IExternalApi[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
