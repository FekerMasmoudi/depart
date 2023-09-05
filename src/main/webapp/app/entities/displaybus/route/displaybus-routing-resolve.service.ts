import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IDisplaybus } from '../displaybus.model';
import { DisplaybusService } from '../service/displaybus.service';

@Injectable({ providedIn: 'root' })
export class DisplaybusRoutingResolveService implements Resolve<IDisplaybus | null> {
  constructor(protected service: DisplaybusService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IDisplaybus | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((displaybus: HttpResponse<IDisplaybus>) => {
          if (displaybus.body) {
            return of(displaybus.body);
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
