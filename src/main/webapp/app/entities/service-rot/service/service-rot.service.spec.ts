import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IServiceRot } from '../service-rot.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../service-rot.test-samples';

import { ServiceRotService } from './service-rot.service';

const requireRestSample: IServiceRot = {
  ...sampleWithRequiredData,
};

describe('ServiceRot Service', () => {
  let service: ServiceRotService;
  let httpMock: HttpTestingController;
  let expectedResult: IServiceRot | IServiceRot[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ServiceRotService);
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

    it('should create a ServiceRot', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const serviceRot = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(serviceRot).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a ServiceRot', () => {
      const serviceRot = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(serviceRot).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a ServiceRot', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of ServiceRot', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a ServiceRot', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addServiceRotToCollectionIfMissing', () => {
      it('should add a ServiceRot to an empty array', () => {
        const serviceRot: IServiceRot = sampleWithRequiredData;
        expectedResult = service.addServiceRotToCollectionIfMissing([], serviceRot);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(serviceRot);
      });

      it('should not add a ServiceRot to an array that contains it', () => {
        const serviceRot: IServiceRot = sampleWithRequiredData;
        const serviceRotCollection: IServiceRot[] = [
          {
            ...serviceRot,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addServiceRotToCollectionIfMissing(serviceRotCollection, serviceRot);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a ServiceRot to an array that doesn't contain it", () => {
        const serviceRot: IServiceRot = sampleWithRequiredData;
        const serviceRotCollection: IServiceRot[] = [sampleWithPartialData];
        expectedResult = service.addServiceRotToCollectionIfMissing(serviceRotCollection, serviceRot);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(serviceRot);
      });

      it('should add only unique ServiceRot to an array', () => {
        const serviceRotArray: IServiceRot[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const serviceRotCollection: IServiceRot[] = [sampleWithRequiredData];
        expectedResult = service.addServiceRotToCollectionIfMissing(serviceRotCollection, ...serviceRotArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const serviceRot: IServiceRot = sampleWithRequiredData;
        const serviceRot2: IServiceRot = sampleWithPartialData;
        expectedResult = service.addServiceRotToCollectionIfMissing([], serviceRot, serviceRot2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(serviceRot);
        expect(expectedResult).toContain(serviceRot2);
      });

      it('should accept null and undefined values', () => {
        const serviceRot: IServiceRot = sampleWithRequiredData;
        expectedResult = service.addServiceRotToCollectionIfMissing([], null, serviceRot, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(serviceRot);
      });

      it('should return initial array if no ServiceRot is added', () => {
        const serviceRotCollection: IServiceRot[] = [sampleWithRequiredData];
        expectedResult = service.addServiceRotToCollectionIfMissing(serviceRotCollection, undefined, null);
        expect(expectedResult).toEqual(serviceRotCollection);
      });
    });

    describe('compareServiceRot', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareServiceRot(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareServiceRot(entity1, entity2);
        const compareResult2 = service.compareServiceRot(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareServiceRot(entity1, entity2);
        const compareResult2 = service.compareServiceRot(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareServiceRot(entity1, entity2);
        const compareResult2 = service.compareServiceRot(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
