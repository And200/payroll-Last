import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { SocialPaymentService } from '../service/social-payment.service';
import { ISocialPayment, SocialPayment } from '../social-payment.model';

import { SocialPaymentUpdateComponent } from './social-payment-update.component';

describe('SocialPayment Management Update Component', () => {
  let comp: SocialPaymentUpdateComponent;
  let fixture: ComponentFixture<SocialPaymentUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let socialPaymentService: SocialPaymentService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [SocialPaymentUpdateComponent],
      providers: [
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(SocialPaymentUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(SocialPaymentUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    socialPaymentService = TestBed.inject(SocialPaymentService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const socialPayment: ISocialPayment = { id: 456 };

      activatedRoute.data = of({ socialPayment });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(socialPayment));
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<SocialPayment>>();
      const socialPayment = { id: 123 };
      jest.spyOn(socialPaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ socialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: socialPayment }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(socialPaymentService.update).toHaveBeenCalledWith(socialPayment);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<SocialPayment>>();
      const socialPayment = new SocialPayment();
      jest.spyOn(socialPaymentService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ socialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: socialPayment }));
      saveSubject.complete();

      // THEN
      expect(socialPaymentService.create).toHaveBeenCalledWith(socialPayment);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<SocialPayment>>();
      const socialPayment = { id: 123 };
      jest.spyOn(socialPaymentService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ socialPayment });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(socialPaymentService.update).toHaveBeenCalledWith(socialPayment);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
