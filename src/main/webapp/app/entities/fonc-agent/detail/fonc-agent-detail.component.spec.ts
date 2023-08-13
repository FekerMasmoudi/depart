import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { FoncAgentDetailComponent } from './fonc-agent-detail.component';

describe('FoncAgent Management Detail Component', () => {
  let comp: FoncAgentDetailComponent;
  let fixture: ComponentFixture<FoncAgentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FoncAgentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ foncAgent: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(FoncAgentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FoncAgentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load foncAgent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.foncAgent).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
