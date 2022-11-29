import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISocialPayment } from '../social-payment.model';

@Component({
  selector: 'payroll-social-payment-detail',
  templateUrl: './social-payment-detail.component.html',
})
export class SocialPaymentDetailComponent implements OnInit {
  socialPayment: ISocialPayment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ socialPayment }) => {
      this.socialPayment = socialPayment;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
