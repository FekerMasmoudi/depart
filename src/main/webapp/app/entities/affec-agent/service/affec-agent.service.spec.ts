import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IAffecAgent } from '../affec-agent.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../affec-agent.test-samples';

import { AffecAgentService } from './affec-agent.service';

const requireRestSample: IAffecAgent = {
  ...sampleWithRequiredData,
};

describe('AffecAgent Service', () => {
  let service: AffecAgentService;
  let httpMock: HttpTestingController;
  let expectedResult: IAffecAgent | IAffecAgent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(AffecAgentService);
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

    it('should create a AffecAgent', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const affecAgent = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(affecAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a AffecAgent', () => {
      const affecAgent = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(affecAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a AffecAgent', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of AffecAgent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a AffecAgent', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addAffecAgentToCollectionIfMissing', () => {
      it('should add a AffecAgent to an empty array', () => {
        const affecAgent: IAffecAgent = sampleWithRequiredData;
        expectedResult = service.addAffecAgentToCollectionIfMissing([], affecAgent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(affecAgent);
      });

      it('should not add a AffecAgent to an array that contains it', () => {
        const affecAgent: IAffecAgent = sampleWithRequiredData;
        const affecAgentCollection: IAffecAgent[] = [
          {
            ...affecAgent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addAffecAgentToCollectionIfMissing(affecAgentCollection, affecAgent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a AffecAgent to an array that doesn't contain it", () => {
        const affecAgent: IAffecAgent = sampleWithRequiredData;
        const affecAgentCollection: IAffecAgent[] = [sampleWithPartialData];
        expectedResult = service.addAffecAgentToCollectionIfMissing(affecAgentCollection, affecAgent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(affecAgent);
      });

      it('should add only unique AffecAgent to an array', () => {
        const affecAgentArray: IAffecAgent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const affecAgentCollection: IAffecAgent[] = [sampleWithRequiredData];
        expectedResult = service.addAffecAgentToCollectionIfMissing(affecAgentCollection, ...affecAgentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const affecAgent: IAffecAgent = sampleWithRequiredData;
        const affecAgent2: IAffecAgent = sampleWithPartialData;
        expectedResult = service.addAffecAgentToCollectionIfMissing([], affecAgent, affecAgent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(affecAgent);
        expect(expectedResult).toContain(affecAgent2);
      });

      it('should accept null and undefined values', () => {
        const affecAgent: IAffecAgent = sampleWithRequiredData;
        expectedResult = service.addAffecAgentToCollectionIfMissing([], null, affecAgent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(affecAgent);
      });

      it('should return initial array if no AffecAgent is added', () => {
        const affecAgentCollection: IAffecAgent[] = [sampleWithRequiredData];
        expectedResult = service.addAffecAgentToCollectionIfMissing(affecAgentCollection, undefined, null);
        expect(expectedResult).toEqual(affecAgentCollection);
      });
    });

    describe('compareAffecAgent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareAffecAgent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareAffecAgent(entity1, entity2);
        const compareResult2 = service.compareAffecAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareAffecAgent(entity1, entity2);
        const compareResult2 = service.compareAffecAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareAffecAgent(entity1, entity2);
        const compareResult2 = service.compareAffecAgent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
