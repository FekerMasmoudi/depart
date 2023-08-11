import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IItineraire } from '../itineraire.model';
import { sampleWithRequiredData, sampleWithNewData, sampleWithPartialData, sampleWithFullData } from '../itineraire.test-samples';

import { ItineraireService } from './itineraire.service';

const requireRestSample: IItineraire = {
  ...sampleWithRequiredData,
};

describe('Itineraire Service', () => {
  let service: ItineraireService;
  let httpMock: HttpTestingController;
  let expectedResult: IItineraire | IItineraire[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(ItineraireService);
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

    it('should create a Itineraire', () => {
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      const itineraire = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(itineraire).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Itineraire', () => {
      const itineraire = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(itineraire).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Itineraire', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Itineraire', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a Itineraire', () => {
      const expected = true;

      service.delete('ABC').subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addItineraireToCollectionIfMissing', () => {
      it('should add a Itineraire to an empty array', () => {
        const itineraire: IItineraire = sampleWithRequiredData;
        expectedResult = service.addItineraireToCollectionIfMissing([], itineraire);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(itineraire);
      });

      it('should not add a Itineraire to an array that contains it', () => {
        const itineraire: IItineraire = sampleWithRequiredData;
        const itineraireCollection: IItineraire[] = [
          {
            ...itineraire,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addItineraireToCollectionIfMissing(itineraireCollection, itineraire);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Itineraire to an array that doesn't contain it", () => {
        const itineraire: IItineraire = sampleWithRequiredData;
        const itineraireCollection: IItineraire[] = [sampleWithPartialData];
        expectedResult = service.addItineraireToCollectionIfMissing(itineraireCollection, itineraire);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(itineraire);
      });

      it('should add only unique Itineraire to an array', () => {
        const itineraireArray: IItineraire[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const itineraireCollection: IItineraire[] = [sampleWithRequiredData];
        expectedResult = service.addItineraireToCollectionIfMissing(itineraireCollection, ...itineraireArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const itineraire: IItineraire = sampleWithRequiredData;
        const itineraire2: IItineraire = sampleWithPartialData;
        expectedResult = service.addItineraireToCollectionIfMissing([], itineraire, itineraire2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(itineraire);
        expect(expectedResult).toContain(itineraire2);
      });

      it('should accept null and undefined values', () => {
        const itineraire: IItineraire = sampleWithRequiredData;
        expectedResult = service.addItineraireToCollectionIfMissing([], null, itineraire, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(itineraire);
      });

      it('should return initial array if no Itineraire is added', () => {
        const itineraireCollection: IItineraire[] = [sampleWithRequiredData];
        expectedResult = service.addItineraireToCollectionIfMissing(itineraireCollection, undefined, null);
        expect(expectedResult).toEqual(itineraireCollection);
      });
    });

    describe('compareItineraire', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareItineraire(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = null;

        const compareResult1 = service.compareItineraire(entity1, entity2);
        const compareResult2 = service.compareItineraire(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'CBA' };

        const compareResult1 = service.compareItineraire(entity1, entity2);
        const compareResult2 = service.compareItineraire(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 'ABC' };
        const entity2 = { id: 'ABC' };

        const compareResult1 = service.compareItineraire(entity1, entity2);
        const compareResult2 = service.compareItineraire(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
