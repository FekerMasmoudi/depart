import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { AffecAgentFormService } from './affec-agent-form.service';
import { AffecAgentService } from '../service/affec-agent.service';
import { IAffecAgent } from '../affec-agent.model';

import { AffecAgentUpdateComponent } from './affec-agent-update.component';

describe('AffecAgent Management Update Component', () => {
  let comp: AffecAgentUpdateComponent;
  let fixture: ComponentFixture<AffecAgentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let affecAgentFormService: AffecAgentFormService;
  let affecAgentService: AffecAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [AffecAgentUpdateComponent],
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
      .overrideTemplate(AffecAgentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(AffecAgentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    affecAgentFormService = TestBed.inject(AffecAgentFormService);
    affecAgentService = TestBed.inject(AffecAgentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const affecAgent: IAffecAgent = { id: 'CBA' };

      activatedRoute.data = of({ affecAgent });
      comp.ngOnInit();

      expect(comp.affecAgent).toEqual(affecAgent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffecAgent>>();
      const affecAgent = { id: 'ABC' };
      jest.spyOn(affecAgentFormService, 'getAffecAgent').mockReturnValue(affecAgent);
      jest.spyOn(affecAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affecAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: affecAgent }));
      saveSubject.complete();

      // THEN
      expect(affecAgentFormService.getAffecAgent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(affecAgentService.update).toHaveBeenCalledWith(expect.objectContaining(affecAgent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffecAgent>>();
      const affecAgent = { id: 'ABC' };
      jest.spyOn(affecAgentFormService, 'getAffecAgent').mockReturnValue({ id: null });
      jest.spyOn(affecAgentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affecAgent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: affecAgent }));
      saveSubject.complete();

      // THEN
      expect(affecAgentFormService.getAffecAgent).toHaveBeenCalled();
      expect(affecAgentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IAffecAgent>>();
      const affecAgent = { id: 'ABC' };
      jest.spyOn(affecAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ affecAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(affecAgentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
