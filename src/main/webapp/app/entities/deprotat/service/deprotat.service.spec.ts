import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDeprotat } from '../deprotat.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../deprotat.test-samples';

import { DeprotatService, RestDeprotat } from './deprotat.service';

const requireRestSample: RestDeprotat = {
  ...sampleWithRequiredData,
  dedated: sampleWithRequiredData.dedated?.format(DATE_FORMAT),
  hdeparte: sampleWithRequiredData.hdeparte?.toJSON(),
  hretoure: sampleWithRequiredData.hretoure?.toJSON(),
  harralle: sampleWithRequiredData.harralle?.toJSON(),
  harrrete: sampleWithRequiredData.harrrete?.toJSON(),
};

describe('Deprotat Service', () => {
  let service: DeprotatService;
  let httpMock: HttpTestingController;
  let expectedResult: IDeprotat | IDeprotat[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DeprotatService);
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

    it('should create a Deprotat', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const deprotat = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(deprotat).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Deprotat', () => {
      const deprotat = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(deprotat).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Deprotat', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Deprotat', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Deprotat', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDeprotatToCollectionIfMissing', () => {
      it('should add a Deprotat to an empty array', () => {
        const deprotat: IDeprotat = sampleWithRequiredData;
        expectedResult = service.addDeprotatToCollectionIfMissing([], deprotat);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(deprotat);
      });

      it('should not add a Deprotat to an array that contains it', () => {
        const deprotat: IDeprotat = sampleWithRequiredData;
        const deprotatCollection: IDeprotat[] = [
          {
            ...deprotat,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDeprotatToCollectionIfMissing(deprotatCollection, deprotat);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Deprotat to an array that doesn't contain it", () => {
        const deprotat: IDeprotat = sampleWithRequiredData;
        const deprotatCollection: IDeprotat[] = [sampleWithPartialData];
        expectedResult = service.addDeprotatToCollectionIfMissing(deprotatCollection, deprotat);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(deprotat);
      });

      it('should add only unique Deprotat to an array', () => {
        const deprotatArray: IDeprotat[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const deprotatCollection: IDeprotat[] = [sampleWithRequiredData];
        expectedResult = service.addDeprotatToCollectionIfMissing(deprotatCollection, ...deprotatArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const deprotat: IDeprotat = sampleWithRequiredData;
        const deprotat2: IDeprotat = sampleWithPartialData;
        expectedResult = service.addDeprotatToCollectionIfMissing([], deprotat, deprotat2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(deprotat);
        expect(expectedResult).toContain(deprotat2);
      });

      it('should accept null and undefined values', () => {
        const deprotat: IDeprotat = sampleWithRequiredData;
        expectedResult = service.addDeprotatToCollectionIfMissing([], null, deprotat, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(deprotat);
      });

      it('should return initial array if no Deprotat is added', () => {
        const deprotatCollection: IDeprotat[] = [sampleWithRequiredData];
        expectedResult = service.addDeprotatToCollectionIfMissing(deprotatCollection, undefined, null);
        expect(expectedResult).toEqual(deprotatCollection);
      });
    });

    describe('compareDeprotat', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDeprotat(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDeprotat(entity1, entity2);
        const compareResult2 = service.compareDeprotat(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDeprotat(entity1, entity2);
        const compareResult2 = service.compareDeprotat(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDeprotat(entity1, entity2);
        const compareResult2 = service.compareDeprotat(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
