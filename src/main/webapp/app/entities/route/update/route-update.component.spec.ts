import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RouteFormService } from './route-form.service';
import { RouteService } from '../service/route.service';
import { IRoute } from '../route.model';

import { RouteUpdateComponent } from './route-update.component';

describe('Route Management Update Component', () => {
  let comp: RouteUpdateComponent;
  let fixture: ComponentFixture<RouteUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let routeFormService: RouteFormService;
  let routeService: RouteService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RouteUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(RouteUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RouteUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    routeFormService = TestBed.inject(RouteFormService);
    routeService = TestBed.inject(RouteService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const route: IRoute = { id: 'CBA' };

      activatedRoute.data = of({ route });
      comp.ngOnInit();

      expect(comp.route).toEqual(route);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRoute>>();
      const route = { id: 'ABC' };
      jest.spyOn(routeFormService, 'getRoute').mockReturnValue(route);
      jest.spyOn(routeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ route });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: route }));
      saveSubject.complete();

      // THEN
      expect(routeFormService.getRoute).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(routeService.update).toHaveBeenCalledWith(expect.objectContaining(route));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRoute>>();
      const route = { id: 'ABC' };
      jest.spyOn(routeFormService, 'getRoute').mockReturnValue({ id: null });
      jest.spyOn(routeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ route: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: route }));
      saveSubject.complete();

      // THEN
      expect(routeFormService.getRoute).toHaveBeenCalled();
      expect(routeService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRoute>>();
      const route = { id: 'ABC' };
      jest.spyOn(routeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ route });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(routeService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
