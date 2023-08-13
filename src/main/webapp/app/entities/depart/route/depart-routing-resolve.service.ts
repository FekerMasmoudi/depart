import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDepart } from '../depart.model';
import { DepartService } from '../service/depart.service';

@Injectable({ providedIn: 'root' })
export class DepartRoutingResolveService implements Resolve<IDepart | null> {
  constructor(protected service: DepartService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDepart | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((depart: HttpResponse<IDepart>) => {
          if (depart.body) {
            return of(depart.body);
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
