import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaboratoryresultComponent } from './laboratoryresult.component';

describe('LaboratoryresultComponent', () => {
  let component: LaboratoryresultComponent;
  let fixture: ComponentFixture<LaboratoryresultComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaboratoryresultComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaboratoryresultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
