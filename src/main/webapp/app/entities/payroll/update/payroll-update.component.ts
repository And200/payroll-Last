import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IPayroll, Payroll } from '../payroll.model';
import { PayrollService } from '../service/payroll.service';
import { IIncome } from 'app/entities/income/income.model';
import { IncomeService } from 'app/entities/income/service/income.service';
import { IDeduction } from 'app/entities/deduction/deduction.model';
import { DeductionService } from 'app/entities/deduction/service/deduction.service';
import { IPositionArl } from 'app/entities/position-arl/position-arl.model';
import { PositionArlService } from 'app/entities/position-arl/service/position-arl.service';
import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';

@Component({
  selector: 'payroll-payroll-update',
  templateUrl: './payroll-update.component.html',
})
export class PayrollUpdateComponent implements OnInit {
  isSaving = false;

  incomesSharedCollection: IIncome[] = [];
  deductionsSharedCollection: IDeduction[] = [];
  positionArlsSharedCollection: IPositionArl[] = [];
  employeesSharedCollection: IEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    workedDays: [null, [Validators.required]],
    baseSalary: [null, [Validators.required]],
    income: [null, Validators.required],
    deduction: [null, Validators.required],
    positionArl: [null, Validators.required],
    employee: [null, Validators.required],
  });

  constructor(
    protected payrollService: PayrollService,
    protected incomeService: IncomeService,
    protected deductionService: DeductionService,
    protected positionArlService: PositionArlService,
    protected employeeService: EmployeeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ payroll }) => {
      this.updateForm(payroll);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const payroll = this.createFromForm();
    if (payroll.id !== undefined) {
      this.subscribeToSaveResponse(this.payrollService.update(payroll));
    } else {
      this.subscribeToSaveResponse(this.payrollService.create(payroll));
    }
  }

  trackIncomeById(_index: number, item: IIncome): number {
    return item.id!;
  }

  trackDeductionById(_index: number, item: IDeduction): number {
    return item.id!;
  }

  trackPositionArlById(_index: number, item: IPositionArl): number {
    return item.id!;
  }

  trackEmployeeById(_index: number, item: IEmployee): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPayroll>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(payroll: IPayroll): void {
    this.editForm.patchValue({
      id: payroll.id,
      workedDays: payroll.workedDays,
      baseSalary: payroll.baseSalary,
      income: payroll.income,
      deduction: payroll.deduction,
      positionArl: payroll.positionArl,
      employee: payroll.employee,
    });

    this.incomesSharedCollection = this.incomeService.addIncomeToCollectionIfMissing(this.incomesSharedCollection, payroll.income);
    this.deductionsSharedCollection = this.deductionService.addDeductionToCollectionIfMissing(
      this.deductionsSharedCollection,
      payroll.deduction
    );
    this.positionArlsSharedCollection = this.positionArlService.addPositionArlToCollectionIfMissing(
      this.positionArlsSharedCollection,
      payroll.positionArl
    );
    this.employeesSharedCollection = this.employeeService.addEmployeeToCollectionIfMissing(
      this.employeesSharedCollection,
      payroll.employee
    );
  }

  protected loadRelationshipsOptions(): void {
    this.incomeService
      .query()
      .pipe(map((res: HttpResponse<IIncome[]>) => res.body ?? []))
      .pipe(map((incomes: IIncome[]) => this.incomeService.addIncomeToCollectionIfMissing(incomes, this.editForm.get('income')!.value)))
      .subscribe((incomes: IIncome[]) => (this.incomesSharedCollection = incomes));

    this.deductionService
      .query()
      .pipe(map((res: HttpResponse<IDeduction[]>) => res.body ?? []))
      .pipe(
        map((deductions: IDeduction[]) =>
          this.deductionService.addDeductionToCollectionIfMissing(deductions, this.editForm.get('deduction')!.value)
        )
      )
      .subscribe((deductions: IDeduction[]) => (this.deductionsSharedCollection = deductions));

    this.positionArlService
      .query()
      .pipe(map((res: HttpResponse<IPositionArl[]>) => res.body ?? []))
      .pipe(
        map((positionArls: IPositionArl[]) =>
          this.positionArlService.addPositionArlToCollectionIfMissing(positionArls, this.editForm.get('positionArl')!.value)
        )
      )
      .subscribe((positionArls: IPositionArl[]) => (this.positionArlsSharedCollection = positionArls));

    this.employeeService
      .query()
      .pipe(map((res: HttpResponse<IEmployee[]>) => res.body ?? []))
      .pipe(
        map((employees: IEmployee[]) =>
          this.employeeService.addEmployeeToCollectionIfMissing(employees, this.editForm.get('employee')!.value)
        )
      )
      .subscribe((employees: IEmployee[]) => (this.employeesSharedCollection = employees));
  }

  protected createFromForm(): IPayroll {
    return {
      ...new Payroll(),
      id: this.editForm.get(['id'])!.value,
      workedDays: this.editForm.get(['workedDays'])!.value,
      baseSalary: this.editForm.get(['baseSalary'])!.value,
      income: this.editForm.get(['income'])!.value,
      deduction: this.editForm.get(['deduction'])!.value,
      positionArl: this.editForm.get(['positionArl'])!.value,
      employee: this.editForm.get(['employee'])!.value,
    };
  }
}
