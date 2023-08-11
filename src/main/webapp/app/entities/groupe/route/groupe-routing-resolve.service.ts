import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IGroupe } from '../groupe.model';
import { GroupeService } from '../service/groupe.service';

@Injectable({ providedIn: 'root' })
export class GroupeRoutingResolveService implements Resolve<IGroupe | null> {
  constructor(protected service: GroupeService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IGroupe | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((groupe: HttpResponse<IGroupe>) => {
          if (groupe.body) {
            return of(groupe.body);
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
