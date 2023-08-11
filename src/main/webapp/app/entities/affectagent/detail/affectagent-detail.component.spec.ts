import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AffectagentDetailComponent } from './affectagent-detail.component';

describe('Affectagent Management Detail Component', () => {
  let comp: AffectagentDetailComponent;
  let fixture: ComponentFixture<AffectagentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AffectagentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ affectagent: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AffectagentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AffectagentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load affectagent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.affectagent).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
