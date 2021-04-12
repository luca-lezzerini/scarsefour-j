import { EventGruppoQuattro } from "./event-gruppo-quattro";

export interface StateGruppoQuattro {
next(event: EventGruppoQuattro): StateGruppoQuattro;

}

export interface AutomabileGruppoQuattro {
  
  //metodi cambiamento stato
  goToScontrinoVuoto();
  goToVediPrezzo();
  gotoScontrinoNonVuoto();
  gotoAnnullamentoScontrino();

  //metodi esecuzione azioni
  vediPrezzoAction();
  chiudiScontrinoAction();
  stornaUltimoAction();
  stornaAction();
  annullaScontrinoAction();
  inserisciEanAction();
  verificaEanAction();
}
