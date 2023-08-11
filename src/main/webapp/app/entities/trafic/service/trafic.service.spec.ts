import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ITrafic } from '../trafic.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../trafic.test-samples';

import { TraficService, RestTrafic } from './trafic.service';

const requireRestSample: RestTrafic = {
  ...sampleWithRequiredData,
  dedated: sampleWithRequiredData.dedated?.format(DATE_FORMAT),
};

describe('Trafic Service', () => {
  let service: TraficService;
  let httpMock: HttpTestingController;
  let expectedResult: ITrafic | ITrafic[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(TraficService);
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

    it('should create a Trafic', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const trafic = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(trafic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Trafic', () => {
      const trafic = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(trafic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Trafic', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Trafic', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Trafic', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addTraficToCollectionIfMissing', () => {
      it('should add a Trafic to an empty array', () => {
        const trafic: ITrafic = sampleWithRequiredData;
        expectedResult = service.addTraficToCollectionIfMissing([], trafic);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trafic);
      });

      it('should not add a Trafic to an array that contains it', () => {
        const trafic: ITrafic = sampleWithRequiredData;
        const traficCollection: ITrafic[] = [
          {
            ...trafic,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addTraficToCollectionIfMissing(traficCollection, trafic);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Trafic to an array that doesn't contain it", () => {
        const trafic: ITrafic = sampleWithRequiredData;
        const traficCollection: ITrafic[] = [sampleWithPartialData];
        expectedResult = service.addTraficToCollectionIfMissing(traficCollection, trafic);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trafic);
      });

      it('should add only unique Trafic to an array', () => {
        const traficArray: ITrafic[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const traficCollection: ITrafic[] = [sampleWithRequiredData];
        expectedResult = service.addTraficToCollectionIfMissing(traficCollection, ...traficArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const trafic: ITrafic = sampleWithRequiredData;
        const trafic2: ITrafic = sampleWithPartialData;
        expectedResult = service.addTraficToCollectionIfMissing([], trafic, trafic2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(trafic);
        expect(expectedResult).toContain(trafic2);
      });

      it('should accept null and undefined values', () => {
        const trafic: ITrafic = sampleWithRequiredData;
        expectedResult = service.addTraficToCollectionIfMissing([], null, trafic, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(trafic);
      });

      it('should return initial array if no Trafic is added', () => {
        const traficCollection: ITrafic[] = [sampleWithRequiredData];
        expectedResult = service.addTraficToCollectionIfMissing(traficCollection, undefined, null);
        expect(expectedResult).toEqual(traficCollection);
      });
    });

    describe('compareTrafic', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareTrafic(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareTrafic(entity1, entity2);
        const compareResult2 = service.compareTrafic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareTrafic(entity1, entity2);
        const compareResult2 = service.compareTrafic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareTrafic(entity1, entity2);
        const compareResult2 = service.compareTrafic(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
