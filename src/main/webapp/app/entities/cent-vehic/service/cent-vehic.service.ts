import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ICentVehic, NewCentVehic } from '../cent-vehic.model';

export type PartialUpdateCentVehic = Partial<ICentVehic> & Pick<ICentVehic, 'id'>;

type RestOf<T extends ICentVehic | NewCentVehic> = Omit<T, 'dateff'> & {
  dateff?: string | null;
};

export type RestCentVehic = RestOf<ICentVehic>;

export type NewRestCentVehic = RestOf<NewCentVehic>;

export type PartialUpdateRestCentVehic = RestOf<PartialUpdateCentVehic>;

export type EntityResponseType = HttpResponse<ICentVehic>;
export type EntityArrayResponseType = HttpResponse<ICentVehic[]>;

@Injectable({ providedIn: 'root' })
export class CentVehicService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/cent-vehics');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(centVehic: NewCentVehic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(centVehic);
    return this.http
      .post<RestCentVehic>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(centVehic: ICentVehic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(centVehic);
    return this.http
      .put<RestCentVehic>(`${this.resourceUrl}/${this.getCentVehicIdentifier(centVehic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(centVehic: PartialUpdateCentVehic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(centVehic);
    return this.http
      .patch<RestCentVehic>(`${this.resourceUrl}/${this.getCentVehicIdentifier(centVehic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestCentVehic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestCentVehic[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getCentVehicIdentifier(centVehic: Pick<ICentVehic, 'id'>): string {
    return centVehic.id;
  }

  compareCentVehic(o1: Pick<ICentVehic, 'id'> | null, o2: Pick<ICentVehic, 'id'> | null): boolean {
    return o1 && o2 ? this.getCentVehicIdentifier(o1) === this.getCentVehicIdentifier(o2) : o1 === o2;
  }

  addCentVehicToCollectionIfMissing<Type extends Pick<ICentVehic, 'id'>>(
    centVehicCollection: Type[],
    ...centVehicsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const centVehics: Type[] = centVehicsToCheck.filter(isPresent);
    if (centVehics.length > 0) {
      const centVehicCollectionIdentifiers = centVehicCollection.map(centVehicItem => this.getCentVehicIdentifier(centVehicItem)!);
      const centVehicsToAdd = centVehics.filter(centVehicItem => {
        const centVehicIdentifier = this.getCentVehicIdentifier(centVehicItem);
        if (centVehicCollectionIdentifiers.includes(centVehicIdentifier)) {
          return false;
        }
        centVehicCollectionIdentifiers.push(centVehicIdentifier);
        return true;
      });
      return [...centVehicsToAdd, ...centVehicCollection];
    }
    return centVehicCollection;
  }

  protected convertDateFromClient<T extends ICentVehic | NewCentVehic | PartialUpdateCentVehic>(centVehic: T): RestOf<T> {
    return {
      ...centVehic,
      dateff: centVehic.dateff?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restCentVehic: RestCentVehic): ICentVehic {
    return {
      ...restCentVehic,
      dateff: restCentVehic.dateff ? dayjs(restCentVehic.dateff) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestCentVehic>): HttpResponse<ICentVehic> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestCentVehic[]>): HttpResponse<ICentVehic[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
