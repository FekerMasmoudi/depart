import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAffectagent } from '../affectagent.model';
import { AffectagentService } from '../service/affectagent.service';

@Injectable({ providedIn: 'root' })
export class AffectagentRoutingResolveService implements Resolve<IAffectagent | null> {
  constructor(protected service: AffectagentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAffectagent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((affectagent: HttpResponse<IAffectagent>) => {
          if (affectagent.body) {
            return of(affectagent.body);
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
