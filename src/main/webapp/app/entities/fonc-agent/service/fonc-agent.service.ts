import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFoncAgent, NewFoncAgent } from '../fonc-agent.model';

export type PartialUpdateFoncAgent = Partial<IFoncAgent> & Pick<IFoncAgent, 'id'>;

type RestOf<T extends IFoncAgent | NewFoncAgent> = Omit<T, 'dateff'> & {
  dateff?: string | null;
};

export type RestFoncAgent = RestOf<IFoncAgent>;

export type NewRestFoncAgent = RestOf<NewFoncAgent>;

export type PartialUpdateRestFoncAgent = RestOf<PartialUpdateFoncAgent>;

export type EntityResponseType = HttpResponse<IFoncAgent>;
export type EntityArrayResponseType = HttpResponse<IFoncAgent[]>;

@Injectable({ providedIn: 'root' })
export class FoncAgentService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fonc-agents');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(foncAgent: NewFoncAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foncAgent);
    return this.http
      .post<RestFoncAgent>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(foncAgent: IFoncAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foncAgent);
    return this.http
      .put<RestFoncAgent>(`${this.resourceUrl}/${this.getFoncAgentIdentifier(foncAgent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(foncAgent: PartialUpdateFoncAgent): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(foncAgent);
    return this.http
      .patch<RestFoncAgent>(`${this.resourceUrl}/${this.getFoncAgentIdentifier(foncAgent)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http
      .get<RestFoncAgent>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFoncAgent[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFoncAgentIdentifier(foncAgent: Pick<IFoncAgent, 'id'>): string {
    return foncAgent.id;
  }

  compareFoncAgent(o1: Pick<IFoncAgent, 'id'> | null, o2: Pick<IFoncAgent, 'id'> | null): boolean {
    return o1 && o2 ? this.getFoncAgentIdentifier(o1) === this.getFoncAgentIdentifier(o2) : o1 === o2;
  }

  addFoncAgentToCollectionIfMissing<Type extends Pick<IFoncAgent, 'id'>>(
    foncAgentCollection: Type[],
    ...foncAgentsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const foncAgents: Type[] = foncAgentsToCheck.filter(isPresent);
    if (foncAgents.length > 0) {
      const foncAgentCollectionIdentifiers = foncAgentCollection.map(foncAgentItem => this.getFoncAgentIdentifier(foncAgentItem)!);
      const foncAgentsToAdd = foncAgents.filter(foncAgentItem => {
        const foncAgentIdentifier = this.getFoncAgentIdentifier(foncAgentItem);
        if (foncAgentCollectionIdentifiers.includes(foncAgentIdentifier)) {
          return false;
        }
        foncAgentCollectionIdentifiers.push(foncAgentIdentifier);
        return true;
      });
      return [...foncAgentsToAdd, ...foncAgentCollection];
    }
    return foncAgentCollection;
  }

  protected convertDateFromClient<T extends IFoncAgent | NewFoncAgent | PartialUpdateFoncAgent>(foncAgent: T): RestOf<T> {
    return {
      ...foncAgent,
      dateff: foncAgent.dateff?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFoncAgent: RestFoncAgent): IFoncAgent {
    return {
      ...restFoncAgent,
      dateff: restFoncAgent.dateff ? dayjs(restFoncAgent.dateff) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFoncAgent>): HttpResponse<IFoncAgent> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFoncAgent[]>): HttpResponse<IFoncAgent[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
