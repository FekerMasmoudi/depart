import { Injectable } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';

import { IRoute, NewRoute } from '../route.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IRoute for edit and NewRouteFormGroupInput for create.
 */
type RouteFormGroupInput = IRoute | PartialWithRequiredKeyOf<NewRoute>;

type RouteFormDefaults = Pick<NewRoute, 'id'>;

type RouteFormGroupContent = {
  id: FormControl<IRoute['id'] | NewRoute['id']>;
  agency_id: FormControl<IRoute['agency_id']>;
  route_short_name: FormControl<IRoute['route_short_name']>;
  route_long_name: FormControl<IRoute['route_long_name']>;
  route_desc: FormControl<IRoute['route_desc']>;
  route_type: FormControl<IRoute['route_type']>;
};

export type RouteFormGroup = FormGroup<RouteFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class RouteFormService {
  createRouteFormGroup(route: RouteFormGroupInput = { id: null }): RouteFormGroup {
    const routeRawValue = {
      ...this.getFormDefaults(),
      ...route,
    };
    return new FormGroup<RouteFormGroupContent>({
      id: new FormControl(
        { value: routeRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        }
      ),
      agency_id: new FormControl(routeRawValue.agency_id, {
        validators: [Validators.required],
      }),
      route_short_name: new FormControl(routeRawValue.route_short_name, {
        validators: [Validators.required],
      }),
      route_long_name: new FormControl(routeRawValue.route_long_name, {
        validators: [Validators.required],
      }),
      route_desc: new FormControl(routeRawValue.route_desc, {
        validators: [Validators.required],
      }),
      route_type: new FormControl(routeRawValue.route_type, {
        validators: [Validators.required],
      }),
    });
  }

  getRoute(form: RouteFormGroup): IRoute | NewRoute {
    return form.getRawValue() as IRoute | NewRoute;
  }

  resetForm(form: RouteFormGroup, route: RouteFormGroupInput): void {
    const routeRawValue = { ...this.getFormDefaults(), ...route };
    form.reset(
      {
        ...routeRawValue,
        id: { value: routeRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */
    );
  }

  private getFormDefaults(): RouteFormDefaults {
    return {
      id: null,
    };
  }
}
