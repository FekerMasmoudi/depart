import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RotRservDetailComponent } from './rot-rserv-detail.component';

describe('RotRserv Management Detail Component', () => {
  let comp: RotRservDetailComponent;
  let fixture: ComponentFixture<RotRservDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RotRservDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ rotRserv: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(RotRservDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RotRservDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load rotRserv on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.rotRserv).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
