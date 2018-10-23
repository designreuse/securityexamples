import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaboratoryresultdetailComponent } from './laboratoryresultdetail.component';

describe('LaboratoryresultdetailComponent', () => {
  let component: LaboratoryresultdetailComponent;
  let fixture: ComponentFixture<LaboratoryresultdetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaboratoryresultdetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaboratoryresultdetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
