import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDeprotat } from '../deprotat.model';
import { DeprotatService } from '../service/deprotat.service';

@Injectable({ providedIn: 'root' })
export class DeprotatRoutingResolveService implements Resolve<IDeprotat | null> {
  constructor(protected service: DeprotatService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDeprotat | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((deprotat: HttpResponse<IDeprotat>) => {
          if (deprotat.body) {
            return of(deprotat.body);
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
