import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IModif, NewModif } from '../modif.model';

export type PartialUpdateModif = Partial<IModif> & Pick<IModif, 'id'>;

type RestOf<T extends IModif | NewModif> = Omit<T, 'dedated' | 'heur'> & {
  dedated?: string | null;
  heur?: string | null;
};

export type RestModif = RestOf<IModif>;

export type NewRestModif = RestOf<NewModif>;

export type PartialUpdateRestModif = RestOf<PartialUpdateModif>;

export type EntityResponseType = HttpResponse<IModif>;
export type EntityArrayResponseType = HttpResponse<IModif[]>;

@Injectable({ providedIn: 'root' })
export class ModifService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/modifs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(modif: NewModif): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modif);
    return this.http.post<RestModif>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(modif: IModif): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modif);
    return this.http
      .put<RestModif>(`${this.resourceUrl}/${this.getModifIdentifier(modif)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(modif: PartialUpdateModif): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(modif);
    return this.http
      .patch<RestModif>(`${this.resourceUrl}/${this.getModifIdentifier(modif)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestModif>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestModif[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getModifIdentifier(modif: Pick<IModif, 'id'>): string {
    return modif.id;
  }

  compareModif(o1: Pick<IModif, 'id'> | null, o2: Pick<IModif, 'id'> | null): boolean {
    return o1 && o2 ? this.getModifIdentifier(o1) === this.getModifIdentifier(o2) : o1 === o2;
  }

  addModifToCollectionIfMissing<Type extends Pick<IModif, 'id'>>(
    modifCollection: Type[],
    ...modifsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const modifs: Type[] = modifsToCheck.filter(isPresent);
    if (modifs.length > 0) {
      const modifCollectionIdentifiers = modifCollection.map(modifItem => this.getModifIdentifier(modifItem)!);
      const modifsToAdd = modifs.filter(modifItem => {
        const modifIdentifier = this.getModifIdentifier(modifItem);
        if (modifCollectionIdentifiers.includes(modifIdentifier)) {
          return false;
        }
        modifCollectionIdentifiers.push(modifIdentifier);
        return true;
      });
      return [...modifsToAdd, ...modifCollection];
    }
    return modifCollection;
  }

  protected convertDateFromClient<T extends IModif | NewModif | PartialUpdateModif>(modif: T): RestOf<T> {
    return {
      ...modif,
      dedated: modif.dedated?.format(DATE_FORMAT) ?? null,
      heur: modif.heur?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restModif: RestModif): IModif {
    return {
      ...restModif,
      dedated: restModif.dedated ? dayjs(restModif.dedated) : undefined,
      heur: restModif.heur ? dayjs(restModif.heur) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestModif>): HttpResponse<IModif> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestModif[]>): HttpResponse<IModif[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
