import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardGruppoUnoComponent } from './dashboard-gruppo-uno.component';

describe('DashboardGruppoUnoComponent', () => {
  let component: DashboardGruppoUnoComponent;
  let fixture: ComponentFixture<DashboardGruppoUnoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DashboardGruppoUnoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardGruppoUnoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
