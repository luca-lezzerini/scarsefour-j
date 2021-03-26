import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaCasseComponent } from './anagrafica-casse.component';

describe('AnagraficaCasseComponent', () => {
  let component: AnagraficaCasseComponent;
  let fixture: ComponentFixture<AnagraficaCasseComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnagraficaCasseComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaCasseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
