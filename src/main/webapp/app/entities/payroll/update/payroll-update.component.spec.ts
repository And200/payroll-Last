import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { PayrollService } from '../service/payroll.service';
import { IPayroll, Payroll } from '../payroll.model';
import { IIncome } from 'app/entities/income/income.model';
import { IncomeService } from 'app/entities/income/service/income.service';
import { IDeduction } from 'app/entities/deduction/deduction.model';
import { DeductionService } from 'app/entities/deduction/service/deduction.service';
import { IPositionArl } from 'app/entities/position-arl/position-arl.model';
import { PositionArlService } from 'app/entities/position-arl/service/position-arl.service';
import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';

import { PayrollUpdateComponent } from './payroll-update.component';

describe('Payroll Management Update Component', () => {
  let comp: PayrollUpdateComponent;
  let fixture: ComponentFixture<PayrollUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let payrollService: PayrollService;
  let incomeService: IncomeService;
  let deductionService: DeductionService;
  let positionArlService: PositionArlService;
  let employeeService: EmployeeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [PayrollUpdateComponent],
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
      .overrideTemplate(PayrollUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(PayrollUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    payrollService = TestBed.inject(PayrollService);
    incomeService = TestBed.inject(IncomeService);
    deductionService = TestBed.inject(DeductionService);
    positionArlService = TestBed.inject(PositionArlService);
    employeeService = TestBed.inject(EmployeeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Income query and add missing value', () => {
      const payroll: IPayroll = { id: 456 };
      const income: IIncome = { id: 53773 };
      payroll.income = income;

      const incomeCollection: IIncome[] = [{ id: 97922 }];
      jest.spyOn(incomeService, 'query').mockReturnValue(of(new HttpResponse({ body: incomeCollection })));
      const additionalIncomes = [income];
      const expectedCollection: IIncome[] = [...additionalIncomes, ...incomeCollection];
      jest.spyOn(incomeService, 'addIncomeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      expect(incomeService.query).toHaveBeenCalled();
      expect(incomeService.addIncomeToCollectionIfMissing).toHaveBeenCalledWith(incomeCollection, ...additionalIncomes);
      expect(comp.incomesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Deduction query and add missing value', () => {
      const payroll: IPayroll = { id: 456 };
      const deduction: IDeduction = { id: 34485 };
      payroll.deduction = deduction;

      const deductionCollection: IDeduction[] = [{ id: 61833 }];
      jest.spyOn(deductionService, 'query').mockReturnValue(of(new HttpResponse({ body: deductionCollection })));
      const additionalDeductions = [deduction];
      const expectedCollection: IDeduction[] = [...additionalDeductions, ...deductionCollection];
      jest.spyOn(deductionService, 'addDeductionToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      expect(deductionService.query).toHaveBeenCalled();
      expect(deductionService.addDeductionToCollectionIfMissing).toHaveBeenCalledWith(deductionCollection, ...additionalDeductions);
      expect(comp.deductionsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call PositionArl query and add missing value', () => {
      const payroll: IPayroll = { id: 456 };
      const positionArl: IPositionArl = { id: 7279 };
      payroll.positionArl = positionArl;

      const positionArlCollection: IPositionArl[] = [{ id: 27910 }];
      jest.spyOn(positionArlService, 'query').mockReturnValue(of(new HttpResponse({ body: positionArlCollection })));
      const additionalPositionArls = [positionArl];
      const expectedCollection: IPositionArl[] = [...additionalPositionArls, ...positionArlCollection];
      jest.spyOn(positionArlService, 'addPositionArlToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      expect(positionArlService.query).toHaveBeenCalled();
      expect(positionArlService.addPositionArlToCollectionIfMissing).toHaveBeenCalledWith(positionArlCollection, ...additionalPositionArls);
      expect(comp.positionArlsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Employee query and add missing value', () => {
      const payroll: IPayroll = { id: 456 };
      const employee: IEmployee = { id: 58563 };
      payroll.employee = employee;

      const employeeCollection: IEmployee[] = [{ id: 15708 }];
      jest.spyOn(employeeService, 'query').mockReturnValue(of(new HttpResponse({ body: employeeCollection })));
      const additionalEmployees = [employee];
      const expectedCollection: IEmployee[] = [...additionalEmployees, ...employeeCollection];
      jest.spyOn(employeeService, 'addEmployeeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      expect(employeeService.query).toHaveBeenCalled();
      expect(employeeService.addEmployeeToCollectionIfMissing).toHaveBeenCalledWith(employeeCollection, ...additionalEmployees);
      expect(comp.employeesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const payroll: IPayroll = { id: 456 };
      const income: IIncome = { id: 60302 };
      payroll.income = income;
      const deduction: IDeduction = { id: 26161 };
      payroll.deduction = deduction;
      const positionArl: IPositionArl = { id: 62846 };
      payroll.positionArl = positionArl;
      const employee: IEmployee = { id: 2875 };
      payroll.employee = employee;

      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(payroll));
      expect(comp.incomesSharedCollection).toContain(income);
      expect(comp.deductionsSharedCollection).toContain(deduction);
      expect(comp.positionArlsSharedCollection).toContain(positionArl);
      expect(comp.employeesSharedCollection).toContain(employee);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Payroll>>();
      const payroll = { id: 123 };
      jest.spyOn(payrollService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: payroll }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(payrollService.update).toHaveBeenCalledWith(payroll);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Payroll>>();
      const payroll = new Payroll();
      jest.spyOn(payrollService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: payroll }));
      saveSubject.complete();

      // THEN
      expect(payrollService.create).toHaveBeenCalledWith(payroll);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Payroll>>();
      const payroll = { id: 123 };
      jest.spyOn(payrollService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ payroll });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(payrollService.update).toHaveBeenCalledWith(payroll);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackIncomeById', () => {
      it('Should return tracked Income primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackIncomeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDeductionById', () => {
      it('Should return tracked Deduction primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDeductionById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackPositionArlById', () => {
      it('Should return tracked PositionArl primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackPositionArlById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackEmployeeById', () => {
      it('Should return tracked Employee primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackEmployeeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
