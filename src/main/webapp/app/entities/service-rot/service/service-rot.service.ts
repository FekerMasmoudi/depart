import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IServiceRot, NewServiceRot } from '../service-rot.model';

export type PartialUpdateServiceRot = Partial<IServiceRot> & Pick<IServiceRot, 'id'>;

export type EntityResponseType = HttpResponse<IServiceRot>;
export type EntityArrayResponseType = HttpResponse<IServiceRot[]>;

@Injectable({ providedIn: 'root' })
export class ServiceRotService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/service-rots');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(serviceRot: NewServiceRot): Observable<EntityResponseType> {
    return this.http.post<IServiceRot>(this.resourceUrl, serviceRot, { observe: 'response' });
  }

  update(serviceRot: IServiceRot): Observable<EntityResponseType> {
    return this.http.put<IServiceRot>(`${this.resourceUrl}/${this.getServiceRotIdentifier(serviceRot)}`, serviceRot, {
      observe: 'response',
    });
  }

  partialUpdate(serviceRot: PartialUpdateServiceRot): Observable<EntityResponseType> {
    return this.http.patch<IServiceRot>(`${this.resourceUrl}/${this.getServiceRotIdentifier(serviceRot)}`, serviceRot, {
      observe: 'response',
    });
  }

  find(id: string): Observable<EntityResponseType> {
    return this.http.get<IServiceRot>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IServiceRot[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: string): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getServiceRotIdentifier(serviceRot: Pick<IServiceRot, 'id'>): string {
    return serviceRot.id;
  }

  compareServiceRot(o1: Pick<IServiceRot, 'id'> | null, o2: Pick<IServiceRot, 'id'> | null): boolean {
    return o1 && o2 ? this.getServiceRotIdentifier(o1) === this.getServiceRotIdentifier(o2) : o1 === o2;
  }

  addServiceRotToCollectionIfMissing<Type extends Pick<IServiceRot, 'id'>>(
    serviceRotCollection: Type[],
    ...serviceRotsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const serviceRots: Type[] = serviceRotsToCheck.filter(isPresent);
    if (serviceRots.length > 0) {
      const serviceRotCollectionIdentifiers = serviceRotCollection.map(serviceRotItem => this.getServiceRotIdentifier(serviceRotItem)!);
      const serviceRotsToAdd = serviceRots.filter(serviceRotItem => {
        const serviceRotIdentifier = this.getServiceRotIdentifier(serviceRotItem);
        if (serviceRotCollectionIdentifiers.includes(serviceRotIdentifier)) {
          return false;
        }
        serviceRotCollectionIdentifiers.push(serviceRotIdentifier);
        return true;
      });
      return [...serviceRotsToAdd, ...serviceRotCollection];
    }
    return serviceRotCollection;
  }
}
