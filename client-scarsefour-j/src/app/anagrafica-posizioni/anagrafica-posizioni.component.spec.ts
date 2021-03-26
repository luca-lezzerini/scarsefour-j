import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaPosizioniComponent } from './anagrafica-posizioni.component';

describe('AnagraficaPosizioniComponent', () => {
  let component: AnagraficaPosizioniComponent;
  let fixture: ComponentFixture<AnagraficaPosizioniComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnagraficaPosizioniComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaPosizioniComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
