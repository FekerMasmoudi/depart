import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ExternalApiFormService } from './external-api-form.service';
import { ExternalApiService } from '../service/external-api.service';
import { IExternalApi } from '../external-api.model';

import { ExternalApiUpdateComponent } from './external-api-update.component';

describe('ExternalApi Management Update Component', () => {
  let comp: ExternalApiUpdateComponent;
  let fixture: ComponentFixture<ExternalApiUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let externalApiFormService: ExternalApiFormService;
  let externalApiService: ExternalApiService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ExternalApiUpdateComponent],
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
      .overrideTemplate(ExternalApiUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ExternalApiUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    externalApiFormService = TestBed.inject(ExternalApiFormService);
    externalApiService = TestBed.inject(ExternalApiService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const externalApi: IExternalApi = { id: 'CBA' };

      activatedRoute.data = of({ externalApi });
      comp.ngOnInit();

      expect(comp.externalApi).toEqual(externalApi);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExternalApi>>();
      const externalApi = { id: 'ABC' };
      jest.spyOn(externalApiFormService, 'getExternalApi').mockReturnValue(externalApi);
      jest.spyOn(externalApiService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ externalApi });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: externalApi }));
      saveSubject.complete();

      // THEN
      expect(externalApiFormService.getExternalApi).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(externalApiService.update).toHaveBeenCalledWith(expect.objectContaining(externalApi));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExternalApi>>();
      const externalApi = { id: 'ABC' };
      jest.spyOn(externalApiFormService, 'getExternalApi').mockReturnValue({ id: null });
      jest.spyOn(externalApiService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ externalApi: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: externalApi }));
      saveSubject.complete();

      // THEN
      expect(externalApiFormService.getExternalApi).toHaveBeenCalled();
      expect(externalApiService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IExternalApi>>();
      const externalApi = { id: 'ABC' };
      jest.spyOn(externalApiService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ externalApi });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(externalApiService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
