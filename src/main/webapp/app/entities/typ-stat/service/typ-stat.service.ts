import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { ITypStat, NewTypStat } from '../typ-stat.model';

export type PartialUpdateTypStat = Partial<ITypStat> & Pick<ITypStat, 'id'>;

export type EntityResponseType = HttpResponse<ITypStat>;
export type EntityArrayResponseType = HttpResponse<ITypStat[]>;

@Injectable({ providedIn: 'root' })
export class TypStatService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/typ-stats');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(typStat: NewTypStat): Observable<EntityResponseType> {
    return this.http.post<ITypStat>(this.resourceUrl, typStat, { observe: 'response' });
  }

  update(typStat: ITypStat): Observable<EntityResponseType> {
    return this.http.put<ITypStat>(`${this.resourceUrl}/${this.getTypStatIdentifier(typStat)}`, typStat, { observe: 'response' });
  }

  partialUpdate(typStat: PartialUpdateTypStat): Observable<EntityResponseType> {
    return this.http.patch<ITypStat>(`${this.resourceUrl}/${this.getTypStatIdentifier(typStat)}`, typStat, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<ITypStat>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ITypStat[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getTypStatIdentifier(typStat: Pick<ITypStat, 'id'>): string {
    return typStat.id;
  }

  compareTypStat(o1: Pick<ITypStat, 'id'> | null, o2: Pick<ITypStat, 'id'> | null): boolean {
    return o1 && o2 ? this.getTypStatIdentifier(o1) === this.getTypStatIdentifier(o2) : o1 === o2;
  }

  addTypStatToCollectionIfMissing<Type extends Pick<ITypStat, 'id'>>(
    typStatCollection: Type[],
    ...typStatsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const typStats: Type[] = typStatsToCheck.filter(isPresent);
    if (typStats.length > 0) {
      const typStatCollectionIdentifiers = typStatCollection.map(typStatItem => this.getTypStatIdentifier(typStatItem)!);
      const typStatsToAdd = typStats.filter(typStatItem => {
        const typStatIdentifier = this.getTypStatIdentifier(typStatItem);
        if (typStatCollectionIdentifiers.includes(typStatIdentifier)) {
          return false;
        }
        typStatCollectionIdentifiers.push(typStatIdentifier);
        return true;
      });
      return [...typStatsToAdd, ...typStatCollection];
    }
    return typStatCollection;
  }
}
