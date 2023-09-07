import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { DepartService } from '../service/depart.service';

import { DepartComponent } from './depart.component';

describe('Depart Management Component', () => {
  let comp: DepartComponent;
  let fixture: ComponentFixture<DepartComponent>;
  let service: DepartService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [RouterTestingModule.withRoutes([{ path: 'depart', component: DepartComponent }]), HttpClientTestingModule],
      declarations: [DepartComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            data: of({
              defaultSort: 'id,asc',
            }),
            queryParamMap: of(
              jest.requireActual('@angular/router').convertToParamMap({
                page: '1',
                size: '1',
                sort: 'id,desc',
              })
            ),
            snapshot: { queryParams: {} },
          },
        },
      ],
    })
      .overrideTemplate(DepartComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DepartComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(DepartService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 'ABC' }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.departs?.[0]).toEqual(expect.objectContaining({ id: 'ABC' }));
  });

  describe('trackId', () => {
    it('Should forward to departService', () => {
      const entity = { id: 'ABC' };
      jest.spyOn(service, 'getDepartIdentifier');
      const id = comp.trackId(0, entity);
      expect(service.getDepartIdentifier).toHaveBeenCalledWith(entity);
      expect(id).toBe(entity.id);
    });
  });
});
