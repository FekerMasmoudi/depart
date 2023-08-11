import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRhAgent } from '../rh-agent.model';
import { RhAgentService } from '../service/rh-agent.service';

@Injectable({ providedIn: 'root' })
export class RhAgentRoutingResolveService implements Resolve<IRhAgent | null> {
  constructor(protected service: RhAgentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRhAgent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((rhAgent: HttpResponse<IRhAgent>) => {
          if (rhAgent.body) {
            return of(rhAgent.body);
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
