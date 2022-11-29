import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { DetailEmployeeSocialPaymentComponent } from './list/detail-employee-social-payment.component';
import { DetailEmployeeSocialPaymentDetailComponent } from './detail/detail-employee-social-payment-detail.component';
import { DetailEmployeeSocialPaymentUpdateComponent } from './update/detail-employee-social-payment-update.component';
import { DetailEmployeeSocialPaymentDeleteDialogComponent } from './delete/detail-employee-social-payment-delete-dialog.component';
import { DetailEmployeeSocialPaymentRoutingModule } from './route/detail-employee-social-payment-routing.module';

@NgModule({
  imports: [SharedModule, DetailEmployeeSocialPaymentRoutingModule],
  declarations: [
    DetailEmployeeSocialPaymentComponent,
    DetailEmployeeSocialPaymentDetailComponent,
    DetailEmployeeSocialPaymentUpdateComponent,
    DetailEmployeeSocialPaymentDeleteDialogComponent,
  ],
  entryComponents: [DetailEmployeeSocialPaymentDeleteDialogComponent],
})
export class DetailEmployeeSocialPaymentModule {}
