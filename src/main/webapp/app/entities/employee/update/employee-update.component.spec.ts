import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { of, Subject, from } from 'rxjs';

import { EmployeeService } from '../service/employee.service';
import { IEmployee, Employee } from '../employee.model';

import { IUser } from 'app/entities/user/user.model';
import { UserService } from 'app/entities/user/user.service';
import { IContract } from 'app/entities/contract/contract.model';
import { ContractService } from 'app/entities/contract/service/contract.service';
import { IPeriod } from 'app/entities/period/period.model';
import { PeriodService } from 'app/entities/period/service/period.service';
import { IOperatorType } from 'app/entities/operator-type/operator-type.model';
import { OperatorTypeService } from 'app/entities/operator-type/service/operator-type.service';
import { ISocialSecurity } from 'app/entities/social-security/social-security.model';
import { SocialSecurityService } from 'app/entities/social-security/service/social-security.service';
import { IOperatorMatriz } from 'app/entities/operator-matriz/operator-matriz.model';
import { OperatorMatrizService } from 'app/entities/operator-matriz/service/operator-matriz.service';
import { IDocumentType } from 'app/entities/document-type/document-type.model';
import { DocumentTypeService } from 'app/entities/document-type/service/document-type.service';

import { EmployeeUpdateComponent } from './employee-update.component';

