import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardGruppoTreComponent } from './dashboard-gruppo-tre.component';

describe('DashboardGruppoTreComponent', () => {
  let component: DashboardGruppoTreComponent;
  let fixture: ComponentFixture<DashboardGruppoTreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardGruppoTreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardGruppoTreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
