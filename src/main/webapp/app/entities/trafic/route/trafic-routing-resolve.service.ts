import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ITrafic } from '../trafic.model';
import { TraficService } from '../service/trafic.service';

@Injectable({ providedIn: 'root' })
export class TraficRoutingResolveService implements Resolve<ITrafic | null> {
  constructor(protected service: TraficService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ITrafic | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((trafic: HttpResponse<ITrafic>) => {
          if (trafic.body) {
            return of(trafic.body);
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
