import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RhAgentDetailComponent } from './rh-agent-detail.component';

describe('RhAgent Management Detail Component', () => {
  let comp: RhAgentDetailComponent;
  let fixture: ComponentFixture<RhAgentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RhAgentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ rhAgent: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(RhAgentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RhAgentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load rhAgent on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.rhAgent).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
