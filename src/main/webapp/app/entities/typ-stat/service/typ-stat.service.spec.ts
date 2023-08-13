import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ITypStat } from '../typ-stat.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../typ-stat.test-samples';

import { TypStatService } from './typ-stat.service';

const requireRestSample: ITypStat = {
  ...sampleWithRequiredData,
};

describe('TypStat Service', () => {
  let service: TypStatService;
  let httpMock: HttpTestingController;
  let expectedResult: ITypStat | ITypStat[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TypStatService);
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

    it('should create a TypStat', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const typStat = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(typStat).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a TypStat', () => {
      const typStat = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(typStat).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a TypStat', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of TypStat', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a TypStat', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTypStatToCollectionIfMissing', () => {
      it('should add a TypStat to an empty array', () => {
        const typStat: ITypStat = sampleWithRequiredData;
        expectedResult = service.addTypStatToCollectionIfMissing([], typStat);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(typStat);
      });

      it('should not add a TypStat to an array that contains it', () => {
        const typStat: ITypStat = sampleWithRequiredData;
        const typStatCollection: ITypStat[] = [
          {
            ...typStat,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTypStatToCollectionIfMissing(typStatCollection, typStat);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a TypStat to an array that doesn't contain it", () => {
        const typStat: ITypStat = sampleWithRequiredData;
        const typStatCollection: ITypStat[] = [sampleWithPartialData];
        expectedResult = service.addTypStatToCollectionIfMissing(typStatCollection, typStat);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(typStat);
      });

      it('should add only unique TypStat to an array', () => {
        const typStatArray: ITypStat[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const typStatCollection: ITypStat[] = [sampleWithRequiredData];
        expectedResult = service.addTypStatToCollectionIfMissing(typStatCollection, ...typStatArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const typStat: ITypStat = sampleWithRequiredData;
        const typStat2: ITypStat = sampleWithPartialData;
        expectedResult = service.addTypStatToCollectionIfMissing([], typStat, typStat2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(typStat);
        expect(expectedResult).toContain(typStat2);
      });

      it('should accept null and undefined values', () => {
        const typStat: ITypStat = sampleWithRequiredData;
        expectedResult = service.addTypStatToCollectionIfMissing([], null, typStat, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(typStat);
      });

      it('should return initial array if no TypStat is added', () => {
        const typStatCollection: ITypStat[] = [sampleWithRequiredData];
        expectedResult = service.addTypStatToCollectionIfMissing(typStatCollection, undefined, null);
        expect(expectedResult).toEqual(typStatCollection);
      });
    });

    describe('compareTypStat', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTypStat(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareTypStat(entity1, entity2);
        const compareResult2 = service.compareTypStat(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareTypStat(entity1, entity2);
        const compareResult2 = service.compareTypStat(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareTypStat(entity1, entity2);
        const compareResult2 = service.compareTypStat(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
