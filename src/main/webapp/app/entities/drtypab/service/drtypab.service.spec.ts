import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDrtypab } from '../drtypab.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../drtypab.test-samples';

import { DrtypabService } from './drtypab.service';

const requireRestSample: IDrtypab = {
  ...sampleWithRequiredData,
};

describe('Drtypab Service', () => {
  let service: DrtypabService;
  let httpMock: HttpTestingController;
  let expectedResult: IDrtypab | IDrtypab[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DrtypabService);
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

    it('should create a Drtypab', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const drtypab = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(drtypab).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Drtypab', () => {
      const drtypab = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(drtypab).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Drtypab', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Drtypab', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Drtypab', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDrtypabToCollectionIfMissing', () => {
      it('should add a Drtypab to an empty array', () => {
        const drtypab: IDrtypab = sampleWithRequiredData;
        expectedResult = service.addDrtypabToCollectionIfMissing([], drtypab);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(drtypab);
      });

      it('should not add a Drtypab to an array that contains it', () => {
        const drtypab: IDrtypab = sampleWithRequiredData;
        const drtypabCollection: IDrtypab[] = [
          {
            ...drtypab,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDrtypabToCollectionIfMissing(drtypabCollection, drtypab);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Drtypab to an array that doesn't contain it", () => {
        const drtypab: IDrtypab = sampleWithRequiredData;
        const drtypabCollection: IDrtypab[] = [sampleWithPartialData];
        expectedResult = service.addDrtypabToCollectionIfMissing(drtypabCollection, drtypab);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(drtypab);
      });

      it('should add only unique Drtypab to an array', () => {
        const drtypabArray: IDrtypab[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const drtypabCollection: IDrtypab[] = [sampleWithRequiredData];
        expectedResult = service.addDrtypabToCollectionIfMissing(drtypabCollection, ...drtypabArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const drtypab: IDrtypab = sampleWithRequiredData;
        const drtypab2: IDrtypab = sampleWithPartialData;
        expectedResult = service.addDrtypabToCollectionIfMissing([], drtypab, drtypab2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(drtypab);
        expect(expectedResult).toContain(drtypab2);
      });

      it('should accept null and undefined values', () => {
        const drtypab: IDrtypab = sampleWithRequiredData;
        expectedResult = service.addDrtypabToCollectionIfMissing([], null, drtypab, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(drtypab);
      });

      it('should return initial array if no Drtypab is added', () => {
        const drtypabCollection: IDrtypab[] = [sampleWithRequiredData];
        expectedResult = service.addDrtypabToCollectionIfMissing(drtypabCollection, undefined, null);
        expect(expectedResult).toEqual(drtypabCollection);
      });
    });

    describe('compareDrtypab', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDrtypab(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDrtypab(entity1, entity2);
        const compareResult2 = service.compareDrtypab(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDrtypab(entity1, entity2);
        const compareResult2 = service.compareDrtypab(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDrtypab(entity1, entity2);
        const compareResult2 = service.compareDrtypab(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
