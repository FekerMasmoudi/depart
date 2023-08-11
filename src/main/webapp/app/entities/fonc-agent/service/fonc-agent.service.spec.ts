import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFoncAgent } from '../fonc-agent.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../fonc-agent.test-samples';

import { FoncAgentService, RestFoncAgent } from './fonc-agent.service';

const requireRestSample: RestFoncAgent = {
  ...sampleWithRequiredData,
  dateff: sampleWithRequiredData.dateff?.format(DATE_FORMAT),
};

describe('FoncAgent Service', () => {
  let service: FoncAgentService;
  let httpMock: HttpTestingController;
  let expectedResult: IFoncAgent | IFoncAgent[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(FoncAgentService);
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

    it('should create a FoncAgent', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const foncAgent = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(foncAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FoncAgent', () => {
      const foncAgent = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(foncAgent).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FoncAgent', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FoncAgent', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FoncAgent', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFoncAgentToCollectionIfMissing', () => {
      it('should add a FoncAgent to an empty array', () => {
        const foncAgent: IFoncAgent = sampleWithRequiredData;
        expectedResult = service.addFoncAgentToCollectionIfMissing([], foncAgent);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foncAgent);
      });

      it('should not add a FoncAgent to an array that contains it', () => {
        const foncAgent: IFoncAgent = sampleWithRequiredData;
        const foncAgentCollection: IFoncAgent[] = [
          {
            ...foncAgent,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFoncAgentToCollectionIfMissing(foncAgentCollection, foncAgent);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FoncAgent to an array that doesn't contain it", () => {
        const foncAgent: IFoncAgent = sampleWithRequiredData;
        const foncAgentCollection: IFoncAgent[] = [sampleWithPartialData];
        expectedResult = service.addFoncAgentToCollectionIfMissing(foncAgentCollection, foncAgent);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foncAgent);
      });

      it('should add only unique FoncAgent to an array', () => {
        const foncAgentArray: IFoncAgent[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const foncAgentCollection: IFoncAgent[] = [sampleWithRequiredData];
        expectedResult = service.addFoncAgentToCollectionIfMissing(foncAgentCollection, ...foncAgentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const foncAgent: IFoncAgent = sampleWithRequiredData;
        const foncAgent2: IFoncAgent = sampleWithPartialData;
        expectedResult = service.addFoncAgentToCollectionIfMissing([], foncAgent, foncAgent2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(foncAgent);
        expect(expectedResult).toContain(foncAgent2);
      });

      it('should accept null and undefined values', () => {
        const foncAgent: IFoncAgent = sampleWithRequiredData;
        expectedResult = service.addFoncAgentToCollectionIfMissing([], null, foncAgent, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(foncAgent);
      });

      it('should return initial array if no FoncAgent is added', () => {
        const foncAgentCollection: IFoncAgent[] = [sampleWithRequiredData];
        expectedResult = service.addFoncAgentToCollectionIfMissing(foncAgentCollection, undefined, null);
        expect(expectedResult).toEqual(foncAgentCollection);
      });
    });

    describe('compareFoncAgent', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFoncAgent(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareFoncAgent(entity1, entity2);
        const compareResult2 = service.compareFoncAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareFoncAgent(entity1, entity2);
        const compareResult2 = service.compareFoncAgent(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareFoncAgent(entity1, entity2);
        const compareResult2 = service.compareFoncAgent(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