describe('Employee Management Update Component', () => {
  let comp: EmployeeUpdateComponent;
  let fixture: ComponentFixture<EmployeeUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let employeeService: EmployeeService;
  let userService: UserService;
  let contractService: ContractService;
  let periodService: PeriodService;
  let operatorTypeService: OperatorTypeService;
  let socialSecurityService: SocialSecurityService;
  let operatorMatrizService: OperatorMatrizService;
  let documentTypeService: DocumentTypeService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule.withRoutes([])],
      declarations: [EmployeeUpdateComponent],
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
      .overrideTemplate(EmployeeUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(EmployeeUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    employeeService = TestBed.inject(EmployeeService);
    userService = TestBed.inject(UserService);
    contractService = TestBed.inject(ContractService);
    periodService = TestBed.inject(PeriodService);
    operatorTypeService = TestBed.inject(OperatorTypeService);
    socialSecurityService = TestBed.inject(SocialSecurityService);
    operatorMatrizService = TestBed.inject(OperatorMatrizService);
    documentTypeService = TestBed.inject(DocumentTypeService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call User query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const user: IUser = { id: 84478 };
      employee.user = user;

      const userCollection: IUser[] = [{ id: 1447 }];
      jest.spyOn(userService, 'query').mockReturnValue(of(new HttpResponse({ body: userCollection })));
      const additionalUsers = [user];
      const expectedCollection: IUser[] = [...additionalUsers, ...userCollection];
      jest.spyOn(userService, 'addUserToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(userService.query).toHaveBeenCalled();
      expect(userService.addUserToCollectionIfMissing).toHaveBeenCalledWith(userCollection, ...additionalUsers);
      expect(comp.usersSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Contract query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const contract: IContract = { id: 79958 };
      employee.contract = contract;

      const contractCollection: IContract[] = [{ id: 65336 }];
      jest.spyOn(contractService, 'query').mockReturnValue(of(new HttpResponse({ body: contractCollection })));
      const additionalContracts = [contract];
      const expectedCollection: IContract[] = [...additionalContracts, ...contractCollection];
      jest.spyOn(contractService, 'addContractToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(contractService.query).toHaveBeenCalled();
      expect(contractService.addContractToCollectionIfMissing).toHaveBeenCalledWith(contractCollection, ...additionalContracts);
      expect(comp.contractsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call Period query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const period: IPeriod = { id: 7389 };
      employee.period = period;

      const periodCollection: IPeriod[] = [{ id: 37801 }];
      jest.spyOn(periodService, 'query').mockReturnValue(of(new HttpResponse({ body: periodCollection })));
      const additionalPeriods = [period];
      const expectedCollection: IPeriod[] = [...additionalPeriods, ...periodCollection];
      jest.spyOn(periodService, 'addPeriodToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(periodService.query).toHaveBeenCalled();
      expect(periodService.addPeriodToCollectionIfMissing).toHaveBeenCalledWith(periodCollection, ...additionalPeriods);
      expect(comp.periodsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call OperatorType query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const operatorType: IOperatorType = { id: 30485 };
      employee.operatorType = operatorType;

      const operatorTypeCollection: IOperatorType[] = [{ id: 3525 }];
      jest.spyOn(operatorTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: operatorTypeCollection })));
      const additionalOperatorTypes = [operatorType];
      const expectedCollection: IOperatorType[] = [...additionalOperatorTypes, ...operatorTypeCollection];
      jest.spyOn(operatorTypeService, 'addOperatorTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(operatorTypeService.query).toHaveBeenCalled();
      expect(operatorTypeService.addOperatorTypeToCollectionIfMissing).toHaveBeenCalledWith(
        operatorTypeCollection,
        ...additionalOperatorTypes
      );
      expect(comp.operatorTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call SocialSecurity query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const socialSecurity: ISocialSecurity = { id: 34960 };
      employee.socialSecurity = socialSecurity;

      const socialSecurityCollection: ISocialSecurity[] = [{ id: 40471 }];
      jest.spyOn(socialSecurityService, 'query').mockReturnValue(of(new HttpResponse({ body: socialSecurityCollection })));
      const additionalSocialSecurities = [socialSecurity];
      const expectedCollection: ISocialSecurity[] = [...additionalSocialSecurities, ...socialSecurityCollection];
      jest.spyOn(socialSecurityService, 'addSocialSecurityToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(socialSecurityService.query).toHaveBeenCalled();
      expect(socialSecurityService.addSocialSecurityToCollectionIfMissing).toHaveBeenCalledWith(
        socialSecurityCollection,
        ...additionalSocialSecurities
      );
      expect(comp.socialSecuritiesSharedCollection).toEqual(expectedCollection);
    });

    it('Should call OperatorMatriz query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const operatorMatriz: IOperatorMatriz = { id: 81881 };
      employee.operatorMatriz = operatorMatriz;

      const operatorMatrizCollection: IOperatorMatriz[] = [{ id: 75701 }];
      jest.spyOn(operatorMatrizService, 'query').mockReturnValue(of(new HttpResponse({ body: operatorMatrizCollection })));
      const additionalOperatorMatrizs = [operatorMatriz];
      const expectedCollection: IOperatorMatriz[] = [...additionalOperatorMatrizs, ...operatorMatrizCollection];
      jest.spyOn(operatorMatrizService, 'addOperatorMatrizToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(operatorMatrizService.query).toHaveBeenCalled();
      expect(operatorMatrizService.addOperatorMatrizToCollectionIfMissing).toHaveBeenCalledWith(
        operatorMatrizCollection,
        ...additionalOperatorMatrizs
      );
      expect(comp.operatorMatrizsSharedCollection).toEqual(expectedCollection);
    });

    it('Should call DocumentType query and add missing value', () => {
      const employee: IEmployee = { id: 456 };
      const documentType: IDocumentType = { id: 42223 };
      employee.documentType = documentType;

      const documentTypeCollection: IDocumentType[] = [{ id: 61901 }];
      jest.spyOn(documentTypeService, 'query').mockReturnValue(of(new HttpResponse({ body: documentTypeCollection })));
      const additionalDocumentTypes = [documentType];
      const expectedCollection: IDocumentType[] = [...additionalDocumentTypes, ...documentTypeCollection];
      jest.spyOn(documentTypeService, 'addDocumentTypeToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(documentTypeService.query).toHaveBeenCalled();
      expect(documentTypeService.addDocumentTypeToCollectionIfMissing).toHaveBeenCalledWith(
        documentTypeCollection,
        ...additionalDocumentTypes
      );
      expect(comp.documentTypesSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const employee: IEmployee = { id: 456 };
      const user: IUser = { id: 66698 };
      employee.user = user;
      const contract: IContract = { id: 28059 };
      employee.contract = contract;
      const period: IPeriod = { id: 95823 };
      employee.period = period;
      const operatorType: IOperatorType = { id: 57684 };
      employee.operatorType = operatorType;
      const socialSecurity: ISocialSecurity = { id: 67167 };
      employee.socialSecurity = socialSecurity;
      const operatorMatriz: IOperatorMatriz = { id: 72611 };
      employee.operatorMatriz = operatorMatriz;
      const documentType: IDocumentType = { id: 58219 };
      employee.documentType = documentType;

      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      expect(comp.editForm.value).toEqual(expect.objectContaining(employee));
      expect(comp.usersSharedCollection).toContain(user);
      expect(comp.contractsSharedCollection).toContain(contract);
      expect(comp.periodsSharedCollection).toContain(period);
      expect(comp.operatorTypesSharedCollection).toContain(operatorType);
      expect(comp.socialSecuritiesSharedCollection).toContain(socialSecurity);
      expect(comp.operatorMatrizsSharedCollection).toContain(operatorMatriz);
      expect(comp.documentTypesSharedCollection).toContain(documentType);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Employee>>();
      const employee = { id: 123 };
      jest.spyOn(employeeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: employee }));
      saveSubject.complete();

      // THEN
      expect(comp.previousState).toHaveBeenCalled();
      expect(employeeService.update).toHaveBeenCalledWith(employee);
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Employee>>();
      const employee = new Employee();
      jest.spyOn(employeeService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: employee }));
      saveSubject.complete();

      // THEN
      expect(employeeService.create).toHaveBeenCalledWith(employee);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<Employee>>();
      const employee = { id: 123 };
      jest.spyOn(employeeService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ employee });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(employeeService.update).toHaveBeenCalledWith(employee);
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Tracking relationships identifiers', () => {
    describe('trackUserById', () => {
      it('Should return tracked User primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackUserById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackContractById', () => {
      it('Should return tracked Contract primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackContractById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackPeriodById', () => {
      it('Should return tracked Period primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackPeriodById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackOperatorTypeById', () => {
      it('Should return tracked OperatorType primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackOperatorTypeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackSocialSecurityById', () => {
      it('Should return tracked SocialSecurity primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackSocialSecurityById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackOperatorMatrizById', () => {
      it('Should return tracked OperatorMatriz primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackOperatorMatrizById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });

    describe('trackDocumentTypeById', () => {
      it('Should return tracked DocumentType primary key', () => {
        const entity = { id: 123 };
        const trackResult = comp.trackDocumentTypeById(0, entity);
        expect(trackResult).toEqual(entity.id);
      });
    });
  });
});
