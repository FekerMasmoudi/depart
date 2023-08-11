import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRhAgent, NewRhAgent } from '../rh-agent.model';

export type PartialUpdateRhAgent = Partial<IRhAgent> & Pick<IRhAgent, 'id'>;

type RestOf<T extends IRhAgent | NewRhAgent> = Omit<T, 'dateffrh'> & {
  dateffrh?: string | null;
};

export type RestRhAgent = RestOf<IRhAgent>;

export type NewRestRhAgent = RestOf<NewRhAgent>;

export type PartialUpdateRestRhAgent = RestOf<PartialUpdateRhAgent>;

export type EntityResponseType = HttpResponse<IRhAgent>;
export type EntityArrayResponseType = HttpResponse<IRhAgent[]>;

@Injectable({ providedIn: 'root' })
export class RhAgentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/rh-agents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(rhAgent: NewRhAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rhAgent);
    return this.http
      .post<RestRhAgent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(rhAgent: IRhAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rhAgent);
    return this.http
      .put<RestRhAgent>(`${this.resourceUrl}/${this.getRhAgentIdentifier(rhAgent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(rhAgent: PartialUpdateRhAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(rhAgent);
    return this.http
      .patch<RestRhAgent>(`${this.resourceUrl}/${this.getRhAgentIdentifier(rhAgent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestRhAgent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestRhAgent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getRhAgentIdentifier(rhAgent: Pick<IRhAgent, 'id'>): string {
    return rhAgent.id;
  }

  compareRhAgent(o1: Pick<IRhAgent, 'id'> | null, o2: Pick<IRhAgent, 'id'> | null): boolean {
    return o1 && o2 ? this.getRhAgentIdentifier(o1) === this.getRhAgentIdentifier(o2) : o1 === o2;
  }

  addRhAgentToCollectionIfMissing<Type extends Pick<IRhAgent, 'id'>>(
    rhAgentCollection: Type[],
    ...rhAgentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const rhAgents: Type[] = rhAgentsToCheck.filter(isPresent);
    if (rhAgents.length > 0) {
      const rhAgentCollectionIdentifiers = rhAgentCollection.map(rhAgentItem => this.getRhAgentIdentifier(rhAgentItem)!);
      const rhAgentsToAdd = rhAgents.filter(rhAgentItem => {
        const rhAgentIdentifier = this.getRhAgentIdentifier(rhAgentItem);
        if (rhAgentCollectionIdentifiers.includes(rhAgentIdentifier)) {
          return false;
        }
        rhAgentCollectionIdentifiers.push(rhAgentIdentifier);
        return true;
      });
      return [...rhAgentsToAdd, ...rhAgentCollection];
    }
    return rhAgentCollection;
  }

  protected convertDateFromClient<T extends IRhAgent | NewRhAgent | PartialUpdateRhAgent>(rhAgent: T): RestOf<T> {
    return {
      ...rhAgent,
      dateffrh: rhAgent.dateffrh?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restRhAgent: RestRhAgent): IRhAgent {
    return {
      ...restRhAgent,
      dateffrh: restRhAgent.dateffrh ? dayjs(restRhAgent.dateffrh) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestRhAgent>): HttpResponse<IRhAgent> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestRhAgent[]>): HttpResponse<IRhAgent[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
