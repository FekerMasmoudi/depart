import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { FoncAgentFormService } from './fonc-agent-form.service';
import { FoncAgentService } from '../service/fonc-agent.service';
import { IFoncAgent } from '../fonc-agent.model';

import { FoncAgentUpdateComponent } from './fonc-agent-update.component';

describe('FoncAgent Management Update Component', () => {
  let comp: FoncAgentUpdateComponent;
  let fixture: ComponentFixture<FoncAgentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let foncAgentFormService: FoncAgentFormService;
  let foncAgentService: FoncAgentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [FoncAgentUpdateComponent],
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
      .overrideTemplate(FoncAgentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FoncAgentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    foncAgentFormService = TestBed.inject(FoncAgentFormService);
    foncAgentService = TestBed.inject(FoncAgentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const foncAgent: IFoncAgent = { id: 'CBA' };

      activatedRoute.data = of({ foncAgent });
      comp.ngOnInit();

      expect(comp.foncAgent).toEqual(foncAgent);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoncAgent>>();
      const foncAgent = { id: 'ABC' };
      jest.spyOn(foncAgentFormService, 'getFoncAgent').mockReturnValue(foncAgent);
      jest.spyOn(foncAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ foncAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: foncAgent }));
      saveSubject.complete();

      // THEN
      expect(foncAgentFormService.getFoncAgent).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(foncAgentService.update).toHaveBeenCalledWith(expect.objectContaining(foncAgent));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoncAgent>>();
      const foncAgent = { id: 'ABC' };
      jest.spyOn(foncAgentFormService, 'getFoncAgent').mockReturnValue({ id: null });
      jest.spyOn(foncAgentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ foncAgent: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: foncAgent }));
      saveSubject.complete();

      // THEN
      expect(foncAgentFormService.getFoncAgent).toHaveBeenCalled();
      expect(foncAgentService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFoncAgent>>();
      const foncAgent = { id: 'ABC' };
      jest.spyOn(foncAgentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ foncAgent });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(foncAgentService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
