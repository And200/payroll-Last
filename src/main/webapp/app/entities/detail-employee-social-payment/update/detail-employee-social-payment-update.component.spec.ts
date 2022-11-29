import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { DetailEmployeeSocialPaymentService } from '../service/detail-employee-social-payment.service';
import { IDetailEmployeeSocialPayment, DetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';
import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';
import { ISocialPayment } from 'app/entities/social-payment/social-payment.model';
import { SocialPaymentService } from 'app/entities/social-payment/service/social-payment.service';

import { DetailEmployeeSocialPaymentUpdateComponent } from './detail-employee-social-payment-update.component';

describe('DetailEmployeeSocialPayment Management Update Component', () => {
  let comp: DetailEmployeeSocialPaymentUpdateComponent;
  let fixture: ComponentFixture<DetailEmployeeSocialPaymentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let detailEmployeeSocialPaymentService: DetailEmployeeSocialPaymentService;
  let employeeService: EmployeeService;
  let socialPaymentService: SocialPaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [DetailEmployeeSocialPaymentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(DetailEmployeeSocialPaymentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(DetailEmployeeSocialPaymentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    detailEmployeeSocialPaymentService = TestBed.inject(DetailEmployeeSocialPaymentService);
    employeeService = TestBed.inject(EmployeeService);
    socialPaymentService = TestBed.inject(SocialPaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Employee query and add missing value', () => {
      const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 456 };
      const employee: IEmployee = { id: 24266 };
      detailEmployeeSocialPayment.employee = employee;

      const employeeCollection: IEmployee[] = [{ id: 37114 }];
      jest.spyOn(employeeService, 'query').mockReturnValue(of(new HttpResponse({ body: employeeCollection })));
      const additionalEmployees = [employee];
      const expectedCollection: IEmployee[] = [...additionalEmployees, ...employeeCollection];
      jest.spyOn(employeeService, 'addEmployeeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      expect(employeeService.query).toHaveBeenCalled();
      expect(employeeService.addEmployeeToCollectionIfMissing).toHaveBeenCalledWith(employeeCollection, ...additionalEmployees);
      expect(comp.employeesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call SocialPayment query and add missing value', () => {
      const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 456 };
      const socialPayment: ISocialPayment = { id: 77872 };
      detailEmployeeSocialPayment.socialPayment = socialPayment;

      const socialPaymentCollection: ISocialPayment[] = [{ id: 4656 }];
      jest.spyOn(socialPaymentService, 'query').mockReturnValue(of(new HttpResponse({ body: socialPaymentCollection })));
      const additionalSocialPayments = [socialPayment];
      const expectedCollection: ISocialPayment[] = [...additionalSocialPayments, ...socialPaymentCollection];
      jest.spyOn(socialPaymentService, 'addSocialPaymentToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      expect(socialPaymentService.query).toHaveBeenCalled();
      expect(socialPaymentService.addSocialPaymentToCollectionIfMissing).toHaveBeenCalledWith(
        socialPaymentCollection,
        ...additionalSocialPayments
      );
      expect(comp.socialPaymentsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const detailEmployeeSocialPayment: IDetailEmployeeSocialPayment = { id: 456 };
      const employee: IEmployee = { id: 44785 };
      detailEmployeeSocialPayment.employee = employee;
      const socialPayment: ISocialPayment = { id: 24069 };
      detailEmployeeSocialPayment.socialPayment = socialPayment;

      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(detailEmployeeSocialPayment));
      expect(comp.employeesSharedCollection).toContain(employee);
      expect(comp.socialPaymentsSharedCollection).toContain(socialPayment);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DetailEmployeeSocialPayment>>();
      const detailEmployeeSocialPayment = { id: 123 };
      jest.spyOn(detailEmployeeSocialPaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detailEmployeeSocialPayment }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(detailEmployeeSocialPaymentService.update).toHaveBeenCalledWith(detailEmployeeSocialPayment);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DetailEmployeeSocialPayment>>();
      const detailEmployeeSocialPayment = new DetailEmployeeSocialPayment();
      jest.spyOn(detailEmployeeSocialPaymentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: detailEmployeeSocialPayment }));
      saveSubject.complete();

      // THEN
      expect(detailEmployeeSocialPaymentService.create).toHaveBeenCalledWith(detailEmployeeSocialPayment);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<DetailEmployeeSocialPayment>>();
      const detailEmployeeSocialPayment = { id: 123 };
      jest.spyOn(detailEmployeeSocialPaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ detailEmployeeSocialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(detailEmployeeSocialPaymentService.update).toHaveBeenCalledWith(detailEmployeeSocialPayment);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackEmployeeById', () => {
      it('Should return tracked Employee primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackEmployeeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackSocialPaymentById', () => {
      it('Should return tracked SocialPayment primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackSocialPaymentById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
