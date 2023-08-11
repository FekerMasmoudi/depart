import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IBordereau, NewBordereau } from '../bordereau.model';

export type PartialUpdateBordereau = Partial<IBordereau> & Pick<IBordereau, 'id'>;

type RestOf<T extends IBordereau | NewBordereau> = Omit<T, 'dedated' | 'detadedb' | 'deheupsr' | 'date_saisie'> & {
  dedated?: string | null;
  detadedb?: string | null;
  deheupsr?: string | null;
  date_saisie?: string | null;
};

export type RestBordereau = RestOf<IBordereau>;

export type NewRestBordereau = RestOf<NewBordereau>;

export type PartialUpdateRestBordereau = RestOf<PartialUpdateBordereau>;

export type EntityResponseType = HttpResponse<IBordereau>;
export type EntityArrayResponseType = HttpResponse<IBordereau[]>;

@Injectable({ providedIn: 'root' })
export class BordereauService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/bordereaus');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(bordereau: NewBordereau): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bordereau);
    return this.http
      .post<RestBordereau>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(bordereau: IBordereau): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bordereau);
    return this.http
      .put<RestBordereau>(`${this.resourceUrl}/${this.getBordereauIdentifier(bordereau)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(bordereau: PartialUpdateBordereau): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(bordereau);
    return this.http
      .patch<RestBordereau>(`${this.resourceUrl}/${this.getBordereauIdentifier(bordereau)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestBordereau>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestBordereau[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getBordereauIdentifier(bordereau: Pick<IBordereau, 'id'>): string {
    return bordereau.id;
  }

  compareBordereau(o1: Pick<IBordereau, 'id'> | null, o2: Pick<IBordereau, 'id'> | null): boolean {
    return o1 && o2 ? this.getBordereauIdentifier(o1) === this.getBordereauIdentifier(o2) : o1 === o2;
  }

  addBordereauToCollectionIfMissing<Type extends Pick<IBordereau, 'id'>>(
    bordereauCollection: Type[],
    ...bordereausToCheck: (Type | null | undefined)[]
  ): Type[] {
    const bordereaus: Type[] = bordereausToCheck.filter(isPresent);
    if (bordereaus.length > 0) {
      const bordereauCollectionIdentifiers = bordereauCollection.map(bordereauItem => this.getBordereauIdentifier(bordereauItem)!);
      const bordereausToAdd = bordereaus.filter(bordereauItem => {
        const bordereauIdentifier = this.getBordereauIdentifier(bordereauItem);
        if (bordereauCollectionIdentifiers.includes(bordereauIdentifier)) {
          return false;
        }
        bordereauCollectionIdentifiers.push(bordereauIdentifier);
        return true;
      });
      return [...bordereausToAdd, ...bordereauCollection];
    }
    return bordereauCollection;
  }

  protected convertDateFromClient<T extends IBordereau | NewBordereau | PartialUpdateBordereau>(bordereau: T): RestOf<T> {
    return {
      ...bordereau,
      dedated: bordereau.dedated?.format(DATE_FORMAT) ?? null,
      detadedb: bordereau.detadedb?.format(DATE_FORMAT) ?? null,
      deheupsr: bordereau.deheupsr?.format(DATE_FORMAT) ?? null,
      date_saisie: bordereau.date_saisie?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restBordereau: RestBordereau): IBordereau {
    return {
      ...restBordereau,
      dedated: restBordereau.dedated ? dayjs(restBordereau.dedated) : undefined,
      detadedb: restBordereau.detadedb ? dayjs(restBordereau.detadedb) : undefined,
      deheupsr: restBordereau.deheupsr ? dayjs(restBordereau.deheupsr) : undefined,
      date_saisie: restBordereau.date_saisie ? dayjs(restBordereau.date_saisie) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestBordereau>): HttpResponse<IBordereau> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestBordereau[]>): HttpResponse<IBordereau[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
