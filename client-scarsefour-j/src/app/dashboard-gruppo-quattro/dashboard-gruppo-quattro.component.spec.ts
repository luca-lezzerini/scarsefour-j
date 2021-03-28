import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardGruppoQuattroComponent } from './dashboard-gruppo-quattro.component';

describe('DashboardGruppoQuattroComponent', () => {
  let component: DashboardGruppoQuattroComponent;
  let fixture: ComponentFixture<DashboardGruppoQuattroComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardGruppoQuattroComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardGruppoQuattroComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
