import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDrabsen } from '../drabsen.model';
import { DrabsenService } from '../service/drabsen.service';

@Injectable({ providedIn: 'root' })
export class DrabsenRoutingResolveService implements Resolve<IDrabsen | null> {
  constructor(protected service: DrabsenService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDrabsen | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((drabsen: HttpResponse<IDrabsen>) => {
          if (drabsen.body) {
            return of(drabsen.body);
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
