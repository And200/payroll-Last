import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDetailEmployeeSocialPayment } from '../detail-employee-social-payment.model';

@Component({
  selector: 'payroll-detail-employee-social-payment-detail',
  templateUrl: './detail-employee-social-payment-detail.component.html',
})
export class DetailEmployeeSocialPaymentDetailComponent implements OnInit {
  detailEmployeeSocialPayment: IDetailEmployeeSocialPayment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ detailEmployeeSocialPayment }) => {
      this.detailEmployeeSocialPayment = detailEmployeeSocialPayment;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
