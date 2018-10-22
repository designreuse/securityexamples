import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LaboratoryresultlistComponent } from './laboratoryresultlist.component';

describe('LaboratoryresultlistComponent', () => {
  let component: LaboratoryresultlistComponent;
  let fixture: ComponentFixture<LaboratoryresultlistComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LaboratoryresultlistComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LaboratoryresultlistComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
