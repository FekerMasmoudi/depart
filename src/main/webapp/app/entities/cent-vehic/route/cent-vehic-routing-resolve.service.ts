import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICentVehic } from '../cent-vehic.model';
import { CentVehicService } from '../service/cent-vehic.service';

@Injectable({ providedIn: 'root' })
export class CentVehicRoutingResolveService implements Resolve<ICentVehic | null> {
  constructor(protected service: CentVehicService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICentVehic | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((centVehic: HttpResponse<ICentVehic>) => {
          if (centVehic.body) {
            return of(centVehic.body);
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
