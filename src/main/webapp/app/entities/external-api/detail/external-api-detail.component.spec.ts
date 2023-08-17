import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ExternalApiDetailComponent } from './external-api-detail.component';

describe('ExternalApi Management Detail Component', () => {
  let comp: ExternalApiDetailComponent;
  let fixture: ComponentFixture<ExternalApiDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ExternalApiDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ externalApi: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ExternalApiDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ExternalApiDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load externalApi on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.externalApi).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
