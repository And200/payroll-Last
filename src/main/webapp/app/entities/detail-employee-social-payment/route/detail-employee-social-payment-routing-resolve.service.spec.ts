import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, ActivatedRoute, Router, convertToParamMap } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';

import { IDetailEmployeeSocialPayment, DetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';
import { DetailEmployeeSocialPaymentService } from '../service/detail-employee-social-payment.service';

import { DetailEmployeeSocialPaymentRoutingResolveService } from './detail-employee-social-payment-routing-resolve.service';

describe('DetailEmployeeSocialPayment routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let routingResolveService: DetailEmployeeSocialPaymentRoutingResolveService;
  let service: DetailEmployeeSocialPaymentService;
  let resultDetailEmployeeSocialPayment: IDetailEmployeeSocialPayment | undefined;

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
    routingResolveService = TestBed.inject(DetailEmployeeSocialPaymentRoutingResolveService);
    service = TestBed.inject(DetailEmployeeSocialPaymentService);
    resultDetailEmployeeSocialPayment = undefined;
  });

  describe('resolve', () => {
    it('should return IDetailEmployeeSocialPayment returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetailEmployeeSocialPayment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDetailEmployeeSocialPayment).toEqual({ id: 123 });
    });

    it('should return new IDetailEmployeeSocialPayment if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetailEmployeeSocialPayment = result;
      });

      // THEN
      expect(service.find).not.toBeCalled();
      expect(resultDetailEmployeeSocialPayment).toEqual(new DetailEmployeeSocialPayment());
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse({ body: null as unknown as DetailEmployeeSocialPayment })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
        resultDetailEmployeeSocialPayment = result;
      });

      // THEN
      expect(service.find).toBeCalledWith(123);
      expect(resultDetailEmployeeSocialPayment).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
