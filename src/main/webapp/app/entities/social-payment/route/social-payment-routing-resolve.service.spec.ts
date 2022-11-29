import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { ISocialPayment, SocialPayment } from '../social-payment.model';
import { SocialPaymentService } from '../service/social-payment.service';

import { SocialPaymentRoutingResolveService } from './social-payment-routing-resolve.service';

describe('SocialPayment routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: SocialPaymentRoutingResolveService;
  let service: SocialPaymentService;
  let resultSocialPayment: ISocialPayment | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    routingResolveService = TestBed.inject(SocialPaymentRoutingResolveService);
    service = TestBed.inject(SocialPaymentService);
    resultSocialPayment = undefined;
  });

  describe('resolve', () => {
    it('should return ISocialPayment returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSocialPayment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultSocialPayment).toEqual({ id: 123 });
    });

    it('should return new ISocialPayment if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSocialPayment = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultSocialPayment).toEqual(new SocialPayment());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as SocialPayment })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultSocialPayment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultSocialPayment).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
