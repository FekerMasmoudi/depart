import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITypStat } from '../typ-stat.model';
import { TypStatService } from '../service/typ-stat.service';

@Injectable({ providedIn: 'root' })
export class TypStatRoutingResolveService implements Resolve<ITypStat | null> {
  constructor(protected service: TypStatService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITypStat | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((typStat: HttpResponse<ITypStat>) => {
          if (typStat.body) {
            return of(typStat.body);
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
