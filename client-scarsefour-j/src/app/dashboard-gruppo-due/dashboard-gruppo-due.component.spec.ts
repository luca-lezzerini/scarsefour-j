import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardGruppoDueComponent } from './dashboard-gruppo-due.component';

describe('DashboardGruppoDueComponent', () => {
  let component: DashboardGruppoDueComponent;
  let fixture: ComponentFixture<DashboardGruppoDueComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardGruppoDueComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardGruppoDueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
