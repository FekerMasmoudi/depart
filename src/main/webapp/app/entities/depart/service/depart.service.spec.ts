import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IDepart } from '../depart.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../depart.test-samples';

import { DepartService, RestDepart } from './depart.service';

const requireRestSample: RestDepart = {
  ...sampleWithRequiredData,
  dedated: sampleWithRequiredData.dedated?.format(DATE_FORMAT),
  deheups: sampleWithRequiredData.deheups?.format(DATE_FORMAT),
  deheufs: sampleWithRequiredData.deheufs?.format(DATE_FORMAT),
  deheuaa: sampleWithRequiredData.deheuaa?.format(DATE_FORMAT),
  deheudr: sampleWithRequiredData.deheudr?.format(DATE_FORMAT),
  deheupd: sampleWithRequiredData.deheupd?.format(DATE_FORMAT),
  deampli: sampleWithRequiredData.deampli?.format(DATE_FORMAT),
};

describe('Depart Service', () => {
  let service: DepartService;
  let httpMock: HttpTestingController;
  let expectedResult: IDepart | IDepart[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DepartService);
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

    it('should create a Depart', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const depart = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(depart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Depart', () => {
      const depart = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(depart).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Depart', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Depart', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Depart', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDepartToCollectionIfMissing', () => {
      it('should add a Depart to an empty array', () => {
        const depart: IDepart = sampleWithRequiredData;
        expectedResult = service.addDepartToCollectionIfMissing([], depart);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(depart);
      });

      it('should not add a Depart to an array that contains it', () => {
        const depart: IDepart = sampleWithRequiredData;
        const departCollection: IDepart[] = [
          {
            ...depart,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDepartToCollectionIfMissing(departCollection, depart);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Depart to an array that doesn't contain it", () => {
        const depart: IDepart = sampleWithRequiredData;
        const departCollection: IDepart[] = [sampleWithPartialData];
        expectedResult = service.addDepartToCollectionIfMissing(departCollection, depart);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(depart);
      });

      it('should add only unique Depart to an array', () => {
        const departArray: IDepart[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const departCollection: IDepart[] = [sampleWithRequiredData];
        expectedResult = service.addDepartToCollectionIfMissing(departCollection, ...departArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const depart: IDepart = sampleWithRequiredData;
        const depart2: IDepart = sampleWithPartialData;
        expectedResult = service.addDepartToCollectionIfMissing([], depart, depart2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(depart);
        expect(expectedResult).toContain(depart2);
      });

      it('should accept null and undefined values', () => {
        const depart: IDepart = sampleWithRequiredData;
        expectedResult = service.addDepartToCollectionIfMissing([], null, depart, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(depart);
      });

      it('should return initial array if no Depart is added', () => {
        const departCollection: IDepart[] = [sampleWithRequiredData];
        expectedResult = service.addDepartToCollectionIfMissing(departCollection, undefined, null);
        expect(expectedResult).toEqual(departCollection);
      });
    });

    describe('compareDepart', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDepart(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDepart(entity1, entity2);
        const compareResult2 = service.compareDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDepart(entity1, entity2);
        const compareResult2 = service.compareDepart(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDepart(entity1, entity2);
        const compareResult2 = service.compareDepart(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
