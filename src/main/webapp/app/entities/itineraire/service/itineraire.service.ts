import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IItineraire, NewItineraire } from '../itineraire.model';

export type PartialUpdateItineraire = Partial<IItineraire> & Pick<IItineraire, 'id'>;

export type EntityResponseType = HttpResponse<IItineraire>;
export type EntityArrayResponseType = HttpResponse<IItineraire[]>;

@Injectable({ providedIn: 'root' })
export class ItineraireService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/itineraires');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(itineraire: NewItineraire): Observable<EntityResponseType> {
    return this.http.post<IItineraire>(this.resourceUrl, itineraire, { observe: 'response' });
  }

  update(itineraire: IItineraire): Observable<EntityResponseType> {
    return this.http.put<IItineraire>(`${this.resourceUrl}/${this.getItineraireIdentifier(itineraire)}`, itineraire, {
      observe: 'response',
    });
  }

  partialUpdate(itineraire: PartialUpdateItineraire): Observable<EntityResponseType> {
    return this.http.patch<IItineraire>(`${this.resourceUrl}/${this.getItineraireIdentifier(itineraire)}`, itineraire, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IItineraire>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IItineraire[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getItineraireIdentifier(itineraire: Pick<IItineraire, 'id'>): string {
    return itineraire.id;
  }

  compareItineraire(o1: Pick<IItineraire, 'id'> | null, o2: Pick<IItineraire, 'id'> | null): boolean {
    return o1 && o2 ? this.getItineraireIdentifier(o1) === this.getItineraireIdentifier(o2) : o1 === o2;
  }

  addItineraireToCollectionIfMissing<Type extends Pick<IItineraire, 'id'>>(
    itineraireCollection: Type[],
    ...itinerairesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const itineraires: Type[] = itinerairesToCheck.filter(isPresent);
    if (itineraires.length > 0) {
      const itineraireCollectionIdentifiers = itineraireCollection.map(itineraireItem => this.getItineraireIdentifier(itineraireItem)!);
      const itinerairesToAdd = itineraires.filter(itineraireItem => {
        const itineraireIdentifier = this.getItineraireIdentifier(itineraireItem);
        if (itineraireCollectionIdentifiers.includes(itineraireIdentifier)) {
          return false;
        }
        itineraireCollectionIdentifiers.push(itineraireIdentifier);
        return true;
      });
      return [...itinerairesToAdd, ...itineraireCollection];
    }
    return itineraireCollection;
  }
}
