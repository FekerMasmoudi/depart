import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAffectagent } from '../affectagent.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../affectagent.test-samples';

import { AffectagentService } from './affectagent.service';

const requireRestSample: IAffectagent = {
  ...sampleWithRequiredData,
};

describe('Affectagent Service', () => {
  let service: AffectagentService;
  let httpMock: HttpTestingController;
  let expectedResult: IAffectagent | IAffectagent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AffectagentService);
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

    it('should create a Affectagent', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const affectagent = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(affectagent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Affectagent', () => {
      const affectagent = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(affectagent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Affectagent', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Affectagent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Affectagent', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAffectagentToCollectionIfMissing', () => {
      it('should add a Affectagent to an empty array', () => {
        const affectagent: IAffectagent = sampleWithRequiredData;
        expectedResult = service.addAffectagentToCollectionIfMissing([], affectagent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(affectagent);
      });

      it('should not add a Affectagent to an array that contains it', () => {
        const affectagent: IAffectagent = sampleWithRequiredData;
        const affectagentCollection: IAffectagent[] = [
          {
            ...affectagent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAffectagentToCollectionIfMissing(affectagentCollection, affectagent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Affectagent to an array that doesn't contain it", () => {
        const affectagent: IAffectagent = sampleWithRequiredData;
        const affectagentCollection: IAffectagent[] = [sampleWithPartialData];
        expectedResult = service.addAffectagentToCollectionIfMissing(affectagentCollection, affectagent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(affectagent);
      });

      it('should add only unique Affectagent to an array', () => {
        const affectagentArray: IAffectagent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const affectagentCollection: IAffectagent[] = [sampleWithRequiredData];
        expectedResult = service.addAffectagentToCollectionIfMissing(affectagentCollection, ...affectagentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const affectagent: IAffectagent = sampleWithRequiredData;
        const affectagent2: IAffectagent = sampleWithPartialData;
        expectedResult = service.addAffectagentToCollectionIfMissing([], affectagent, affectagent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(affectagent);
        expect(expectedResult).toContain(affectagent2);
      });

      it('should accept null and undefined values', () => {
        const affectagent: IAffectagent = sampleWithRequiredData;
        expectedResult = service.addAffectagentToCollectionIfMissing([], null, affectagent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(affectagent);
      });

      it('should return initial array if no Affectagent is added', () => {
        const affectagentCollection: IAffectagent[] = [sampleWithRequiredData];
        expectedResult = service.addAffectagentToCollectionIfMissing(affectagentCollection, undefined, null);
        expect(expectedResult).toEqual(affectagentCollection);
      });
    });

    describe('compareAffectagent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAffectagent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareAffectagent(entity1, entity2);
        const compareResult2 = service.compareAffectagent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareAffectagent(entity1, entity2);
        const compareResult2 = service.compareAffectagent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareAffectagent(entity1, entity2);
        const compareResult2 = service.compareAffectagent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
