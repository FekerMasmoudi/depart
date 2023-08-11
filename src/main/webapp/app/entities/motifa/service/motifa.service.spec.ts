import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMotifa } from '../motifa.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../motifa.test-samples';

import { MotifaService } from './motifa.service';

const requireRestSample: IMotifa = {
  ...sampleWithRequiredData,
};

describe('Motifa Service', () => {
  let service: MotifaService;
  let httpMock: HttpTestingController;
  let expectedResult: IMotifa | IMotifa[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MotifaService);
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

    it('should create a Motifa', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const motifa = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(motifa).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Motifa', () => {
      const motifa = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(motifa).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Motifa', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Motifa', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Motifa', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMotifaToCollectionIfMissing', () => {
      it('should add a Motifa to an empty array', () => {
        const motifa: IMotifa = sampleWithRequiredData;
        expectedResult = service.addMotifaToCollectionIfMissing([], motifa);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(motifa);
      });

      it('should not add a Motifa to an array that contains it', () => {
        const motifa: IMotifa = sampleWithRequiredData;
        const motifaCollection: IMotifa[] = [
          {
            ...motifa,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMotifaToCollectionIfMissing(motifaCollection, motifa);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Motifa to an array that doesn't contain it", () => {
        const motifa: IMotifa = sampleWithRequiredData;
        const motifaCollection: IMotifa[] = [sampleWithPartialData];
        expectedResult = service.addMotifaToCollectionIfMissing(motifaCollection, motifa);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(motifa);
      });

      it('should add only unique Motifa to an array', () => {
        const motifaArray: IMotifa[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const motifaCollection: IMotifa[] = [sampleWithRequiredData];
        expectedResult = service.addMotifaToCollectionIfMissing(motifaCollection, ...motifaArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const motifa: IMotifa = sampleWithRequiredData;
        const motifa2: IMotifa = sampleWithPartialData;
        expectedResult = service.addMotifaToCollectionIfMissing([], motifa, motifa2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(motifa);
        expect(expectedResult).toContain(motifa2);
      });

      it('should accept null and undefined values', () => {
        const motifa: IMotifa = sampleWithRequiredData;
        expectedResult = service.addMotifaToCollectionIfMissing([], null, motifa, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(motifa);
      });

      it('should return initial array if no Motifa is added', () => {
        const motifaCollection: IMotifa[] = [sampleWithRequiredData];
        expectedResult = service.addMotifaToCollectionIfMissing(motifaCollection, undefined, null);
        expect(expectedResult).toEqual(motifaCollection);
      });
    });

    describe('compareMotifa', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMotifa(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareMotifa(entity1, entity2);
        const compareResult2 = service.compareMotifa(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareMotifa(entity1, entity2);
        const compareResult2 = service.compareMotifa(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareMotifa(entity1, entity2);
        const compareResult2 = service.compareMotifa(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
