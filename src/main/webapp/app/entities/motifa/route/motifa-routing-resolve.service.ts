import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMotifa } from '../motifa.model';
import { MotifaService } from '../service/motifa.service';

@Injectable({ providedIn: 'root' })
export class MotifaRoutingResolveService implements Resolve<IMotifa | null> {
  constructor(protected service: MotifaService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMotifa | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((motifa: HttpResponse<IMotifa>) => {
          if (motifa.body) {
            return of(motifa.body);
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
