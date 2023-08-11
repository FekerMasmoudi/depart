import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBordereau } from '../bordereau.model';
import { BordereauService } from '../service/bordereau.service';

@Injectable({ providedIn: 'root' })
export class BordereauRoutingResolveService implements Resolve<IBordereau | null> {
  constructor(protected service: BordereauService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBordereau | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((bordereau: HttpResponse<IBordereau>) => {
          if (bordereau.body) {
            return of(bordereau.body);
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
