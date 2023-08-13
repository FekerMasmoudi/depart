import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRotRserv, NewRotRserv } from '../rot-rserv.model';

export type PartialUpdateRotRserv = Partial<IRotRserv> & Pick<IRotRserv, 'id'>;

type RestOf<T extends IRotRserv | NewRotRserv> = Omit<T, 'dedated' | 'heurdeb' | 'heurfin' | 'lieedeb' | 'lieefin'> & {
  dedated?: string | null;
  heurdeb?: string | null;
  heurfin?: string | null;
  lieedeb?: string | null;
  lieefin?: string | null;
};

export type RestRotRserv = RestOf<IRotRserv>;

export type NewRestRotRserv = RestOf<NewRotRserv>;

export type PartialUpdateRestRotRserv = RestOf<PartialUpdateRotRserv>;

export type EntityResponseType = HttpResponse<IRotRserv>;
export type EntityArrayResponseType = HttpResponse<IRotRserv[]>;

@Injectable({ providedIn: 'root' })
export class RotRservService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/rot-rservs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(rotRserv: NewRotRserv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rotRserv);
    return this.http
      .post<RestRotRserv>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(rotRserv: IRotRserv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rotRserv);
    return this.http
      .put<RestRotRserv>(`${this.resourceUrl}/${this.getRotRservIdentifier(rotRserv)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(rotRserv: PartialUpdateRotRserv): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rotRserv);
    return this.http
      .patch<RestRotRserv>(`${this.resourceUrl}/${this.getRotRservIdentifier(rotRserv)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestRotRserv>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRotRserv[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRotRservIdentifier(rotRserv: Pick<IRotRserv, 'id'>): string {
    return rotRserv.id;
  }

  compareRotRserv(o1: Pick<IRotRserv, 'id'> | null, o2: Pick<IRotRserv, 'id'> | null): boolean {
    return o1 && o2 ? this.getRotRservIdentifier(o1) === this.getRotRservIdentifier(o2) : o1 === o2;
  }

  addRotRservToCollectionIfMissing<Type extends Pick<IRotRserv, 'id'>>(
    rotRservCollection: Type[],
    ...rotRservsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const rotRservs: Type[] = rotRservsToCheck.filter(isPresent);
    if (rotRservs.length > 0) {
      const rotRservCollectionIdentifiers = rotRservCollection.map(rotRservItem => this.getRotRservIdentifier(rotRservItem)!);
      const rotRservsToAdd = rotRservs.filter(rotRservItem => {
        const rotRservIdentifier = this.getRotRservIdentifier(rotRservItem);
        if (rotRservCollectionIdentifiers.includes(rotRservIdentifier)) {
          return false;
        }
        rotRservCollectionIdentifiers.push(rotRservIdentifier);
        return true;
      });
      return [...rotRservsToAdd, ...rotRservCollection];
    }
    return rotRservCollection;
  }

  protected convertDateFromClient<T extends IRotRserv | NewRotRserv | PartialUpdateRotRserv>(rotRserv: T): RestOf<T> {
    return {
      ...rotRserv,
      dedated: rotRserv.dedated?.format(DATE_FORMAT) ?? null,
      heurdeb: rotRserv.heurdeb?.toJSON() ?? null,
      heurfin: rotRserv.heurfin?.toJSON() ?? null,
      lieedeb: rotRserv.lieedeb?.toJSON() ?? null,
      lieefin: rotRserv.lieefin?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restRotRserv: RestRotRserv): IRotRserv {
    return {
      ...restRotRserv,
      dedated: restRotRserv.dedated ? dayjs(restRotRserv.dedated) : undefined,
      heurdeb: restRotRserv.heurdeb ? dayjs(restRotRserv.heurdeb) : undefined,
      heurfin: restRotRserv.heurfin ? dayjs(restRotRserv.heurfin) : undefined,
      lieedeb: restRotRserv.lieedeb ? dayjs(restRotRserv.lieedeb) : undefined,
      lieefin: restRotRserv.lieefin ? dayjs(restRotRserv.lieefin) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRotRserv>): HttpResponse<IRotRserv> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRotRserv[]>): HttpResponse<IRotRserv[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
