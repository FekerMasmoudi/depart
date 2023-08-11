import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDepart, NewDepart } from '../depart.model';

export type PartialUpdateDepart = Partial<IDepart> & Pick<IDepart, 'id'>;

type RestOf<T extends IDepart | NewDepart> = Omit<T, 'dedated' | 'deheups' | 'deheufs' | 'deheuaa' | 'deheudr' | 'deheupd' | 'deampli'> & {
  dedated?: string | null;
  deheups?: string | null;
  deheufs?: string | null;
  deheuaa?: string | null;
  deheudr?: string | null;
  deheupd?: string | null;
  deampli?: string | null;
};

export type RestDepart = RestOf<IDepart>;

export type NewRestDepart = RestOf<NewDepart>;

export type PartialUpdateRestDepart = RestOf<PartialUpdateDepart>;

export type EntityResponseType = HttpResponse<IDepart>;
export type EntityArrayResponseType = HttpResponse<IDepart[]>;

@Injectable({ providedIn: 'root' })
export class DepartService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/departs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(depart: NewDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(depart);
    return this.http
      .post<RestDepart>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(depart: IDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(depart);
    return this.http
      .put<RestDepart>(`${this.resourceUrl}/${this.getDepartIdentifier(depart)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(depart: PartialUpdateDepart): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(depart);
    return this.http
      .patch<RestDepart>(`${this.resourceUrl}/${this.getDepartIdentifier(depart)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestDepart>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestDepart[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDepartIdentifier(depart: Pick<IDepart, 'id'>): string {
    return depart.id;
  }

  compareDepart(o1: Pick<IDepart, 'id'> | null, o2: Pick<IDepart, 'id'> | null): boolean {
    return o1 && o2 ? this.getDepartIdentifier(o1) === this.getDepartIdentifier(o2) : o1 === o2;
  }

  addDepartToCollectionIfMissing<Type extends Pick<IDepart, 'id'>>(
    departCollection: Type[],
    ...departsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const departs: Type[] = departsToCheck.filter(isPresent);
    if (departs.length > 0) {
      const departCollectionIdentifiers = departCollection.map(departItem => this.getDepartIdentifier(departItem)!);
      const departsToAdd = departs.filter(departItem => {
        const departIdentifier = this.getDepartIdentifier(departItem);
        if (departCollectionIdentifiers.includes(departIdentifier)) {
          return false;
        }
        departCollectionIdentifiers.push(departIdentifier);
        return true;
      });
      return [...departsToAdd, ...departCollection];
    }
    return departCollection;
  }

  protected convertDateFromClient<T extends IDepart | NewDepart | PartialUpdateDepart>(depart: T): RestOf<T> {
    return {
      ...depart,
      dedated: depart.dedated?.format(DATE_FORMAT) ?? null,
      deheups: depart.deheups?.toJSON() ?? null,
      deheufs: depart.deheufs?.toJSON() ?? null,
      deheuaa: depart.deheuaa?.toJSON() ?? null,
      deheudr: depart.deheudr?.toJSON() ?? null,
      deheupd: depart.deheupd?.toJSON() ?? null,
      deampli: depart.deampli?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restDepart: RestDepart): IDepart {
    return {
      ...restDepart,
      dedated: restDepart.dedated ? dayjs(restDepart.dedated) : undefined,
      deheups: restDepart.deheups ? dayjs(restDepart.deheups) : undefined,
      deheufs: restDepart.deheufs ? dayjs(restDepart.deheufs) : undefined,
      deheuaa: restDepart.deheuaa ? dayjs(restDepart.deheuaa) : undefined,
      deheudr: restDepart.deheudr ? dayjs(restDepart.deheudr) : undefined,
      deheupd: restDepart.deheupd ? dayjs(restDepart.deheupd) : undefined,
      deampli: restDepart.deampli ? dayjs(restDepart.deampli) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestDepart>): HttpResponse<IDepart> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestDepart[]>): HttpResponse<IDepart[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
