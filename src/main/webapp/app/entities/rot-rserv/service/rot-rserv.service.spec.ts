import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRotRserv } from '../rot-rserv.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../rot-rserv.test-samples';

import { RotRservService, RestRotRserv } from './rot-rserv.service';

const requireRestSample: RestRotRserv = {
  ...sampleWithRequiredData,
  dedated: sampleWithRequiredData.dedated?.format(DATE_FORMAT),
  heurdeb: sampleWithRequiredData.heurdeb?.toJSON(),
  heurfin: sampleWithRequiredData.heurfin?.toJSON(),
  lieedeb: sampleWithRequiredData.lieedeb?.toJSON(),
  lieefin: sampleWithRequiredData.lieefin?.toJSON(),
};

describe('RotRserv Service', () => {
  let service: RotRservService;
  let httpMock: HttpTestingController;
  let expectedResult: IRotRserv | IRotRserv[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RotRservService);
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

    it('should create a RotRserv', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const rotRserv = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(rotRserv).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RotRserv', () => {
      const rotRserv = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(rotRserv).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RotRserv', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RotRserv', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RotRserv', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRotRservToCollectionIfMissing', () => {
      it('should add a RotRserv to an empty array', () => {
        const rotRserv: IRotRserv = sampleWithRequiredData;
        expectedResult = service.addRotRservToCollectionIfMissing([], rotRserv);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rotRserv);
      });

      it('should not add a RotRserv to an array that contains it', () => {
        const rotRserv: IRotRserv = sampleWithRequiredData;
        const rotRservCollection: IRotRserv[] = [
          {
            ...rotRserv,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRotRservToCollectionIfMissing(rotRservCollection, rotRserv);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RotRserv to an array that doesn't contain it", () => {
        const rotRserv: IRotRserv = sampleWithRequiredData;
        const rotRservCollection: IRotRserv[] = [sampleWithPartialData];
        expectedResult = service.addRotRservToCollectionIfMissing(rotRservCollection, rotRserv);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rotRserv);
      });

      it('should add only unique RotRserv to an array', () => {
        const rotRservArray: IRotRserv[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const rotRservCollection: IRotRserv[] = [sampleWithRequiredData];
        expectedResult = service.addRotRservToCollectionIfMissing(rotRservCollection, ...rotRservArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rotRserv: IRotRserv = sampleWithRequiredData;
        const rotRserv2: IRotRserv = sampleWithPartialData;
        expectedResult = service.addRotRservToCollectionIfMissing([], rotRserv, rotRserv2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rotRserv);
        expect(expectedResult).toContain(rotRserv2);
      });

      it('should accept null and undefined values', () => {
        const rotRserv: IRotRserv = sampleWithRequiredData;
        expectedResult = service.addRotRservToCollectionIfMissing([], null, rotRserv, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rotRserv);
      });

      it('should return initial array if no RotRserv is added', () => {
        const rotRservCollection: IRotRserv[] = [sampleWithRequiredData];
        expectedResult = service.addRotRservToCollectionIfMissing(rotRservCollection, undefined, null);
        expect(expectedResult).toEqual(rotRservCollection);
      });
    });

    describe('compareRotRserv', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRotRserv(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareRotRserv(entity1, entity2);
        const compareResult2 = service.compareRotRserv(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareRotRserv(entity1, entity2);
        const compareResult2 = service.compareRotRserv(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareRotRserv(entity1, entity2);
        const compareResult2 = service.compareRotRserv(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
