import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IDisplaybus, NewDisplaybus } from '../displaybus.model';

export type PartialUpdateDisplaybus = Partial<IDisplaybus> & Pick<IDisplaybus, 'id'>;

export type EntityResponseType = HttpResponse<IDisplaybus>;
export type EntityArrayResponseType = HttpResponse<IDisplaybus[]>;

@Injectable({ providedIn: 'root' })
export class DisplaybusService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/displaybuses');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(displaybus: NewDisplaybus): Observable<EntityResponseType> {
    return this.http.post<IDisplaybus>(this.resourceUrl, displaybus, { observe: 'response' });
  }

  update(displaybus: IDisplaybus): Observable<EntityResponseType> {
    return this.http.put<IDisplaybus>(`${this.resourceUrl}/${this.getDisplaybusIdentifier(displaybus)}`, displaybus, {
      observe: 'response',
    });
  }

  partialUpdate(displaybus: PartialUpdateDisplaybus): Observable<EntityResponseType> {
    return this.http.patch<IDisplaybus>(`${this.resourceUrl}/${this.getDisplaybusIdentifier(displaybus)}`, displaybus, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IDisplaybus>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IDisplaybus[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getDisplaybusIdentifier(displaybus: Pick<IDisplaybus, 'id'>): string {
    return displaybus.id;
  }

  compareDisplaybus(o1: Pick<IDisplaybus, 'id'> | null, o2: Pick<IDisplaybus, 'id'> | null): boolean {
    return o1 && o2 ? this.getDisplaybusIdentifier(o1) === this.getDisplaybusIdentifier(o2) : o1 === o2;
  }

  addDisplaybusToCollectionIfMissing<Type extends Pick<IDisplaybus, 'id'>>(
    displaybusCollection: Type[],
    ...displaybusesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const displaybuses: Type[] = displaybusesToCheck.filter(isPresent);
    if (displaybuses.length > 0) {
      const displaybusCollectionIdentifiers = displaybusCollection.map(displaybusItem => this.getDisplaybusIdentifier(displaybusItem)!);
      const displaybusesToAdd = displaybuses.filter(displaybusItem => {
        const displaybusIdentifier = this.getDisplaybusIdentifier(displaybusItem);
        if (displaybusCollectionIdentifiers.includes(displaybusIdentifier)) {
          return false;
        }
        displaybusCollectionIdentifiers.push(displaybusIdentifier);
        return true;
      });
      return [...displaybusesToAdd, ...displaybusCollection];
    }
    return displaybusCollection;
  }
}
