import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IExternalApi } from '../external-api.model';
import { ExternalApiService } from '../service/external-api.service';

@Injectable({ providedIn: 'root' })
export class ExternalApiRoutingResolveService implements Resolve<IExternalApi | null> {
  constructor(protected service: ExternalApiService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExternalApi | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((externalApi: HttpResponse<IExternalApi>) => {
          if (externalApi.body) {
            return of(externalApi.body);
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
