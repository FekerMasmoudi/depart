import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IMotifa, NewMotifa } from '../motifa.model';

export type PartialUpdateMotifa = Partial<IMotifa> & Pick<IMotifa, 'id'>;

export type EntityResponseType = HttpResponse<IMotifa>;
export type EntityArrayResponseType = HttpResponse<IMotifa[]>;

@Injectable({ providedIn: 'root' })
export class MotifaService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/motifas');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(motifa: NewMotifa): Observable<EntityResponseType> {
    return this.http.post<IMotifa>(this.resourceUrl, motifa, { observe: 'response' });
  }

  update(motifa: IMotifa): Observable<EntityResponseType> {
    return this.http.put<IMotifa>(`${this.resourceUrl}/${this.getMotifaIdentifier(motifa)}`, motifa, { observe: 'response' });
  }

  partialUpdate(motifa: PartialUpdateMotifa): Observable<EntityResponseType> {
    return this.http.patch<IMotifa>(`${this.resourceUrl}/${this.getMotifaIdentifier(motifa)}`, motifa, { observe: 'response' });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IMotifa>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IMotifa[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getMotifaIdentifier(motifa: Pick<IMotifa, 'id'>): string {
    return motifa.id;
  }

  compareMotifa(o1: Pick<IMotifa, 'id'> | null, o2: Pick<IMotifa, 'id'> | null): boolean {
    return o1 && o2 ? this.getMotifaIdentifier(o1) === this.getMotifaIdentifier(o2) : o1 === o2;
  }

  addMotifaToCollectionIfMissing<Type extends Pick<IMotifa, 'id'>>(
    motifaCollection: Type[],
    ...motifasToCheck: (Type | null | undefined)[]
  ): Type[] {
    const motifas: Type[] = motifasToCheck.filter(isPresent);
    if (motifas.length > 0) {
      const motifaCollectionIdentifiers = motifaCollection.map(motifaItem => this.getMotifaIdentifier(motifaItem)!);
      const motifasToAdd = motifas.filter(motifaItem => {
        const motifaIdentifier = this.getMotifaIdentifier(motifaItem);
        if (motifaCollectionIdentifiers.includes(motifaIdentifier)) {
          return false;
        }
        motifaCollectionIdentifiers.push(motifaIdentifier);
        return true;
      });
      return [...motifasToAdd, ...motifaCollection];
    }
    return motifaCollection;
  }
}
