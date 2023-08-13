jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { AffecAgentService } from '../service/affec-agent.service';

import { AffecAgentDeleteDialogComponent } from './affec-agent-delete-dialog.component';

describe('AffecAgent Management Delete Component', () => {
  let comp: AffecAgentDeleteDialogComponent;
  let fixture: ComponentFixture<AffecAgentDeleteDialogComponent>;
  let service: AffecAgentService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [AffecAgentDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(AffecAgentDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AffecAgentDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(AffecAgentService);
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
