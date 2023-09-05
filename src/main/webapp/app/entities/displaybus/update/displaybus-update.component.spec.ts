import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DisplaybusFormService } from './displaybus-form.service';
import { DisplaybusService } from '../service/displaybus.service';
import { IDisplaybus } from '../displaybus.model';

import { DisplaybusUpdateComponent } from './displaybus-update.component';

describe('Displaybus Management Update Component', () => {
  let comp: DisplaybusUpdateComponent;
  let fixture: ComponentFixture<DisplaybusUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let displaybusFormService: DisplaybusFormService;
  let displaybusService: DisplaybusService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DisplaybusUpdateComponent],
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
      .overrideTemplate(DisplaybusUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DisplaybusUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    displaybusFormService = TestBed.inject(DisplaybusFormService);
    displaybusService = TestBed.inject(DisplaybusService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const displaybus: IDisplaybus = { id: 'CBA' };

      activatedRoute.data = of({ displaybus });
      comp.ngOnInit();

      expect(comp.displaybus).toEqual(displaybus);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisplaybus>>();
      const displaybus = { id: 'ABC' };
      jest.spyOn(displaybusFormService, 'getDisplaybus').mockReturnValue(displaybus);
      jest.spyOn(displaybusService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ displaybus });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: displaybus }));
      saveSubject.complete();

      // THEN
      expect(displaybusFormService.getDisplaybus).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(displaybusService.update).toHaveBeenCalledWith(expect.objectContaining(displaybus));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisplaybus>>();
      const displaybus = { id: 'ABC' };
      jest.spyOn(displaybusFormService, 'getDisplaybus').mockReturnValue({ id: null });
      jest.spyOn(displaybusService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ displaybus: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: displaybus }));
      saveSubject.complete();

      // THEN
      expect(displaybusFormService.getDisplaybus).toHaveBeenCalled();
      expect(displaybusService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IDisplaybus>>();
      const displaybus = { id: 'ABC' };
      jest.spyOn(displaybusService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ displaybus });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(displaybusService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
