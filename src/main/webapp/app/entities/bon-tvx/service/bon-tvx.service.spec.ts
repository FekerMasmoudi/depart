import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IBonTvx } from '../bon-tvx.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../bon-tvx.test-samples';

import { BonTvxService, RestBonTvx } from './bon-tvx.service';

const requireRestSample: RestBonTvx = {
  ...sampleWithRequiredData,
  datbt: sampleWithRequiredData.datbt?.format(DATE_FORMAT),
  datdt: sampleWithRequiredData.datdt?.format(DATE_FORMAT),
  datft: sampleWithRequiredData.datft?.format(DATE_FORMAT),
  heurdb: sampleWithRequiredData.heurdb?.format(DATE_FORMAT),
  heurfi: sampleWithRequiredData.heurfi?.format(DATE_FORMAT),
  datsrt: sampleWithRequiredData.datsrt?.format(DATE_FORMAT),
  heursr: sampleWithRequiredData.heursr?.format(DATE_FORMAT),
  datsorprev: sampleWithRequiredData.datsorprev?.format(DATE_FORMAT),
  datmnqdu: sampleWithRequiredData.datmnqdu?.format(DATE_FORMAT),
  datmnqau: sampleWithRequiredData.datmnqau?.format(DATE_FORMAT),
  datentant: sampleWithRequiredData.datentant?.format(DATE_FORMAT),
  datvld: sampleWithRequiredData.datvld?.format(DATE_FORMAT),
  datsais: sampleWithRequiredData.datsais?.format(DATE_FORMAT),
};

describe('BonTvx Service', () => {
  let service: BonTvxService;
  let httpMock: HttpTestingController;
  let expectedResult: IBonTvx | IBonTvx[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(BonTvxService);
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

    it('should create a BonTvx', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const bonTvx = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(bonTvx).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a BonTvx', () => {
      const bonTvx = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(bonTvx).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a BonTvx', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of BonTvx', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a BonTvx', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addBonTvxToCollectionIfMissing', () => {
      it('should add a BonTvx to an empty array', () => {
        const bonTvx: IBonTvx = sampleWithRequiredData;
        expectedResult = service.addBonTvxToCollectionIfMissing([], bonTvx);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bonTvx);
      });

      it('should not add a BonTvx to an array that contains it', () => {
        const bonTvx: IBonTvx = sampleWithRequiredData;
        const bonTvxCollection: IBonTvx[] = [
          {
            ...bonTvx,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addBonTvxToCollectionIfMissing(bonTvxCollection, bonTvx);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a BonTvx to an array that doesn't contain it", () => {
        const bonTvx: IBonTvx = sampleWithRequiredData;
        const bonTvxCollection: IBonTvx[] = [sampleWithPartialData];
        expectedResult = service.addBonTvxToCollectionIfMissing(bonTvxCollection, bonTvx);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bonTvx);
      });

      it('should add only unique BonTvx to an array', () => {
        const bonTvxArray: IBonTvx[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const bonTvxCollection: IBonTvx[] = [sampleWithRequiredData];
        expectedResult = service.addBonTvxToCollectionIfMissing(bonTvxCollection, ...bonTvxArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const bonTvx: IBonTvx = sampleWithRequiredData;
        const bonTvx2: IBonTvx = sampleWithPartialData;
        expectedResult = service.addBonTvxToCollectionIfMissing([], bonTvx, bonTvx2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(bonTvx);
        expect(expectedResult).toContain(bonTvx2);
      });

      it('should accept null and undefined values', () => {
        const bonTvx: IBonTvx = sampleWithRequiredData;
        expectedResult = service.addBonTvxToCollectionIfMissing([], null, bonTvx, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(bonTvx);
      });

      it('should return initial array if no BonTvx is added', () => {
        const bonTvxCollection: IBonTvx[] = [sampleWithRequiredData];
        expectedResult = service.addBonTvxToCollectionIfMissing(bonTvxCollection, undefined, null);
        expect(expectedResult).toEqual(bonTvxCollection);
      });
    });

    describe('compareBonTvx', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareBonTvx(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareBonTvx(entity1, entity2);
        const compareResult2 = service.compareBonTvx(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareBonTvx(entity1, entity2);
        const compareResult2 = service.compareBonTvx(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareBonTvx(entity1, entity2);
        const compareResult2 = service.compareBonTvx(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
