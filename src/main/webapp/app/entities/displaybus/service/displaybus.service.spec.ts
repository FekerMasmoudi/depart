import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDisplaybus } from '../displaybus.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../displaybus.test-samples';

import { DisplaybusService } from './displaybus.service';

const requireRestSample: IDisplaybus = {
  ...sampleWithRequiredData,
};

describe('Displaybus Service', () => {
  let service: DisplaybusService;
  let httpMock: HttpTestingController;
  let expectedResult: IDisplaybus | IDisplaybus[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DisplaybusService);
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

    it('should create a Displaybus', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const displaybus = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(displaybus).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Displaybus', () => {
      const displaybus = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(displaybus).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Displaybus', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Displaybus', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Displaybus', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addDisplaybusToCollectionIfMissing', () => {
      it('should add a Displaybus to an empty array', () => {
        const displaybus: IDisplaybus = sampleWithRequiredData;
        expectedResult = service.addDisplaybusToCollectionIfMissing([], displaybus);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(displaybus);
      });

      it('should not add a Displaybus to an array that contains it', () => {
        const displaybus: IDisplaybus = sampleWithRequiredData;
        const displaybusCollection: IDisplaybus[] = [
          {
            ...displaybus,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addDisplaybusToCollectionIfMissing(displaybusCollection, displaybus);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Displaybus to an array that doesn't contain it", () => {
        const displaybus: IDisplaybus = sampleWithRequiredData;
        const displaybusCollection: IDisplaybus[] = [sampleWithPartialData];
        expectedResult = service.addDisplaybusToCollectionIfMissing(displaybusCollection, displaybus);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(displaybus);
      });

      it('should add only unique Displaybus to an array', () => {
        const displaybusArray: IDisplaybus[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const displaybusCollection: IDisplaybus[] = [sampleWithRequiredData];
        expectedResult = service.addDisplaybusToCollectionIfMissing(displaybusCollection, ...displaybusArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const displaybus: IDisplaybus = sampleWithRequiredData;
        const displaybus2: IDisplaybus = sampleWithPartialData;
        expectedResult = service.addDisplaybusToCollectionIfMissing([], displaybus, displaybus2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(displaybus);
        expect(expectedResult).toContain(displaybus2);
      });

      it('should accept null and undefined values', () => {
        const displaybus: IDisplaybus = sampleWithRequiredData;
        expectedResult = service.addDisplaybusToCollectionIfMissing([], null, displaybus, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(displaybus);
      });

      it('should return initial array if no Displaybus is added', () => {
        const displaybusCollection: IDisplaybus[] = [sampleWithRequiredData];
        expectedResult = service.addDisplaybusToCollectionIfMissing(displaybusCollection, undefined, null);
        expect(expectedResult).toEqual(displaybusCollection);
      });
    });

    describe('compareDisplaybus', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareDisplaybus(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareDisplaybus(entity1, entity2);
        const compareResult2 = service.compareDisplaybus(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareDisplaybus(entity1, entity2);
        const compareResult2 = service.compareDisplaybus(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareDisplaybus(entity1, entity2);
        const compareResult2 = service.compareDisplaybus(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
