import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IMotifchserv } from '../motifchserv.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../motifchserv.test-samples';

import { MotifchservService } from './motifchserv.service';

const requireRestSample: IMotifchserv = {
  ...sampleWithRequiredData,
};

describe('Motifchserv Service', () => {
  let service: MotifchservService;
  let httpMock: HttpTestingController;
  let expectedResult: IMotifchserv | IMotifchserv[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(MotifchservService);
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

    it('should create a Motifchserv', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const motifchserv = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(motifchserv).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Motifchserv', () => {
      const motifchserv = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(motifchserv).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Motifchserv', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Motifchserv', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Motifchserv', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addMotifchservToCollectionIfMissing', () => {
      it('should add a Motifchserv to an empty array', () => {
        const motifchserv: IMotifchserv = sampleWithRequiredData;
        expectedResult = service.addMotifchservToCollectionIfMissing([], motifchserv);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(motifchserv);
      });

      it('should not add a Motifchserv to an array that contains it', () => {
        const motifchserv: IMotifchserv = sampleWithRequiredData;
        const motifchservCollection: IMotifchserv[] = [
          {
            ...motifchserv,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addMotifchservToCollectionIfMissing(motifchservCollection, motifchserv);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Motifchserv to an array that doesn't contain it", () => {
        const motifchserv: IMotifchserv = sampleWithRequiredData;
        const motifchservCollection: IMotifchserv[] = [sampleWithPartialData];
        expectedResult = service.addMotifchservToCollectionIfMissing(motifchservCollection, motifchserv);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(motifchserv);
      });

      it('should add only unique Motifchserv to an array', () => {
        const motifchservArray: IMotifchserv[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const motifchservCollection: IMotifchserv[] = [sampleWithRequiredData];
        expectedResult = service.addMotifchservToCollectionIfMissing(motifchservCollection, ...motifchservArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const motifchserv: IMotifchserv = sampleWithRequiredData;
        const motifchserv2: IMotifchserv = sampleWithPartialData;
        expectedResult = service.addMotifchservToCollectionIfMissing([], motifchserv, motifchserv2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(motifchserv);
        expect(expectedResult).toContain(motifchserv2);
      });

      it('should accept null and undefined values', () => {
        const motifchserv: IMotifchserv = sampleWithRequiredData;
        expectedResult = service.addMotifchservToCollectionIfMissing([], null, motifchserv, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(motifchserv);
      });

      it('should return initial array if no Motifchserv is added', () => {
        const motifchservCollection: IMotifchserv[] = [sampleWithRequiredData];
        expectedResult = service.addMotifchservToCollectionIfMissing(motifchservCollection, undefined, null);
        expect(expectedResult).toEqual(motifchservCollection);
      });
    });

    describe('compareMotifchserv', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareMotifchserv(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareMotifchserv(entity1, entity2);
        const compareResult2 = service.compareMotifchserv(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareMotifchserv(entity1, entity2);
        const compareResult2 = service.compareMotifchserv(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareMotifchserv(entity1, entity2);
        const compareResult2 = service.compareMotifchserv(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
