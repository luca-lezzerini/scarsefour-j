import { EventTre } from "./event-tre";

export interface StateTre{
    next(e : EventTre) : StateTre;
}

export interface Automabile{
    goToScontrinoVuoto();
    goToScontrinoNonVuoto();
    goToVediPrezzo();
    goToAnnullamentoScontrino();
}