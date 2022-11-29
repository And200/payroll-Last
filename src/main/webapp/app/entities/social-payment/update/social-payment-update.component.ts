import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { ISocialPayment, SocialPayment } from '../social-payment.model';
import { SocialPaymentService } from '../service/social-payment.service';

@Component({
  selector: 'payroll-social-payment-update',
  templateUrl: './social-payment-update.component.html',
})
export class SocialPaymentUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    socialPaymentName: [null, [Validators.required, Validators.maxLength(50)]],
  });

  constructor(protected socialPaymentService: SocialPaymentService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ socialPayment }) => {
      this.updateForm(socialPayment);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const socialPayment = this.createFromForm();
    if (socialPayment.id !== undefined) {
      this.subscribeToSaveResponse(this.socialPaymentService.update(socialPayment));
    } else {
      this.subscribeToSaveResponse(this.socialPaymentService.create(socialPayment));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISocialPayment>>): void {
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

  protected updateForm(socialPayment: ISocialPayment): void {
    this.editForm.patchValue({
      id: socialPayment.id,
      socialPaymentName: socialPayment.socialPaymentName,
    });
  }

  protected createFromForm(): ISocialPayment {
    return {
      ...new SocialPayment(),
      id: this.editForm.get(['id'])!.value,
      socialPaymentName: this.editForm.get(['socialPaymentName'])!.value,
    };
  }
}
