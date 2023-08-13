import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRotRserv } from '../rot-rserv.model';
import { RotRservService } from '../service/rot-rserv.service';

@Injectable({ providedIn: 'root' })
export class RotRservRoutingResolveService implements Resolve<IRotRserv | null> {
  constructor(protected service: RotRservService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRotRserv | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((rotRserv: HttpResponse<IRotRserv>) => {
          if (rotRserv.body) {
            return of(rotRserv.body);
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
