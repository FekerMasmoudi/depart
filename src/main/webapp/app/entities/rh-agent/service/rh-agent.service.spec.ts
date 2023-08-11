import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRhAgent } from '../rh-agent.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../rh-agent.test-samples';

import { RhAgentService, RestRhAgent } from './rh-agent.service';

const requireRestSample: RestRhAgent = {
  ...sampleWithRequiredData,
  dateffrh: sampleWithRequiredData.dateffrh?.format(DATE_FORMAT),
};

describe('RhAgent Service', () => {
  let service: RhAgentService;
  let httpMock: HttpTestingController;
  let expectedResult: IRhAgent | IRhAgent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RhAgentService);
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

    it('should create a RhAgent', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const rhAgent = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(rhAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RhAgent', () => {
      const rhAgent = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(rhAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RhAgent', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RhAgent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a RhAgent', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addRhAgentToCollectionIfMissing', () => {
      it('should add a RhAgent to an empty array', () => {
        const rhAgent: IRhAgent = sampleWithRequiredData;
        expectedResult = service.addRhAgentToCollectionIfMissing([], rhAgent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rhAgent);
      });

      it('should not add a RhAgent to an array that contains it', () => {
        const rhAgent: IRhAgent = sampleWithRequiredData;
        const rhAgentCollection: IRhAgent[] = [
          {
            ...rhAgent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addRhAgentToCollectionIfMissing(rhAgentCollection, rhAgent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RhAgent to an array that doesn't contain it", () => {
        const rhAgent: IRhAgent = sampleWithRequiredData;
        const rhAgentCollection: IRhAgent[] = [sampleWithPartialData];
        expectedResult = service.addRhAgentToCollectionIfMissing(rhAgentCollection, rhAgent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rhAgent);
      });

      it('should add only unique RhAgent to an array', () => {
        const rhAgentArray: IRhAgent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const rhAgentCollection: IRhAgent[] = [sampleWithRequiredData];
        expectedResult = service.addRhAgentToCollectionIfMissing(rhAgentCollection, ...rhAgentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const rhAgent: IRhAgent = sampleWithRequiredData;
        const rhAgent2: IRhAgent = sampleWithPartialData;
        expectedResult = service.addRhAgentToCollectionIfMissing([], rhAgent, rhAgent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(rhAgent);
        expect(expectedResult).toContain(rhAgent2);
      });

      it('should accept null and undefined values', () => {
        const rhAgent: IRhAgent = sampleWithRequiredData;
        expectedResult = service.addRhAgentToCollectionIfMissing([], null, rhAgent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(rhAgent);
      });

      it('should return initial array if no RhAgent is added', () => {
        const rhAgentCollection: IRhAgent[] = [sampleWithRequiredData];
        expectedResult = service.addRhAgentToCollectionIfMissing(rhAgentCollection, undefined, null);
        expect(expectedResult).toEqual(rhAgentCollection);
      });
    });

    describe('compareRhAgent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareRhAgent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareRhAgent(entity1, entity2);
        const compareResult2 = service.compareRhAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareRhAgent(entity1, entity2);
        const compareResult2 = service.compareRhAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareRhAgent(entity1, entity2);
        const compareResult2 = service.compareRhAgent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
