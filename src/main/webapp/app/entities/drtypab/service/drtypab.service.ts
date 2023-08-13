import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDrtypab, NewDrtypab } from '../drtypab.model';

export type PartialUpdateDrtypab = Partial<IDrtypab> & Pick<IDrtypab, 'id'>;

export type EntityResponseType = HttpResponse<IDrtypab>;
export type EntityArrayResponseType = HttpResponse<IDrtypab[]>;

@Injectable({ providedIn: 'root' })
export class DrtypabService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/drtypabs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(drtypab: NewDrtypab): Observable<EntityResponseType> {
    return this.http.post<IDrtypab>(this.resourceUrl, drtypab, { observe: 'response' });
  }

  update(drtypab: IDrtypab): Observable<EntityResponseType> {
    return this.http.put<IDrtypab>(`${this.resourceUrl}/${this.getDrtypabIdentifier(drtypab)}`, drtypab, { observe: 'response' });
  }

  partialUpdate(drtypab: PartialUpdateDrtypab): Observable<EntityResponseType> {
    return this.http.patch<IDrtypab>(`${this.resourceUrl}/${this.getDrtypabIdentifier(drtypab)}`, drtypab, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDrtypab>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDrtypab[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDrtypabIdentifier(drtypab: Pick<IDrtypab, 'id'>): string {
    return drtypab.id;
  }

  compareDrtypab(o1: Pick<IDrtypab, 'id'> | null, o2: Pick<IDrtypab, 'id'> | null): boolean {
    return o1 && o2 ? this.getDrtypabIdentifier(o1) === this.getDrtypabIdentifier(o2) : o1 === o2;
  }

  addDrtypabToCollectionIfMissing<Type extends Pick<IDrtypab, 'id'>>(
    drtypabCollection: Type[],
    ...drtypabsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const drtypabs: Type[] = drtypabsToCheck.filter(isPresent);
    if (drtypabs.length > 0) {
      const drtypabCollectionIdentifiers = drtypabCollection.map(drtypabItem => this.getDrtypabIdentifier(drtypabItem)!);
      const drtypabsToAdd = drtypabs.filter(drtypabItem => {
        const drtypabIdentifier = this.getDrtypabIdentifier(drtypabItem);
        if (drtypabCollectionIdentifiers.includes(drtypabIdentifier)) {
          return false;
        }
        drtypabCollectionIdentifiers.push(drtypabIdentifier);
        return true;
      });
      return [...drtypabsToAdd, ...drtypabCollection];
    }
    return drtypabCollection;
  }
}
