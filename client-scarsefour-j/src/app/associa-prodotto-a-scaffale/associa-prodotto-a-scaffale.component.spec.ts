import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AssociaProdottoAScaffaleComponent } from './associa-prodotto-a-scaffale.component';

describe('AssociaProdottoAScaffaleComponent', () => {
  let component: AssociaProdottoAScaffaleComponent;
  let fixture: ComponentFixture<AssociaProdottoAScaffaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AssociaProdottoAScaffaleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AssociaProdottoAScaffaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
