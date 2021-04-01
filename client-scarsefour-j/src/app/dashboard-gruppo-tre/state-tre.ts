import { EventTre } from "./event-tre";

export interface StateTre{
    next(e : EventTre) : StateTre;
}

export interface AutomabileTre{
    // metodi cambiamento stato
    goToScontrinoVuoto();
    goToScontrinoNonVuoto();
    goToVediPrezzo();
    goToAnnullamentoScontrino();

    // metodi esecuzione azioni
    aggiungiRigaScontrinoAction();
    annullaScontrinoAction();
    erroreEanAction();
    vediPrezzoAction();
}