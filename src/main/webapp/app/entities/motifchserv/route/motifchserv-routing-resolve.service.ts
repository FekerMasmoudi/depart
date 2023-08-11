import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IMotifchserv } from '../motifchserv.model';
import { MotifchservService } from '../service/motifchserv.service';

@Injectable({ providedIn: 'root' })
export class MotifchservRoutingResolveService implements Resolve<IMotifchserv | null> {
  constructor(protected service: MotifchservService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IMotifchserv | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((motifchserv: HttpResponse<IMotifchserv>) => {
          if (motifchserv.body) {
            return of(motifchserv.body);
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
