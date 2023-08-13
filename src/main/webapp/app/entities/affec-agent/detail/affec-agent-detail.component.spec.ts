import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { AffecAgentDetailComponent } from './affec-agent-detail.component';

describe('AffecAgent Management Detail Component', () => {
  let comp: AffecAgentDetailComponent;
  let fixture: ComponentFixture<AffecAgentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AffecAgentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ affecAgent: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(AffecAgentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(AffecAgentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load affecAgent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.affecAgent).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
