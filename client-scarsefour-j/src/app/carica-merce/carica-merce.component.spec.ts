import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CaricaMerceComponent } from './carica-merce.component';

describe('CaricaMerceComponent', () => {
  let component: CaricaMerceComponent;
  let fixture: ComponentFixture<CaricaMerceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CaricaMerceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CaricaMerceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
