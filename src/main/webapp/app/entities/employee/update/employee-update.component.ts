import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import dayjs from 'dayjs/esm';
import { DATE_TIME_FORMAT } from 'app/config/input.constants';

import { IEmployee, Employee } from '../employee.model';
import { EmployeeService } from '../service/employee.service';
import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IContract } from 'app/entities/contract/contract.model';
import { ContractService } from 'app/entities/contract/service/contract.service';
import { IPeriod } from 'app/entities/period/period.model';
import { PeriodService } from 'app/entities/period/service/period.service';
import { IOperatorType } from 'app/entities/operator-type/operator-type.model';
import { OperatorTypeService } from 'app/entities/operator-type/service/operator-type.service';
import { ISocialSecurity } from 'app/entities/social-security/social-security.model';
import { SocialSecurityService } from 'app/entities/social-security/service/social-security.service';
import { IOperatorMatriz } from 'app/entities/operator-matriz/operator-matriz.model';
import { OperatorMatrizService } from 'app/entities/operator-matriz/service/operator-matriz.service';
import { IDocumentType } from 'app/entities/document-type/document-type.model';
import { DocumentTypeService } from 'app/entities/document-type/service/document-type.service';
import { StateEmployee } from 'app/entities/enumerations/state-employee.model';

@Component({
  selector: 'payroll-employee-update',
  templateUrl: './employee-update.component.html',
})
export class EmployeeUpdateComponent implements OnInit {
  isSaving = false;
  stateEmployeeValues = Object.keys(StateEmployee);

  usersSharedCollection: IUser[] = [];
  contractsSharedCollection: IContract[] = [];
  periodsSharedCollection: IPeriod[] = [];
  operatorTypesSharedCollection: IOperatorType[] = [];
  socialSecuritiesSharedCollection: ISocialSecurity[] = [];
  operatorMatrizsSharedCollection: IOperatorMatriz[] = [];
  documentTypesSharedCollection: IDocumentType[] = [];

  editForm = this.fb.group({
    id: [],
    completeName: [null, [Validators.required, Validators.maxLength(100)]],
    documentNumber: [null, [Validators.required]],
    address: [null, [Validators.required, Validators.maxLength(100)]],
    dateStart: [null, [Validators.required]],
    city: [null, [Validators.required, Validators.maxLength(50)]],
    mobile: [null, [Validators.required, Validators.maxLength(50)]],
    stateEmployee: [null, [Validators.required]],
    user: [null, Validators.required],
    contract: [null, Validators.required],
    period: [null, Validators.required],
    operatorType: [null, Validators.required],
    socialSecurity: [null, Validators.required],
    operatorMatriz: [null, Validators.required],
    documentType: [null, Validators.required],
  });

