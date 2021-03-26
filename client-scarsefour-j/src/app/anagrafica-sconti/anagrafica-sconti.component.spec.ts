import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnagraficaScontiComponent } from './anagrafica-sconti.component';

describe('AnagraficaScontiComponent', () => {
  let component: AnagraficaScontiComponent;
  let fixture: ComponentFixture<AnagraficaScontiComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnagraficaScontiComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnagraficaScontiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
