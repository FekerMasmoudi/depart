import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ServiceRotDetailComponent } from './service-rot-detail.component';

describe('ServiceRot Management Detail Component', () => {
  let comp: ServiceRotDetailComponent;
  let fixture: ComponentFixture<ServiceRotDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ServiceRotDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ serviceRot: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ServiceRotDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ServiceRotDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load serviceRot on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.serviceRot).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
