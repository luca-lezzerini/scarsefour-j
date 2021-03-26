import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaCassiereComponent } from './anagrafica-cassiere.component';

describe('AnagraficaCassiereComponent', () => {
  let component: AnagraficaCassiereComponent;
  let fixture: ComponentFixture<AnagraficaCassiereComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnagraficaCassiereComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaCassiereComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
