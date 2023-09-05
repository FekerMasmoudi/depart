import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DisplaybusDetailComponent } from './displaybus-detail.component';

describe('Displaybus Management Detail Component', () => {
  let comp: DisplaybusDetailComponent;
  let fixture: ComponentFixture<DisplaybusDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DisplaybusDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ displaybus: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(DisplaybusDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DisplaybusDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load displaybus on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.displaybus).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
