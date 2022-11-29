import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DetailEmployeeSocialPaymentDetailComponent } from './detail-employee-social-payment-detail.component';

describe('DetailEmployeeSocialPayment Management Detail Component', () => {
  let comp: DetailEmployeeSocialPaymentDetailComponent;
  let fixture: ComponentFixture<DetailEmployeeSocialPaymentDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DetailEmployeeSocialPaymentDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ detailEmployeeSocialPayment: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(DetailEmployeeSocialPaymentDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(DetailEmployeeSocialPaymentDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load detailEmployeeSocialPayment on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.detailEmployeeSocialPayment).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});
