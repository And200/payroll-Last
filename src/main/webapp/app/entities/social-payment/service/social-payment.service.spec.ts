import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { ISocialPayment, SocialPayment } from '../social-payment.model';

import { SocialPaymentService } from './social-payment.service';

describe('SocialPayment Service', () => {
  let service: SocialPaymentService;
  let httpMock: HttpTestingController;
  let elemDefault: ISocialPayment;
  let expectedResult: ISocialPayment | ISocialPayment[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(SocialPaymentService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      socialPaymentName: 'AAAAAAA',
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

    it('should create a SocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new SocialPayment()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a SocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          socialPaymentName: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a SocialPayment', () => {
      const patchObject = Object.assign(
        {
          socialPaymentName: 'BBBBBB',
        },
        new SocialPayment()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of SocialPayment', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          socialPaymentName: 'BBBBBB',
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

    it('should delete a SocialPayment', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addSocialPaymentToCollectionIfMissing', () => {
      it('should add a SocialPayment to an empty array', () => {
        const socialPayment: ISocialPayment = { id: 123 };
        expectedResult = service.addSocialPaymentToCollectionIfMissing([], socialPayment);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(socialPayment);
      });

      it('should not add a SocialPayment to an array that contains it', () => {
        const socialPayment: ISocialPayment = { id: 123 };
        const socialPaymentCollection: ISocialPayment[] = [
          {
            ...socialPayment,
          },
          { id: 456 },
        ];
        expectedResult = service.addSocialPaymentToCollectionIfMissing(socialPaymentCollection, socialPayment);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a SocialPayment to an array that doesn't contain it", () => {
        const socialPayment: ISocialPayment = { id: 123 };
        const socialPaymentCollection: ISocialPayment[] = [{ id: 456 }];
        expectedResult = service.addSocialPaymentToCollectionIfMissing(socialPaymentCollection, socialPayment);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(socialPayment);
      });

      it('should add only unique SocialPayment to an array', () => {
        const socialPaymentArray: ISocialPayment[] = [{ id: 123 }, { id: 456 }, { id: 58999 }];
        const socialPaymentCollection: ISocialPayment[] = [{ id: 123 }];
        expectedResult = service.addSocialPaymentToCollectionIfMissing(socialPaymentCollection, ...socialPaymentArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const socialPayment: ISocialPayment = { id: 123 };
        const socialPayment2: ISocialPayment = { id: 456 };
        expectedResult = service.addSocialPaymentToCollectionIfMissing([], socialPayment, socialPayment2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(socialPayment);
        expect(expectedResult).toContain(socialPayment2);
      });

      it('should accept null and undefined values', () => {
        const socialPayment: ISocialPayment = { id: 123 };
        expectedResult = service.addSocialPaymentToCollectionIfMissing([], null, socialPayment, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(socialPayment);
      });

      it('should return initial array if no SocialPayment is added', () => {
        const socialPaymentCollection: ISocialPayment[] = [{ id: 123 }];
        expectedResult = service.addSocialPaymentToCollectionIfMissing(socialPaymentCollection, undefined, null);
        expect(expectedResult).toEqual(socialPaymentCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
