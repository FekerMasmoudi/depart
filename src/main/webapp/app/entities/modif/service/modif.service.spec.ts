import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IModif } from '../modif.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../modif.test-samples';

import { ModifService, RestModif } from './modif.service';

const requireRestSample: RestModif = {
  ...sampleWithRequiredData,
  dedated: sampleWithRequiredData.dedated?.format(DATE_FORMAT),
  heur: sampleWithRequiredData.heur?.format(DATE_FORMAT),
};

describe('Modif Service', () => {
  let service: ModifService;
  let httpMock: HttpTestingController;
  let expectedResult: IModif | IModif[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ModifService);
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

    it('should create a Modif', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const modif = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(modif).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Modif', () => {
      const modif = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(modif).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Modif', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Modif', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Modif', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addModifToCollectionIfMissing', () => {
      it('should add a Modif to an empty array', () => {
        const modif: IModif = sampleWithRequiredData;
        expectedResult = service.addModifToCollectionIfMissing([], modif);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(modif);
      });

      it('should not add a Modif to an array that contains it', () => {
        const modif: IModif = sampleWithRequiredData;
        const modifCollection: IModif[] = [
          {
            ...modif,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addModifToCollectionIfMissing(modifCollection, modif);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Modif to an array that doesn't contain it", () => {
        const modif: IModif = sampleWithRequiredData;
        const modifCollection: IModif[] = [sampleWithPartialData];
        expectedResult = service.addModifToCollectionIfMissing(modifCollection, modif);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(modif);
      });

      it('should add only unique Modif to an array', () => {
        const modifArray: IModif[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const modifCollection: IModif[] = [sampleWithRequiredData];
        expectedResult = service.addModifToCollectionIfMissing(modifCollection, ...modifArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const modif: IModif = sampleWithRequiredData;
        const modif2: IModif = sampleWithPartialData;
        expectedResult = service.addModifToCollectionIfMissing([], modif, modif2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(modif);
        expect(expectedResult).toContain(modif2);
      });

      it('should accept null and undefined values', () => {
        const modif: IModif = sampleWithRequiredData;
        expectedResult = service.addModifToCollectionIfMissing([], null, modif, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(modif);
      });

      it('should return initial array if no Modif is added', () => {
        const modifCollection: IModif[] = [sampleWithRequiredData];
        expectedResult = service.addModifToCollectionIfMissing(modifCollection, undefined, null);
        expect(expectedResult).toEqual(modifCollection);
      });
    });

    describe('compareModif', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareModif(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareModif(entity1, entity2);
        const compareResult2 = service.compareModif(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareModif(entity1, entity2);
        const compareResult2 = service.compareModif(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareModif(entity1, entity2);
        const compareResult2 = service.compareModif(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
