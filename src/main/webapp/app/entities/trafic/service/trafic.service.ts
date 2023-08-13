import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITrafic, NewTrafic } from '../trafic.model';

export type PartialUpdateTrafic = Partial<ITrafic> & Pick<ITrafic, 'id'>;

type RestOf<T extends ITrafic | NewTrafic> = Omit<T, 'dedated'> & {
  dedated?: string | null;
};

export type RestTrafic = RestOf<ITrafic>;

export type NewRestTrafic = RestOf<NewTrafic>;

export type PartialUpdateRestTrafic = RestOf<PartialUpdateTrafic>;

export type EntityResponseType = HttpResponse<ITrafic>;
export type EntityArrayResponseType = HttpResponse<ITrafic[]>;

@Injectable({ providedIn: 'root' })
export class TraficService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/trafics');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(trafic: NewTrafic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trafic);
    return this.http
      .post<RestTrafic>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(trafic: ITrafic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trafic);
    return this.http
      .put<RestTrafic>(`${this.resourceUrl}/${this.getTraficIdentifier(trafic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(trafic: PartialUpdateTrafic): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(trafic);
    return this.http
      .patch<RestTrafic>(`${this.resourceUrl}/${this.getTraficIdentifier(trafic)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestTrafic>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestTrafic[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTraficIdentifier(trafic: Pick<ITrafic, 'id'>): string {
    return trafic.id;
  }

  compareTrafic(o1: Pick<ITrafic, 'id'> | null, o2: Pick<ITrafic, 'id'> | null): boolean {
    return o1 && o2 ? this.getTraficIdentifier(o1) === this.getTraficIdentifier(o2) : o1 === o2;
  }

  addTraficToCollectionIfMissing<Type extends Pick<ITrafic, 'id'>>(
    traficCollection: Type[],
    ...traficsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const trafics: Type[] = traficsToCheck.filter(isPresent);
    if (trafics.length > 0) {
      const traficCollectionIdentifiers = traficCollection.map(traficItem => this.getTraficIdentifier(traficItem)!);
      const traficsToAdd = trafics.filter(traficItem => {
        const traficIdentifier = this.getTraficIdentifier(traficItem);
        if (traficCollectionIdentifiers.includes(traficIdentifier)) {
          return false;
        }
        traficCollectionIdentifiers.push(traficIdentifier);
        return true;
      });
      return [...traficsToAdd, ...traficCollection];
    }
    return traficCollection;
  }

  protected convertDateFromClient<T extends ITrafic | NewTrafic | PartialUpdateTrafic>(trafic: T): RestOf<T> {
    return {
      ...trafic,
      dedated: trafic.dedated?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restTrafic: RestTrafic): ITrafic {
    return {
      ...restTrafic,
      dedated: restTrafic.dedated ? dayjs(restTrafic.dedated) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestTrafic>): HttpResponse<ITrafic> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestTrafic[]>): HttpResponse<ITrafic[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
