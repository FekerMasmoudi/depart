import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IItineraire } from '../itineraire.model';
import { ItineraireService } from '../service/itineraire.service';

@Injectable({ providedIn: 'root' })
export class ItineraireRoutingResolveService implements Resolve<IItineraire | null> {
  constructor(protected service: ItineraireService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IItineraire | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((itineraire: HttpResponse<IItineraire>) => {
          if (itineraire.body) {
            return of(itineraire.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(null);
  }
}
