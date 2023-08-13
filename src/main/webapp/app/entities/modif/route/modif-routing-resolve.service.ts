import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IModif } from '../modif.model';
import { ModifService } from '../service/modif.service';

@Injectable({ providedIn: 'root' })
export class ModifRoutingResolveService implements Resolve<IModif | null> {
  constructor(protected service: ModifService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IModif | null | never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((modif: HttpResponse<IModif>) => {
          if (modif.body) {
            return of(modif.body);
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
