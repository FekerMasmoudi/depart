import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ILigne, NewLigne } from '../ligne.model';

export type PartialUpdateLigne = Partial<ILigne> & Pick<ILigne, 'id'>;

type RestOf<T extends ILigne | NewLigne> = Omit<T, 'lastupdate'> & {
  lastupdate?: string | null;
};

export type RestLigne = RestOf<ILigne>;

export type NewRestLigne = RestOf<NewLigne>;

export type PartialUpdateRestLigne = RestOf<PartialUpdateLigne>;

export type EntityResponseType = HttpResponse<ILigne>;
export type EntityArrayResponseType = HttpResponse<ILigne[]>;

@Injectable({ providedIn: 'root' })
export class LigneService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/lignes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(ligne: NewLigne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ligne);
    return this.http.post<RestLigne>(this.resourceUrl, copy, { observe: 'response' }).pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ligne: ILigne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ligne);
    return this.http
      .put<RestLigne>(`${this.resourceUrl}/${this.getLigneIdentifier(ligne)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ligne: PartialUpdateLigne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ligne);
    return this.http
      .patch<RestLigne>(`${this.resourceUrl}/${this.getLigneIdentifier(ligne)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestLigne>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestLigne[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getLigneIdentifier(ligne: Pick<ILigne, 'id'>): string {
    return ligne.id;
  }

  compareLigne(o1: Pick<ILigne, 'id'> | null, o2: Pick<ILigne, 'id'> | null): boolean {
    return o1 && o2 ? this.getLigneIdentifier(o1) === this.getLigneIdentifier(o2) : o1 === o2;
  }

  addLigneToCollectionIfMissing<Type extends Pick<ILigne, 'id'>>(
    ligneCollection: Type[],
    ...lignesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const lignes: Type[] = lignesToCheck.filter(isPresent);
    if (lignes.length > 0) {
      const ligneCollectionIdentifiers = ligneCollection.map(ligneItem => this.getLigneIdentifier(ligneItem)!);
      const lignesToAdd = lignes.filter(ligneItem => {
        const ligneIdentifier = this.getLigneIdentifier(ligneItem);
        if (ligneCollectionIdentifiers.includes(ligneIdentifier)) {
          return false;
        }
        ligneCollectionIdentifiers.push(ligneIdentifier);
        return true;
      });
      return [...lignesToAdd, ...ligneCollection];
    }
    return ligneCollection;
  }

  protected convertDateFromClient<T extends ILigne | NewLigne | PartialUpdateLigne>(ligne: T): RestOf<T> {
    return {
      ...ligne,
      lastupdate: ligne.lastupdate?.toJSON() ?? null,
    };
  }

  protected convertDateFromServer(restLigne: RestLigne): ILigne {
    return {
      ...restLigne,
      lastupdate: restLigne.lastupdate ? dayjs(restLigne.lastupdate) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestLigne>): HttpResponse<ILigne> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestLigne[]>): HttpResponse<ILigne[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
