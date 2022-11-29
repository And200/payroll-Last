import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import { IDetailEmployeeSocialPayment, DetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';
import { DetailEmployeeSocialPaymentService } from '../service/detail-employee-social-payment.service';
import { IEmployee } from 'app/entities/employee/employee.model';
import { EmployeeService } from 'app/entities/employee/service/employee.service';
import { ISocialPayment } from 'app/entities/social-payment/social-payment.model';
import { SocialPaymentService } from 'app/entities/social-payment/service/social-payment.service';

@Component({
  selector: 'payroll-detail-employee-social-payment-update',
  templateUrl: './detail-employee-social-payment-update.component.html',
})
export class DetailEmployeeSocialPaymentUpdateComponent implements OnInit {
  isSaving = false;

  employeesSharedCollection: IEmployee[] = [];
  socialPaymentsSharedCollection: ISocialPayment[] = [];

  editForm = this.fb.group({
    id: [],
    description: [null, [Validators.required, Validators.maxLength(100)]],
    employee: [null, Validators.required],
    socialPayment: [null, Validators.required],
  });

  constructor(
    protected detailEmployeeSocialPaymentService: DetailEmployeeSocialPaymentService,
    protected employeeService: EmployeeService,
    protected socialPaymentService: SocialPaymentService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailEmployeeSocialPayment }) => {
      this.updateForm(detailEmployeeSocialPayment);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const detailEmployeeSocialPayment = this.createFromForm();
    if (detailEmployeeSocialPayment.id !== undefined) {
      this.subscribeToSaveResponse(this.detailEmployeeSocialPaymentService.update(detailEmployeeSocialPayment));
    } else {
      this.subscribeToSaveResponse(this.detailEmployeeSocialPaymentService.create(detailEmployeeSocialPayment));
    }
  }

  trackEmployeeById(_index: number, item: IEmployee): number {
    return item.id!;
  }

  trackSocialPaymentById(_index: number, item: ISocialPayment): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IDetailEmployeeSocialPayment>>): void {
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

  protected updateForm(detailEmployeeSocialPayment: IDetailEmployeeSocialPayment): void {
    this.editForm.patchValue({
      id: detailEmployeeSocialPayment.id,
      description: detailEmployeeSocialPayment.description,
      employee: detailEmployeeSocialPayment.employee,
      socialPayment: detailEmployeeSocialPayment.socialPayment,
    });

    this.employeesSharedCollection = this.employeeService.addEmployeeToCollectionIfMissing(
      this.employeesSharedCollection,
      detailEmployeeSocialPayment.employee
    );
    this.socialPaymentsSharedCollection = this.socialPaymentService.addSocialPaymentToCollectionIfMissing(
      this.socialPaymentsSharedCollection,
      detailEmployeeSocialPayment.socialPayment
    );
  }

  protected loadRelationshipsOptions(): void {
    this.employeeService
      .query()
      .pipe(map((res: HttpResponse<IEmployee[]>) => res.body ?? []))
      .pipe(
        map((employees: IEmployee[]) =>
          this.employeeService.addEmployeeToCollectionIfMissing(employees, this.editForm.get('employee')!.value)
        )
      )
      .subscribe((employees: IEmployee[]) => (this.employeesSharedCollection = employees));

    this.socialPaymentService
      .query()
      .pipe(map((res: HttpResponse<ISocialPayment[]>) => res.body ?? []))
      .pipe(
        map((socialPayments: ISocialPayment[]) =>
          this.socialPaymentService.addSocialPaymentToCollectionIfMissing(socialPayments, this.editForm.get('socialPayment')!.value)
        )
      )
      .subscribe((socialPayments: ISocialPayment[]) => (this.socialPaymentsSharedCollection = socialPayments));
  }

  protected createFromForm(): IDetailEmployeeSocialPayment {
    return {
      ...new DetailEmployeeSocialPayment(),
      id: this.editForm.get(['id'])!.value,
      description: this.editForm.get(['description'])!.value,
      employee: this.editForm.get(['employee'])!.value,
      socialPayment: this.editForm.get(['socialPayment'])!.value,
    };
  }
}
