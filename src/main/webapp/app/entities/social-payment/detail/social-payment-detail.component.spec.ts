import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SocialPaymentDetailComponent } from './social-payment-detail.component';

describe('SocialPayment Management Detail Component', () => {
  let comp: SocialPaymentDetailComponent;
  let fixture: ComponentFixture<SocialPaymentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SocialPaymentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ socialPayment: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(SocialPaymentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(SocialPaymentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load socialPayment on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.socialPayment).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
