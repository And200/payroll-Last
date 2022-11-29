import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { SocialPaymentComponent } from './list/social-payment.component';
import { SocialPaymentDetailComponent } from './detail/social-payment-detail.component';
import { SocialPaymentUpdateComponent } from './update/social-payment-update.component';
import { SocialPaymentDeleteDialogComponent } from './delete/social-payment-delete-dialog.component';
import { SocialPaymentRoutingModule } from './route/social-payment-routing.module';

@NgModule({
  imports: [SharedModule, SocialPaymentRoutingModule],
  declarations: [SocialPaymentComponent, SocialPaymentDetailComponent, SocialPaymentUpdateComponent, SocialPaymentDeleteDialogComponent],
  entryComponents: [SocialPaymentDeleteDialogComponent],
})
export class SocialPaymentModule {}
