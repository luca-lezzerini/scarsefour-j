import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ScaricaMerceComponent } from './scarica-merce.component';

describe('ScaricaMerceComponent', () => {
  let component: ScaricaMerceComponent;
  let fixture: ComponentFixture<ScaricaMerceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ScaricaMerceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ScaricaMerceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
