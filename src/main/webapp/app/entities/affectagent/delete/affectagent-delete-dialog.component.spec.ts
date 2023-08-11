jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { AffectagentService } from '../service/affectagent.service';

import { AffectagentDeleteDialogComponent } from './affectagent-delete-dialog.component';

describe('Affectagent Management Delete Component', () => {
  let comp: AffectagentDeleteDialogComponent;
  let fixture: ComponentFixture<AffectagentDeleteDialogComponent>;
  let service: AffectagentService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AffectagentDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(AffectagentDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AffectagentDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AffectagentService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete('ABC');
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith('ABC');
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      })
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
