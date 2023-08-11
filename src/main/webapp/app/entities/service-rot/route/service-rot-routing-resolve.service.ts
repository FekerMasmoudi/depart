import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IServiceRot } from '../service-rot.model';
import { ServiceRotService } from '../service/service-rot.service';

@Injectable({ providedIn: 'root' })
export class ServiceRotRoutingResolveService implements Resolve<IServiceRot | null> {
  constructor(protected service: ServiceRotService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IServiceRot | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((serviceRot: HttpResponse<IServiceRot>) => {
          if (serviceRot.body) {
            return of(serviceRot.body);
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
