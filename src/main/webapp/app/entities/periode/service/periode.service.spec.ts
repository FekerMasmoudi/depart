import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IPeriode } from '../periode.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../periode.test-samples';

import { PeriodeService } from './periode.service';

const requireRestSample: IPeriode = {
  ...sampleWithRequiredData,
};

describe('Periode Service', () => {
  let service: PeriodeService;
  let httpMock: HttpTestingController;
  let expectedResult: IPeriode | IPeriode[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(PeriodeService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find('ABC').subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a Periode', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const periode = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(periode).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Periode', () => {
      const periode = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(periode).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Periode', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Periode', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Periode', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addPeriodeToCollectionIfMissing', () => {
      it('should add a Periode to an empty array', () => {
        const periode: IPeriode = sampleWithRequiredData;
        expectedResult = service.addPeriodeToCollectionIfMissing([], periode);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(periode);
      });

      it('should not add a Periode to an array that contains it', () => {
        const periode: IPeriode = sampleWithRequiredData;
        const periodeCollection: IPeriode[] = [
          {
            ...periode,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addPeriodeToCollectionIfMissing(periodeCollection, periode);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Periode to an array that doesn't contain it", () => {
        const periode: IPeriode = sampleWithRequiredData;
        const periodeCollection: IPeriode[] = [sampleWithPartialData];
        expectedResult = service.addPeriodeToCollectionIfMissing(periodeCollection, periode);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(periode);
      });

      it('should add only unique Periode to an array', () => {
        const periodeArray: IPeriode[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const periodeCollection: IPeriode[] = [sampleWithRequiredData];
        expectedResult = service.addPeriodeToCollectionIfMissing(periodeCollection, ...periodeArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const periode: IPeriode = sampleWithRequiredData;
        const periode2: IPeriode = sampleWithPartialData;
        expectedResult = service.addPeriodeToCollectionIfMissing([], periode, periode2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(periode);
        expect(expectedResult).toContain(periode2);
      });

      it('should accept null and undefined values', () => {
        const periode: IPeriode = sampleWithRequiredData;
        expectedResult = service.addPeriodeToCollectionIfMissing([], null, periode, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(periode);
      });

      it('should return initial array if no Periode is added', () => {
        const periodeCollection: IPeriode[] = [sampleWithRequiredData];
        expectedResult = service.addPeriodeToCollectionIfMissing(periodeCollection, undefined, null);
        expect(expectedResult).toEqual(periodeCollection);
      });
    });

    describe('comparePeriode', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.comparePeriode(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.comparePeriode(entity1, entity2);
        const compareResult2 = service.comparePeriode(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.comparePeriode(entity1, entity2);
        const compareResult2 = service.comparePeriode(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.comparePeriode(entity1, entity2);
        const compareResult2 = service.comparePeriode(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
