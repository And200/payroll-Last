import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IDetailEmployeeSocialPayment, DetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';

import { DetailEmployeeSocialPaymentService } from './detail-employee-social-payment.service';

describe('DetailEmployeeSocialPayment Service', () => {
  let service: DetailEmployeeSocialPaymentService;
  let httpMock: HttpTestingController;
  let elemDefault: IDetailEmployeeSocialPayment;
  let expectedResult: IDetailEmployeeSocialPayment | IDetailEmployeeSocialPayment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(DetailEmployeeSocialPaymentService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      description: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a DetailEmployeeSocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new DetailEmployeeSocialPayment()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a DetailEmployeeSocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          description: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a DetailEmployeeSocialPayment', () => {
      const patchObject = Object.assign({}, new DetailEmployeeSocialPayment());

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of DetailEmployeeSocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          description: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a DetailEmployeeSocialPayment', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addDetailEmployeeSocialPaymentToCollectionIfMissing', () => {
      it('should add a DetailEmployeeSocialPayment to an empty array', () => {
        const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 123 };
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing([], detailEmployeeSocialPayment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detailEmployeeSocialPayment);
      });

      it('should not add a DetailEmployeeSocialPayment to an array that contains it', () => {
        const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 123 };
        const detailEmployeeSocialPaymentCollection: IDetailEmployeeSocialPayment[] = [
          {
            ...detailEmployeeSocialPayment,
          },
          { id: 456 },
        ];
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing(
          detailEmployeeSocialPaymentCollection,
          detailEmployeeSocialPayment
        );
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a DetailEmployeeSocialPayment to an array that doesn't contain it", () => {
        const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 123 };
        const detailEmployeeSocialPaymentCollection: IDetailEmployeeSocialPayment[] = [{ id: 456 }];
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing(
          detailEmployeeSocialPaymentCollection,
          detailEmployeeSocialPayment
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detailEmployeeSocialPayment);
      });

      it('should add only unique DetailEmployeeSocialPayment to an array', () => {
        const detailEmployeeSocialPaymentArray: IDetailEmployeeSocialPayment[] = [{ id: 123 }, { id: 456 }, { id: 60065 }];
        const detailEmployeeSocialPaymentCollection: IDetailEmployeeSocialPayment[] = [{ id: 123 }];
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing(
          detailEmployeeSocialPaymentCollection,
          ...detailEmployeeSocialPaymentArray
        );
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 123 };
        const detailEmployeeSocialPayment2: IDetailEmployeeSocialPayment = { id: 456 };
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing(
          [],
          detailEmployeeSocialPayment,
          detailEmployeeSocialPayment2
        );
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(detailEmployeeSocialPayment);
        expect(expectedResult).toContain(detailEmployeeSocialPayment2);
      });

      it('should accept null and undefined values', () => {
        const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 123 };
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing([], null, detailEmployeeSocialPayment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(detailEmployeeSocialPayment);
      });

      it('should return initial array if no DetailEmployeeSocialPayment is added', () => {
        const detailEmployeeSocialPaymentCollection: IDetailEmployeeSocialPayment[] = [{ id: 123 }];
        expectedResult = service.addDetailEmployeeSocialPaymentToCollectionIfMissing(
          detailEmployeeSocialPaymentCollection,
          undefined,
          null
        );
        expect(expectedResult).toEqual(detailEmployeeSocialPaymentCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
