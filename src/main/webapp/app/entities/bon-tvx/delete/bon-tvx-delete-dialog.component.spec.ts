jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { BonTvxService } from '../service/bon-tvx.service';

import { BonTvxDeleteDialogComponent } from './bon-tvx-delete-dialog.component';

describe('BonTvx Management Delete Component', () => {
  let comp: BonTvxDeleteDialogComponent;
  let fixture: ComponentFixture<BonTvxDeleteDialogComponent>;
  let service: BonTvxService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [BonTvxDeleteDialogComponent],
      providers: [NgbActiveModal],
    })
      .overrideTemplate(BonTvxDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(BonTvxDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(BonTvxService);
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
