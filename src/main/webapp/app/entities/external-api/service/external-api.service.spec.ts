import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IExternalApi } from '../external-api.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../external-api.test-samples';

import { ExternalApiService, RestExternalApi } from './external-api.service';

const requireRestSample: RestExternalApi = {
  ...sampleWithRequiredData,
  datecreatedt: sampleWithRequiredData.datecreatedt?.format(DATE_FORMAT),
  lastupdatedm: sampleWithRequiredData.lastupdatedm?.format(DATE_FORMAT),
  createdatet: sampleWithRequiredData.createdatet?.format(DATE_FORMAT),
  lastupdatete: sampleWithRequiredData.lastupdatete?.format(DATE_FORMAT),
};

describe('ExternalApi Service', () => {
  let service: ExternalApiService;
  let httpMock: HttpTestingController;
  let expectedResult: IExternalApi | IExternalApi[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ExternalApiService);
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

    it('should create a ExternalApi', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const externalApi = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(externalApi).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ExternalApi', () => {
      const externalApi = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(externalApi).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ExternalApi', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ExternalApi', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ExternalApi', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addExternalApiToCollectionIfMissing', () => {
      it('should add a ExternalApi to an empty array', () => {
        const externalApi: IExternalApi = sampleWithRequiredData;
        expectedResult = service.addExternalApiToCollectionIfMissing([], externalApi);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(externalApi);
      });

      it('should not add a ExternalApi to an array that contains it', () => {
        const externalApi: IExternalApi = sampleWithRequiredData;
        const externalApiCollection: IExternalApi[] = [
          {
            ...externalApi,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addExternalApiToCollectionIfMissing(externalApiCollection, externalApi);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ExternalApi to an array that doesn't contain it", () => {
        const externalApi: IExternalApi = sampleWithRequiredData;
        const externalApiCollection: IExternalApi[] = [sampleWithPartialData];
        expectedResult = service.addExternalApiToCollectionIfMissing(externalApiCollection, externalApi);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(externalApi);
      });

      it('should add only unique ExternalApi to an array', () => {
        const externalApiArray: IExternalApi[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const externalApiCollection: IExternalApi[] = [sampleWithRequiredData];
        expectedResult = service.addExternalApiToCollectionIfMissing(externalApiCollection, ...externalApiArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const externalApi: IExternalApi = sampleWithRequiredData;
        const externalApi2: IExternalApi = sampleWithPartialData;
        expectedResult = service.addExternalApiToCollectionIfMissing([], externalApi, externalApi2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(externalApi);
        expect(expectedResult).toContain(externalApi2);
      });

      it('should accept null and undefined values', () => {
        const externalApi: IExternalApi = sampleWithRequiredData;
        expectedResult = service.addExternalApiToCollectionIfMissing([], null, externalApi, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(externalApi);
      });

      it('should return initial array if no ExternalApi is added', () => {
        const externalApiCollection: IExternalApi[] = [sampleWithRequiredData];
        expectedResult = service.addExternalApiToCollectionIfMissing(externalApiCollection, undefined, null);
        expect(expectedResult).toEqual(externalApiCollection);
      });
    });

    describe('compareExternalApi', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareExternalApi(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareExternalApi(entity1, entity2);
        const compareResult2 = service.compareExternalApi(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareExternalApi(entity1, entity2);
        const compareResult2 = service.compareExternalApi(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareExternalApi(entity1, entity2);
        const compareResult2 = service.compareExternalApi(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
