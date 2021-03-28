import { EventGruppoQuattro } from "./event-gruppo-quattro";

export interface StateGruppoQuattro {
next(event: EventGruppoQuattro)

}

export interface AutomabileGruppoQuattro {
  goToScontrinoVuoto();
  goToVediPrezzo();
  gotoScontrinoNonVuoto();
  gotoAnnullamentoScontrino();
}