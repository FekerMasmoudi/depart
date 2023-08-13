import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { RhAgentFormService } from './rh-agent-form.service';
import { RhAgentService } from '../service/rh-agent.service';
import { IRhAgent } from '../rh-agent.model';

import { RhAgentUpdateComponent } from './rh-agent-update.component';

describe('RhAgent Management Update Component', () => {
  let comp: RhAgentUpdateComponent;
  let fixture: ComponentFixture<RhAgentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let rhAgentFormService: RhAgentFormService;
  let rhAgentService: RhAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [RhAgentUpdateComponent],
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
      .overrideTemplate(RhAgentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RhAgentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    rhAgentFormService = TestBed.inject(RhAgentFormService);
    rhAgentService = TestBed.inject(RhAgentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const rhAgent: IRhAgent = { id: 'CBA' };

      activatedRoute.data = of({ rhAgent });
      comp.ngOnInit();

      expect(comp.rhAgent).toEqual(rhAgent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRhAgent>>();
      const rhAgent = { id: 'ABC' };
      jest.spyOn(rhAgentFormService, 'getRhAgent').mockReturnValue(rhAgent);
      jest.spyOn(rhAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rhAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rhAgent }));
      saveSubject.complete();

      // THEN
      expect(rhAgentFormService.getRhAgent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(rhAgentService.update).toHaveBeenCalledWith(expect.objectContaining(rhAgent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRhAgent>>();
      const rhAgent = { id: 'ABC' };
      jest.spyOn(rhAgentFormService, 'getRhAgent').mockReturnValue({ id: null });
      jest.spyOn(rhAgentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rhAgent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: rhAgent }));
      saveSubject.complete();

      // THEN
      expect(rhAgentFormService.getRhAgent).toHaveBeenCalled();
      expect(rhAgentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IRhAgent>>();
      const rhAgent = { id: 'ABC' };
      jest.spyOn(rhAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ rhAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(rhAgentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
