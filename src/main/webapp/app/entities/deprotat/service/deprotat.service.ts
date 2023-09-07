import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDeprotat, NewDeprotat } from '../deprotat.model';

export type PartialUpdateDeprotat = Partial<IDeprotat> & Pick<IDeprotat, 'id'>;

type RestOf<T extends IDeprotat | NewDeprotat> = Omit<T, 'dedated' | 'hdeparte' | 'hretoure' | 'harralle' | 'harrrete'> & {
  dedated?: string | null;
  hdeparte?: string | null;
  hretoure?: string | null;
  harralle?: string | null;
  harrrete?: string | null;
};

export type RestDeprotat = RestOf<IDeprotat>;

export type NewRestDeprotat = RestOf<NewDeprotat>;

export type PartialUpdateRestDeprotat = RestOf<PartialUpdateDeprotat>;

export type EntityResponseType = HttpResponse<IDeprotat>;
export type EntityArrayResponseType = HttpResponse<IDeprotat[]>;

@Injectable({ providedIn: 'root' })
export class DeprotatService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/deprotats');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(deprotat: NewDeprotat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deprotat);
    return this.http
      .post<RestDeprotat>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(deprotat: IDeprotat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deprotat);
    return this.http
      .put<RestDeprotat>(`${this.resourceUrl}/${this.getDeprotatIdentifier(deprotat)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(deprotat: PartialUpdateDeprotat): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(deprotat);
    return this.http
      .patch<RestDeprotat>(`${this.resourceUrl}/${this.getDeprotatIdentifier(deprotat)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDeprotat>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDeprotat[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDeprotatIdentifier(deprotat: Pick<IDeprotat, 'id'>): string {
    return deprotat.id;
  }

  compareDeprotat(o1: Pick<IDeprotat, 'id'> | null, o2: Pick<IDeprotat, 'id'> | null): boolean {
    return o1 && o2 ? this.getDeprotatIdentifier(o1) === this.getDeprotatIdentifier(o2) : o1 === o2;
  }

  addDeprotatToCollectionIfMissing<Type extends Pick<IDeprotat, 'id'>>(
    deprotatCollection: Type[],
    ...deprotatsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const deprotats: Type[] = deprotatsToCheck.filter(isPresent);
    if (deprotats.length > 0) {
      const deprotatCollectionIdentifiers = deprotatCollection.map(deprotatItem => this.getDeprotatIdentifier(deprotatItem)!);
      const deprotatsToAdd = deprotats.filter(deprotatItem => {
        const deprotatIdentifier = this.getDeprotatIdentifier(deprotatItem);
        if (deprotatCollectionIdentifiers.includes(deprotatIdentifier)) {
          return false;
        }
        deprotatCollectionIdentifiers.push(deprotatIdentifier);
        return true;
      });
      return [...deprotatsToAdd, ...deprotatCollection];
    }
    return deprotatCollection;
  }

  protected convertDateFromClient<T extends IDeprotat | NewDeprotat | PartialUpdateDeprotat>(deprotat: T): RestOf<T> {
    return {
      ...deprotat,
      dedated: deprotat.dedated?.format(DATE_FORMAT) ?? null,
      hdeparte: deprotat.hdeparte?.format(DATE_FORMAT) ?? null,
      hretoure: deprotat.hretoure?.format(DATE_FORMAT) ?? null,
      harralle: deprotat.harralle?.format(DATE_FORMAT) ?? null,
      harrrete: deprotat.harrrete?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restDeprotat: RestDeprotat): IDeprotat {
    return {
      ...restDeprotat,
      dedated: restDeprotat.dedated ? dayjs(restDeprotat.dedated) : undefined,
      hdeparte: restDeprotat.hdeparte ? dayjs(restDeprotat.hdeparte) : undefined,
      hretoure: restDeprotat.hretoure ? dayjs(restDeprotat.hretoure) : undefined,
      harralle: restDeprotat.harralle ? dayjs(restDeprotat.harralle) : undefined,
      harrrete: restDeprotat.harrrete ? dayjs(restDeprotat.harrrete) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDeprotat>): HttpResponse<IDeprotat> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDeprotat[]>): HttpResponse<IDeprotat[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
