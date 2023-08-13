import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IBonTvx } from '../bon-tvx.model';
import { BonTvxService } from '../service/bon-tvx.service';

@Injectable({ providedIn: 'root' })
export class BonTvxRoutingResolveService implements Resolve<IBonTvx | null> {
  constructor(protected service: BonTvxService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBonTvx | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((bonTvx: HttpResponse<IBonTvx>) => {
          if (bonTvx.body) {
            return of(bonTvx.body);
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
