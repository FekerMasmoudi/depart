import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMotifchserv, NewMotifchserv } from '../motifchserv.model';

export type PartialUpdateMotifchserv = Partial<IMotifchserv> & Pick<IMotifchserv, 'id'>;

export type EntityResponseType = HttpResponse<IMotifchserv>;
export type EntityArrayResponseType = HttpResponse<IMotifchserv[]>;

@Injectable({ providedIn: 'root' })
export class MotifchservService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/motifchservs');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(motifchserv: NewMotifchserv): Observable<EntityResponseType> {
    return this.http.post<IMotifchserv>(this.resourceUrl, motifchserv, { observe: 'response' });
  }

  update(motifchserv: IMotifchserv): Observable<EntityResponseType> {
    return this.http.put<IMotifchserv>(`${this.resourceUrl}/${this.getMotifchservIdentifier(motifchserv)}`, motifchserv, {
      observe: 'response',
    });
  }

  partialUpdate(motifchserv: PartialUpdateMotifchserv): Observable<EntityResponseType> {
    return this.http.patch<IMotifchserv>(`${this.resourceUrl}/${this.getMotifchservIdentifier(motifchserv)}`, motifchserv, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IMotifchserv>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMotifchserv[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMotifchservIdentifier(motifchserv: Pick<IMotifchserv, 'id'>): string {
    return motifchserv.id;
  }

  compareMotifchserv(o1: Pick<IMotifchserv, 'id'> | null, o2: Pick<IMotifchserv, 'id'> | null): boolean {
    return o1 && o2 ? this.getMotifchservIdentifier(o1) === this.getMotifchservIdentifier(o2) : o1 === o2;
  }

  addMotifchservToCollectionIfMissing<Type extends Pick<IMotifchserv, 'id'>>(
    motifchservCollection: Type[],
    ...motifchservsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const motifchservs: Type[] = motifchservsToCheck.filter(isPresent);
    if (motifchservs.length > 0) {
      const motifchservCollectionIdentifiers = motifchservCollection.map(
        motifchservItem => this.getMotifchservIdentifier(motifchservItem)!
      );
      const motifchservsToAdd = motifchservs.filter(motifchservItem => {
        const motifchservIdentifier = this.getMotifchservIdentifier(motifchservItem);
        if (motifchservCollectionIdentifiers.includes(motifchservIdentifier)) {
          return false;
        }
        motifchservCollectionIdentifiers.push(motifchservIdentifier);
        return true;
      });
      return [...motifchservsToAdd, ...motifchservCollection];
    }
    return motifchservCollection;
  }
}
