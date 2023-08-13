import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFoncAgent } from '../fonc-agent.model';
import { FoncAgentService } from '../service/fonc-agent.service';

@Injectable({ providedIn: 'root' })
export class FoncAgentRoutingResolveService implements Resolve<IFoncAgent | null> {
  constructor(protected service: FoncAgentService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFoncAgent | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((foncAgent: HttpResponse<IFoncAgent>) => {
          if (foncAgent.body) {
            return of(foncAgent.body);
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
