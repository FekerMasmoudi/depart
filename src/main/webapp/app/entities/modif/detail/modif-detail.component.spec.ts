import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ModifDetailComponent } from './modif-detail.component';

describe('Modif Management Detail Component', () => {
  let comp: ModifDetailComponent;
  let fixture: ComponentFixture<ModifDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ModifDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ modif: { id: 'ABC' } }) },
        },
      ],
    })
      .overrideTemplate(ModifDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(ModifDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load modif on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.modif).toEqual(expect.objectContaining({ id: 'ABC' }));
    });
  });
});
