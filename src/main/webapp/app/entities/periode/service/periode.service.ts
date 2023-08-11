import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IPeriode, NewPeriode } from '../periode.model';

export type PartialUpdatePeriode = Partial<IPeriode> & Pick<IPeriode, 'id'>;

export type EntityResponseType = HttpResponse<IPeriode>;
export type EntityArrayResponseType = HttpResponse<IPeriode[]>;

@Injectable({ providedIn: 'root' })
export class PeriodeService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/periodes');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(periode: NewPeriode): Observable<EntityResponseType> {
    return this.http.post<IPeriode>(this.resourceUrl, periode, { observe: 'response' });
  }

  update(periode: IPeriode): Observable<EntityResponseType> {
    return this.http.put<IPeriode>(`${this.resourceUrl}/${this.getPeriodeIdentifier(periode)}`, periode, { observe: 'response' });
  }

  partialUpdate(periode: PartialUpdatePeriode): Observable<EntityResponseType> {
    return this.http.patch<IPeriode>(`${this.resourceUrl}/${this.getPeriodeIdentifier(periode)}`, periode, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IPeriode>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPeriode[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getPeriodeIdentifier(periode: Pick<IPeriode, 'id'>): string {
    return periode.id;
  }

  comparePeriode(o1: Pick<IPeriode, 'id'> | null, o2: Pick<IPeriode, 'id'> | null): boolean {
    return o1 && o2 ? this.getPeriodeIdentifier(o1) === this.getPeriodeIdentifier(o2) : o1 === o2;
  }

  addPeriodeToCollectionIfMissing<Type extends Pick<IPeriode, 'id'>>(
    periodeCollection: Type[],
    ...periodesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const periodes: Type[] = periodesToCheck.filter(isPresent);
    if (periodes.length > 0) {
      const periodeCollectionIdentifiers = periodeCollection.map(periodeItem => this.getPeriodeIdentifier(periodeItem)!);
      const periodesToAdd = periodes.filter(periodeItem => {
        const periodeIdentifier = this.getPeriodeIdentifier(periodeItem);
        if (periodeCollectionIdentifiers.includes(periodeIdentifier)) {
          return false;
        }
        periodeCollectionIdentifiers.push(periodeIdentifier);
        return true;
      });
      return [...periodesToAdd, ...periodeCollection];
    }
    return periodeCollection;
  }
}
