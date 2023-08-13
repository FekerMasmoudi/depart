import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { ICentVehic } from '../cent-vehic.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../cent-vehic.test-samples';

import { CentVehicService, RestCentVehic } from './cent-vehic.service';

const requireRestSample: RestCentVehic = {
  ...sampleWithRequiredData,
  dateff: sampleWithRequiredData.dateff?.format(DATE_FORMAT),
};

describe('CentVehic Service', () => {
  let service: CentVehicService;
  let httpMock: HttpTestingController;
  let expectedResult: ICentVehic | ICentVehic[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(CentVehicService);
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

    it('should create a CentVehic', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const centVehic = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(centVehic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a CentVehic', () => {
      const centVehic = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(centVehic).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a CentVehic', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of CentVehic', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a CentVehic', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addCentVehicToCollectionIfMissing', () => {
      it('should add a CentVehic to an empty array', () => {
        const centVehic: ICentVehic = sampleWithRequiredData;
        expectedResult = service.addCentVehicToCollectionIfMissing([], centVehic);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(centVehic);
      });

      it('should not add a CentVehic to an array that contains it', () => {
        const centVehic: ICentVehic = sampleWithRequiredData;
        const centVehicCollection: ICentVehic[] = [
          {
            ...centVehic,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addCentVehicToCollectionIfMissing(centVehicCollection, centVehic);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a CentVehic to an array that doesn't contain it", () => {
        const centVehic: ICentVehic = sampleWithRequiredData;
        const centVehicCollection: ICentVehic[] = [sampleWithPartialData];
        expectedResult = service.addCentVehicToCollectionIfMissing(centVehicCollection, centVehic);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(centVehic);
      });

      it('should add only unique CentVehic to an array', () => {
        const centVehicArray: ICentVehic[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const centVehicCollection: ICentVehic[] = [sampleWithRequiredData];
        expectedResult = service.addCentVehicToCollectionIfMissing(centVehicCollection, ...centVehicArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const centVehic: ICentVehic = sampleWithRequiredData;
        const centVehic2: ICentVehic = sampleWithPartialData;
        expectedResult = service.addCentVehicToCollectionIfMissing([], centVehic, centVehic2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(centVehic);
        expect(expectedResult).toContain(centVehic2);
      });

      it('should accept null and undefined values', () => {
        const centVehic: ICentVehic = sampleWithRequiredData;
        expectedResult = service.addCentVehicToCollectionIfMissing([], null, centVehic, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(centVehic);
      });

      it('should return initial array if no CentVehic is added', () => {
        const centVehicCollection: ICentVehic[] = [sampleWithRequiredData];
        expectedResult = service.addCentVehicToCollectionIfMissing(centVehicCollection, undefined, null);
        expect(expectedResult).toEqual(centVehicCollection);
      });
    });

    describe('compareCentVehic', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareCentVehic(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareCentVehic(entity1, entity2);
        const compareResult2 = service.compareCentVehic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareCentVehic(entity1, entity2);
        const compareResult2 = service.compareCentVehic(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareCentVehic(entity1, entity2);
        const compareResult2 = service.compareCentVehic(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
