import { EventGruppoQuattro } from "./event-gruppo-quattro";

export interface StateGruppoQuattro {
next(event: EventGruppoQuattro)

}

export interface AutomabileGruppoQuattro {
  
  //metodi cambiamento stato
  goToScontrinoVuoto();
  goToVediPrezzo();
  gotoScontrinoNonVuoto();
  gotoAnnullamentoScontrino();

  //metodi esecuzione azioni
  

}
