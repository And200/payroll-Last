import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ISocialPayment } from '../social-payment.model';
import { SocialPaymentService } from '../service/social-payment.service';

@Component({
  templateUrl: './social-payment-delete-dialog.component.html',
})
export class SocialPaymentDeleteDialogComponent {
  socialPayment?: ISocialPayment;

  constructor(protected socialPaymentService: SocialPaymentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.socialPaymentService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
