import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IDetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';
import { DetailEmployeeSocialPaymentService } from '../service/detail-employee-social-payment.service';

@Component({
  templateUrl: './detail-employee-social-payment-delete-dialog.component.html',
})
export class DetailEmployeeSocialPaymentDeleteDialogComponent {
  detailEmployeeSocialPayment?: IDetailEmployeeSocialPayment;

  constructor(protected detailEmployeeSocialPaymentService: DetailEmployeeSocialPaymentService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.detailEmployeeSocialPaymentService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
