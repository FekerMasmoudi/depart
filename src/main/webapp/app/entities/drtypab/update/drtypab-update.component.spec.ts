import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DrtypabFormService } from './drtypab-form.service';
import { DrtypabService } from '../service/drtypab.service';
import { IDrtypab } from '../drtypab.model';

import { DrtypabUpdateComponent } from './drtypab-update.component';

describe('Drtypab Management Update Component', () => {
  let comp: DrtypabUpdateComponent;
  let fixture: ComponentFixture<DrtypabUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let drtypabFormService: DrtypabFormService;
  let drtypabService: DrtypabService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DrtypabUpdateComponent],
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
      .overrideTemplate(DrtypabUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DrtypabUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    drtypabFormService = TestBed.inject(DrtypabFormService);
    drtypabService = TestBed.inject(DrtypabService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const drtypab: IDrtypab = { id: 'CBA' };

      activatedRoute.data = of({ drtypab });
      comp.ngOnInit();

      expect(comp.drtypab).toEqual(drtypab);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrtypab>>();
      const drtypab = { id: 'ABC' };
      jest.spyOn(drtypabFormService, 'getDrtypab').mockReturnValue(drtypab);
      jest.spyOn(drtypabService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drtypab });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: drtypab }));
      saveSubject.complete();

      // THEN
      expect(drtypabFormService.getDrtypab).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(drtypabService.update).toHaveBeenCalledWith(expect.objectContaining(drtypab));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrtypab>>();
      const drtypab = { id: 'ABC' };
      jest.spyOn(drtypabFormService, 'getDrtypab').mockReturnValue({ id: null });
      jest.spyOn(drtypabService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drtypab: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: drtypab }));
      saveSubject.complete();

      // THEN
      expect(drtypabFormService.getDrtypab).toHaveBeenCalled();
      expect(drtypabService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDrtypab>>();
      const drtypab = { id: 'ABC' };
      jest.spyOn(drtypabService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ drtypab });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(drtypabService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
