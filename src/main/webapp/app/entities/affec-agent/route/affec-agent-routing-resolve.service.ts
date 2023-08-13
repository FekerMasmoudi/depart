import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IAffecAgent } from '../affec-agent.model';
import { AffecAgentService } from '../service/affec-agent.service';

@Injectable({ providedIn: 'root' })
export class AffecAgentRoutingResolveService implements Resolve<IAffecAgent | null> {
  constructor(protected service: AffecAgentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IAffecAgent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((affecAgent: HttpResponse<IAffecAgent>) => {
          if (affecAgent.body) {
            return of(affecAgent.body);
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
