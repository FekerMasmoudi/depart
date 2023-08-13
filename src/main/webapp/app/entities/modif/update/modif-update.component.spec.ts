import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { ModifFormService } from './modif-form.service';
import { ModifService } from '../service/modif.service';
import { IModif } from '../modif.model';

import { ModifUpdateComponent } from './modif-update.component';

describe('Modif Management Update Component', () => {
  let comp: ModifUpdateComponent;
  let fixture: ComponentFixture<ModifUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let modifFormService: ModifFormService;
  let modifService: ModifService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [ModifUpdateComponent],
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
      .overrideTemplate(ModifUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(ModifUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    modifFormService = TestBed.inject(ModifFormService);
    modifService = TestBed.inject(ModifService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const modif: IModif = { id: 'CBA' };

      activatedRoute.data = of({ modif });
      comp.ngOnInit();

      expect(comp.modif).toEqual(modif);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IModif>>();
      const modif = { id: 'ABC' };
      jest.spyOn(modifFormService, 'getModif').mockReturnValue(modif);
      jest.spyOn(modifService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ modif });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: modif }));
      saveSubject.complete();

      // THEN
      expect(modifFormService.getModif).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(modifService.update).toHaveBeenCalledWith(expect.objectContaining(modif));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IModif>>();
      const modif = { id: 'ABC' };
      jest.spyOn(modifFormService, 'getModif').mockReturnValue({ id: null });
      jest.spyOn(modifService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ modif: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: modif }));
      saveSubject.complete();

      // THEN
      expect(modifFormService.getModif).toHaveBeenCalled();
      expect(modifService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IModif>>();
      const modif = { id: 'ABC' };
      jest.spyOn(modifService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ modif });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(modifService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
