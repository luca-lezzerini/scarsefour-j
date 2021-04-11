import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VisualizzaGiacenzaComponent } from './visualizza-giacenza.component';

describe('VisualizzaGiacenzaComponent', () => {
  let component: VisualizzaGiacenzaComponent;
  let fixture: ComponentFixture<VisualizzaGiacenzaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VisualizzaGiacenzaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VisualizzaGiacenzaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
