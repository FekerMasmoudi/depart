import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RotRservFormService } from './rot-rserv-form.service';
import { RotRservService } from '../service/rot-rserv.service';
import { IRotRserv } from '../rot-rserv.model';

import { RotRservUpdateComponent } from './rot-rserv-update.component';

describe('RotRserv Management Update Component', () => {
  let comp: RotRservUpdateComponent;
  let fixture: ComponentFixture<RotRservUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rotRservFormService: RotRservFormService;
  let rotRservService: RotRservService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RotRservUpdateComponent],
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
      .overrideTemplate(RotRservUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RotRservUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rotRservFormService = TestBed.inject(RotRservFormService);
    rotRservService = TestBed.inject(RotRservService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const rotRserv: IRotRserv = { id: 'CBA' };

      activatedRoute.data = of({ rotRserv });
      comp.ngOnInit();

      expect(comp.rotRserv).toEqual(rotRserv);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRotRserv>>();
      const rotRserv = { id: 'ABC' };
      jest.spyOn(rotRservFormService, 'getRotRserv').mockReturnValue(rotRserv);
      jest.spyOn(rotRservService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rotRserv });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rotRserv }));
      saveSubject.complete();

      // THEN
      expect(rotRservFormService.getRotRserv).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(rotRservService.update).toHaveBeenCalledWith(expect.objectContaining(rotRserv));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRotRserv>>();
      const rotRserv = { id: 'ABC' };
      jest.spyOn(rotRservFormService, 'getRotRserv').mockReturnValue({ id: null });
      jest.spyOn(rotRservService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rotRserv: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rotRserv }));
      saveSubject.complete();

      // THEN
      expect(rotRservFormService.getRotRserv).toHaveBeenCalled();
      expect(rotRservService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRotRserv>>();
      const rotRserv = { id: 'ABC' };
      jest.spyOn(rotRservService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rotRserv });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rotRservService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
