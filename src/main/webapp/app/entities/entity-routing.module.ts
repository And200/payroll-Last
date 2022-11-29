import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'contract',
        data: { pageTitle: 'payrollApp.contract.home.title' },
        loadChildren: () => import('./contract/contract.module').then(m => m.ContractModule),
      },
      {
        path: 'document-type',
        data: { pageTitle: 'payrollApp.documentType.home.title' },
        loadChildren: () => import('./document-type/document-type.module').then(m => m.DocumentTypeModule),
      },
      {
        path: 'employee',
        data: { pageTitle: 'payrollApp.employee.home.title' },
        loadChildren: () => import('./employee/employee.module').then(m => m.EmployeeModule),
      },
      {
        path: 'position-arl',
        data: { pageTitle: 'payrollApp.positionArl.home.title' },
        loadChildren: () => import('./position-arl/position-arl.module').then(m => m.PositionArlModule),
      },
      {
        path: 'social-security',
        data: { pageTitle: 'payrollApp.socialSecurity.home.title' },
        loadChildren: () => import('./social-security/social-security.module').then(m => m.SocialSecurityModule),
      },
      {
        path: 'payroll',
        data: { pageTitle: 'payrollApp.payroll.home.title' },
        loadChildren: () => import('./payroll/payroll.module').then(m => m.PayrollModule),
      },
      {
        path: 'income',
        data: { pageTitle: 'payrollApp.income.home.title' },
        loadChildren: () => import('./income/income.module').then(m => m.IncomeModule),
      },
      {
        path: 'deduction',
        data: { pageTitle: 'payrollApp.deduction.home.title' },
        loadChildren: () => import('./deduction/deduction.module').then(m => m.DeductionModule),
      },
      {
        path: 'period',
        data: { pageTitle: 'payrollApp.period.home.title' },
        loadChildren: () => import('./period/period.module').then(m => m.PeriodModule),
      },
      {
        path: 'cost-center',
        data: { pageTitle: 'payrollApp.costCenter.home.title' },
        loadChildren: () => import('./cost-center/cost-center.module').then(m => m.CostCenterModule),
      },
      {
        path: 'project-master',
        data: { pageTitle: 'payrollApp.projectMaster.home.title' },
        loadChildren: () => import('./project-master/project-master.module').then(m => m.ProjectMasterModule),
      },
      {
        path: 'account-plan',
        data: { pageTitle: 'payrollApp.accountPlan.home.title' },
        loadChildren: () => import('./account-plan/account-plan.module').then(m => m.AccountPlanModule),
      },
      {
        path: 'social-payment',
        data: { pageTitle: 'payrollApp.socialPayment.home.title' },
        loadChildren: () => import('./social-payment/social-payment.module').then(m => m.SocialPaymentModule),
      },
      {
        path: 'operator-type',
        data: { pageTitle: 'payrollApp.operatorType.home.title' },
        loadChildren: () => import('./operator-type/operator-type.module').then(m => m.OperatorTypeModule),
      },
      {
        path: 'operator-matriz',
        data: { pageTitle: 'payrollApp.operatorMatriz.home.title' },
        loadChildren: () => import('./operator-matriz/operator-matriz.module').then(m => m.OperatorMatrizModule),
      },
      {
        path: 'detail-employee-social-payment',
        data: { pageTitle: 'payrollApp.detailEmployeeSocialPayment.home.title' },
        loadChildren: () =>
          import('./detail-employee-social-payment/detail-employee-social-payment.module').then(m => m.DetailEmployeeSocialPaymentModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
