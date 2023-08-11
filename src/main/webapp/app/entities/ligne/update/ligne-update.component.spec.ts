import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { LigneFormService } from './ligne-form.service';
import { LigneService } from '../service/ligne.service';
import { ILigne } from '../ligne.model';

import { LigneUpdateComponent } from './ligne-update.component';

describe('Ligne Management Update Component', () => {
  let comp: LigneUpdateComponent;
  let fixture: ComponentFixture<LigneUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ligneFormService: LigneFormService;
  let ligneService: LigneService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [LigneUpdateComponent],
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
      .overrideTemplate(LigneUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(LigneUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ligneFormService = TestBed.inject(LigneFormService);
    ligneService = TestBed.inject(LigneService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ligne: ILigne = { id: 'CBA' };

      activatedRoute.data = of({ ligne });
      comp.ngOnInit();

      expect(comp.ligne).toEqual(ligne);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILigne>>();
      const ligne = { id: 'ABC' };
      jest.spyOn(ligneFormService, 'getLigne').mockReturnValue(ligne);
      jest.spyOn(ligneService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ligne });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ligne }));
      saveSubject.complete();

      // THEN
      expect(ligneFormService.getLigne).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ligneService.update).toHaveBeenCalledWith(expect.objectContaining(ligne));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILigne>>();
      const ligne = { id: 'ABC' };
      jest.spyOn(ligneFormService, 'getLigne').mockReturnValue({ id: null });
      jest.spyOn(ligneService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ligne: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ligne }));
      saveSubject.complete();

      // THEN
      expect(ligneFormService.getLigne).toHaveBeenCalled();
      expect(ligneService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<ILigne>>();
      const ligne = { id: 'ABC' };
      jest.spyOn(ligneService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ligne });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ligneService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
