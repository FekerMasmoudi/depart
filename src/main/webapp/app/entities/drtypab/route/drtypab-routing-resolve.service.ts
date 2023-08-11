import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDrtypab } from '../drtypab.model';
import { DrtypabService } from '../service/drtypab.service';

@Injectable({ providedIn: 'root' })
export class DrtypabRoutingResolveService implements Resolve<IDrtypab | null> {
  constructor(protected service: DrtypabService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDrtypab | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((drtypab: HttpResponse<IDrtypab>) => {
          if (drtypab.body) {
            return of(drtypab.body);
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
