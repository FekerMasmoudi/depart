import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAffectagent, NewAffectagent } from '../affectagent.model';

export type PartialUpdateAffectagent = Partial<IAffectagent> & Pick<IAffectagent, 'id'>;

export type EntityResponseType = HttpResponse<IAffectagent>;
export type EntityArrayResponseType = HttpResponse<IAffectagent[]>;

@Injectable({ providedIn: 'root' })
export class AffectagentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/affectagents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(affectagent: NewAffectagent): Observable<EntityResponseType> {
    return this.http.post<IAffectagent>(this.resourceUrl, affectagent, { observe: 'response' });
  }

  update(affectagent: IAffectagent): Observable<EntityResponseType> {
    return this.http.put<IAffectagent>(`${this.resourceUrl}/${this.getAffectagentIdentifier(affectagent)}`, affectagent, {
      observe: 'response',
    });
  }

  partialUpdate(affectagent: PartialUpdateAffectagent): Observable<EntityResponseType> {
    return this.http.patch<IAffectagent>(`${this.resourceUrl}/${this.getAffectagentIdentifier(affectagent)}`, affectagent, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAffectagent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAffectagent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAffectagentIdentifier(affectagent: Pick<IAffectagent, 'id'>): string {
    return affectagent.id;
  }

  compareAffectagent(o1: Pick<IAffectagent, 'id'> | null, o2: Pick<IAffectagent, 'id'> | null): boolean {
    return o1 && o2 ? this.getAffectagentIdentifier(o1) === this.getAffectagentIdentifier(o2) : o1 === o2;
  }

  addAffectagentToCollectionIfMissing<Type extends Pick<IAffectagent, 'id'>>(
    affectagentCollection: Type[],
    ...affectagentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const affectagents: Type[] = affectagentsToCheck.filter(isPresent);
    if (affectagents.length > 0) {
      const affectagentCollectionIdentifiers = affectagentCollection.map(
        affectagentItem => this.getAffectagentIdentifier(affectagentItem)!
      );
      const affectagentsToAdd = affectagents.filter(affectagentItem => {
        const affectagentIdentifier = this.getAffectagentIdentifier(affectagentItem);
        if (affectagentCollectionIdentifiers.includes(affectagentIdentifier)) {
          return false;
        }
        affectagentCollectionIdentifiers.push(affectagentIdentifier);
        return true;
      });
      return [...affectagentsToAdd, ...affectagentCollection];
    }
    return affectagentCollection;
  }
}