  constructor(
    protected employeeService: EmployeeService,
    protected userService: UserService,
    protected contractService: ContractService,
    protected periodService: PeriodService,
    protected operatorTypeService: OperatorTypeService,
    protected socialSecurityService: SocialSecurityService,
    protected operatorMatrizService: OperatorMatrizService,
    protected documentTypeService: DocumentTypeService,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ employee }) => {
      if (employee.id === undefined) {
        const today = dayjs().startOf('day');
        employee.dateStart = today;
      }

      this.updateForm(employee);

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const employee = this.createFromForm();
    if (employee.id !== undefined) {
      this.subscribeToSaveResponse(this.employeeService.update(employee));
    } else {
      this.subscribeToSaveResponse(this.employeeService.create(employee));
    }
  }

  trackUserById(_index: number, item: IUser): number {
    return item.id!;
  }

  trackContractById(_index: number, item: IContract): number {
    return item.id!;
  }

  trackPeriodById(_index: number, item: IPeriod): number {
    return item.id!;
  }

  trackOperatorTypeById(_index: number, item: IOperatorType): number {
    return item.id!;
  }

  trackSocialSecurityById(_index: number, item: ISocialSecurity): number {
    return item.id!;
  }

  trackOperatorMatrizById(_index: number, item: IOperatorMatriz): number {
    return item.id!;
  }

  trackDocumentTypeById(_index: number, item: IDocumentType): number {
    return item.id!;
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IEmployee>>): void {
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

  protected updateForm(employee: IEmployee): void {
    this.editForm.patchValue({
      id: employee.id,
      completeName: employee.completeName,
      documentNumber: employee.documentNumber,
      address: employee.address,
      dateStart: employee.dateStart ? employee.dateStart.format(DATE_TIME_FORMAT) : null,
      city: employee.city,
      mobile: employee.mobile,
      stateEmployee: employee.stateEmployee,
      user: employee.user,
      contract: employee.contract,
      period: employee.period,
      operatorType: employee.operatorType,
      socialSecurity: employee.socialSecurity,
      operatorMatriz: employee.operatorMatriz,
      documentType: employee.documentType,
    });

    this.usersSharedCollection = this.userService.addUserToCollectionIfMissing(this.usersSharedCollection, employee.user);
    this.contractsSharedCollection = this.contractService.addContractToCollectionIfMissing(
      this.contractsSharedCollection,
      employee.contract
    );
    this.periodsSharedCollection = this.periodService.addPeriodToCollectionIfMissing(this.periodsSharedCollection, employee.period);
    this.operatorTypesSharedCollection = this.operatorTypeService.addOperatorTypeToCollectionIfMissing(
      this.operatorTypesSharedCollection,
      employee.operatorType
    );
    this.socialSecuritiesSharedCollection = this.socialSecurityService.addSocialSecurityToCollectionIfMissing(
      this.socialSecuritiesSharedCollection,
      employee.socialSecurity
    );
    this.operatorMatrizsSharedCollection = this.operatorMatrizService.addOperatorMatrizToCollectionIfMissing(
      this.operatorMatrizsSharedCollection,
      employee.operatorMatriz
    );
    this.documentTypesSharedCollection = this.documentTypeService.addDocumentTypeToCollectionIfMissing(
      this.documentTypesSharedCollection,
      employee.documentType
    );
  }

  protected loadRelationshipsOptions(): void {
    this.userService
      .query()
      .pipe(map((res: HttpResponse<IUser[]>) => res.body ?? []))
      .pipe(map((users: IUser[]) => this.userService.addUserToCollectionIfMissing(users, this.editForm.get('user')!.value)))
      .subscribe((users: IUser[]) => (this.usersSharedCollection = users));

    this.contractService
      .query()
      .pipe(map((res: HttpResponse<IContract[]>) => res.body ?? []))
      .pipe(
        map((contracts: IContract[]) =>
          this.contractService.addContractToCollectionIfMissing(contracts, this.editForm.get('contract')!.value)
        )
      )
      .subscribe((contracts: IContract[]) => (this.contractsSharedCollection = contracts));

    this.periodService
      .query()
      .pipe(map((res: HttpResponse<IPeriod[]>) => res.body ?? []))
      .pipe(map((periods: IPeriod[]) => this.periodService.addPeriodToCollectionIfMissing(periods, this.editForm.get('period')!.value)))
      .subscribe((periods: IPeriod[]) => (this.periodsSharedCollection = periods));

    this.operatorTypeService
      .query()
      .pipe(map((res: HttpResponse<IOperatorType[]>) => res.body ?? []))
      .pipe(
        map((operatorTypes: IOperatorType[]) =>
          this.operatorTypeService.addOperatorTypeToCollectionIfMissing(operatorTypes, this.editForm.get('operatorType')!.value)
        )
      )
      .subscribe((operatorTypes: IOperatorType[]) => (this.operatorTypesSharedCollection = operatorTypes));

    this.socialSecurityService
      .query()
      .pipe(map((res: HttpResponse<ISocialSecurity[]>) => res.body ?? []))
      .pipe(
        map((socialSecurities: ISocialSecurity[]) =>
          this.socialSecurityService.addSocialSecurityToCollectionIfMissing(socialSecurities, this.editForm.get('socialSecurity')!.value)
        )
      )
      .subscribe((socialSecurities: ISocialSecurity[]) => (this.socialSecuritiesSharedCollection = socialSecurities));

    this.operatorMatrizService
      .query()
      .pipe(map((res: HttpResponse<IOperatorMatriz[]>) => res.body ?? []))
      .pipe(
        map((operatorMatrizs: IOperatorMatriz[]) =>
          this.operatorMatrizService.addOperatorMatrizToCollectionIfMissing(operatorMatrizs, this.editForm.get('operatorMatriz')!.value)
        )
      )
      .subscribe((operatorMatrizs: IOperatorMatriz[]) => (this.operatorMatrizsSharedCollection = operatorMatrizs));

    this.documentTypeService
      .query()
      .pipe(map((res: HttpResponse<IDocumentType[]>) => res.body ?? []))
      .pipe(
        map((documentTypes: IDocumentType[]) =>
          this.documentTypeService.addDocumentTypeToCollectionIfMissing(documentTypes, this.editForm.get('documentType')!.value)
        )
      )
      .subscribe((documentTypes: IDocumentType[]) => (this.documentTypesSharedCollection = documentTypes));
  }

  protected createFromForm(): IEmployee {
    return {
      ...new Employee(),
      id: this.editForm.get(['id'])!.value,
      completeName: this.editForm.get(['completeName'])!.value,
      documentNumber: this.editForm.get(['documentNumber'])!.value,
      address: this.editForm.get(['address'])!.value,
      dateStart: this.editForm.get(['dateStart'])!.value ? dayjs(this.editForm.get(['dateStart'])!.value, DATE_TIME_FORMAT) : undefined,
      city: this.editForm.get(['city'])!.value,
      mobile: this.editForm.get(['mobile'])!.value,
      stateEmployee: this.editForm.get(['stateEmployee'])!.value,
      user: this.editForm.get(['user'])!.value,
      contract: this.editForm.get(['contract'])!.value,
      period: this.editForm.get(['period'])!.value,
      operatorType: this.editForm.get(['operatorType'])!.value,
      socialSecurity: this.editForm.get(['socialSecurity'])!.value,
      operatorMatriz: this.editForm.get(['operatorMatriz'])!.value,
      documentType: this.editForm.get(['documentType'])!.value,
    };
  }
}
