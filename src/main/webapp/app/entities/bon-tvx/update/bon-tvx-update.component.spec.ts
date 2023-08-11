import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { BonTvxFormService } from './bon-tvx-form.service';
import { BonTvxService } from '../service/bon-tvx.service';
import { IBonTvx } from '../bon-tvx.model';

import { BonTvxUpdateComponent } from './bon-tvx-update.component';

describe('BonTvx Management Update Component', () => {
  let comp: BonTvxUpdateComponent;
  let fixture: ComponentFixture<BonTvxUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let bonTvxFormService: BonTvxFormService;
  let bonTvxService: BonTvxService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [BonTvxUpdateComponent],
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
      .overrideTemplate(BonTvxUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(BonTvxUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    bonTvxFormService = TestBed.inject(BonTvxFormService);
    bonTvxService = TestBed.inject(BonTvxService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const bonTvx: IBonTvx = { id: 'CBA' };

      activatedRoute.data = of({ bonTvx });
      comp.ngOnInit();

      expect(comp.bonTvx).toEqual(bonTvx);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBonTvx>>();
      const bonTvx = { id: 'ABC' };
      jest.spyOn(bonTvxFormService, 'getBonTvx').mockReturnValue(bonTvx);
      jest.spyOn(bonTvxService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bonTvx });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bonTvx }));
      saveSubject.complete();

      // THEN
      expect(bonTvxFormService.getBonTvx).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(bonTvxService.update).toHaveBeenCalledWith(expect.objectContaining(bonTvx));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBonTvx>>();
      const bonTvx = { id: 'ABC' };
      jest.spyOn(bonTvxFormService, 'getBonTvx').mockReturnValue({ id: null });
      jest.spyOn(bonTvxService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bonTvx: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: bonTvx }));
      saveSubject.complete();

      // THEN
      expect(bonTvxFormService.getBonTvx).toHaveBeenCalled();
      expect(bonTvxService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IBonTvx>>();
      const bonTvx = { id: 'ABC' };
      jest.spyOn(bonTvxService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ bonTvx });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(bonTvxService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
