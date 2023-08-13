import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDrabsen } from '../drabsen.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../drabsen.test-samples';

import { DrabsenService, RestDrabsen } from './drabsen.service';

const requireRestSample: RestDrabsen = {
  ...sampleWithRequiredData,
  databs: sampleWithRequiredData.databs?.format(DATE_FORMAT),
};

describe('Drabsen Service', () => {
  let service: DrabsenService;
  let httpMock: HttpTestingController;
  let expectedResult: IDrabsen | IDrabsen[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DrabsenService);
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

    it('should create a Drabsen', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const drabsen = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(drabsen).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Drabsen', () => {
      const drabsen = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(drabsen).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Drabsen', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Drabsen', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Drabsen', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDrabsenToCollectionIfMissing', () => {
      it('should add a Drabsen to an empty array', () => {
        const drabsen: IDrabsen = sampleWithRequiredData;
        expectedResult = service.addDrabsenToCollectionIfMissing([], drabsen);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(drabsen);
      });

      it('should not add a Drabsen to an array that contains it', () => {
        const drabsen: IDrabsen = sampleWithRequiredData;
        const drabsenCollection: IDrabsen[] = [
          {
            ...drabsen,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDrabsenToCollectionIfMissing(drabsenCollection, drabsen);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Drabsen to an array that doesn't contain it", () => {
        const drabsen: IDrabsen = sampleWithRequiredData;
        const drabsenCollection: IDrabsen[] = [sampleWithPartialData];
        expectedResult = service.addDrabsenToCollectionIfMissing(drabsenCollection, drabsen);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(drabsen);
      });

      it('should add only unique Drabsen to an array', () => {
        const drabsenArray: IDrabsen[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const drabsenCollection: IDrabsen[] = [sampleWithRequiredData];
        expectedResult = service.addDrabsenToCollectionIfMissing(drabsenCollection, ...drabsenArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const drabsen: IDrabsen = sampleWithRequiredData;
        const drabsen2: IDrabsen = sampleWithPartialData;
        expectedResult = service.addDrabsenToCollectionIfMissing([], drabsen, drabsen2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(drabsen);
        expect(expectedResult).toContain(drabsen2);
      });

      it('should accept null and undefined values', () => {
        const drabsen: IDrabsen = sampleWithRequiredData;
        expectedResult = service.addDrabsenToCollectionIfMissing([], null, drabsen, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(drabsen);
      });

      it('should return initial array if no Drabsen is added', () => {
        const drabsenCollection: IDrabsen[] = [sampleWithRequiredData];
        expectedResult = service.addDrabsenToCollectionIfMissing(drabsenCollection, undefined, null);
        expect(expectedResult).toEqual(drabsenCollection);
      });
    });

    describe('compareDrabsen', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDrabsen(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDrabsen(entity1, entity2);
        const compareResult2 = service.compareDrabsen(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDrabsen(entity1, entity2);
        const compareResult2 = service.compareDrabsen(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDrabsen(entity1, entity2);
        const compareResult2 = service.compareDrabsen(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
