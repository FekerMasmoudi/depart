import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IAffecAgent, NewAffecAgent } from '../affec-agent.model';

export type PartialUpdateAffecAgent = Partial<IAffecAgent> & Pick<IAffecAgent, 'id'>;

export type EntityResponseType = HttpResponse<IAffecAgent>;
export type EntityArrayResponseType = HttpResponse<IAffecAgent[]>;

@Injectable({ providedIn: 'root' })
export class AffecAgentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/affec-agents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(affecAgent: NewAffecAgent): Observable<EntityResponseType> {
    return this.http.post<IAffecAgent>(this.resourceUrl, affecAgent, { observe: 'response' });
  }

  update(affecAgent: IAffecAgent): Observable<EntityResponseType> {
    return this.http.put<IAffecAgent>(`${this.resourceUrl}/${this.getAffecAgentIdentifier(affecAgent)}`, affecAgent, {
      observe: 'response',
    });
  }

  partialUpdate(affecAgent: PartialUpdateAffecAgent): Observable<EntityResponseType> {
    return this.http.patch<IAffecAgent>(`${this.resourceUrl}/${this.getAffecAgentIdentifier(affecAgent)}`, affecAgent, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IAffecAgent>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IAffecAgent[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getAffecAgentIdentifier(affecAgent: Pick<IAffecAgent, 'id'>): string {
    return affecAgent.id;
  }

  compareAffecAgent(o1: Pick<IAffecAgent, 'id'> | null, o2: Pick<IAffecAgent, 'id'> | null): boolean {
    return o1 && o2 ? this.getAffecAgentIdentifier(o1) === this.getAffecAgentIdentifier(o2) : o1 === o2;
  }

  addAffecAgentToCollectionIfMissing<Type extends Pick<IAffecAgent, 'id'>>(
    affecAgentCollection: Type[],
    ...affecAgentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const affecAgents: Type[] = affecAgentsToCheck.filter(isPresent);
    if (affecAgents.length > 0) {
      const affecAgentCollectionIdentifiers = affecAgentCollection.map(affecAgentItem => this.getAffecAgentIdentifier(affecAgentItem)!);
      const affecAgentsToAdd = affecAgents.filter(affecAgentItem => {
        const affecAgentIdentifier = this.getAffecAgentIdentifier(affecAgentItem);
        if (affecAgentCollectionIdentifiers.includes(affecAgentIdentifier)) {
          return false;
        }
        affecAgentCollectionIdentifiers.push(affecAgentIdentifier);
        return true;
      });
      return [...affecAgentsToAdd, ...affecAgentCollection];
    }
    return affecAgentCollection;
  }
}
