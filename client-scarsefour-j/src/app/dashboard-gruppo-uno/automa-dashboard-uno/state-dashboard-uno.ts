import { EventDashboardUno } from "./event-dashboard-uno";

export interface StateDashboardUno{
    next(event: EventDashboardUno): StateDashboardUno;
}

export interface AutomabileDashboardUno{

    goToScontrinoVuotoInitial();
    goToScontrinoVuotoPrimoEan();
    goToScontrinoVuotoFromAnnulla();
    goToScontrinoVuotoFromAll();
    goToVediPrezzo();
    goToScontrinoNonVuoto();
    goToAnnullamentoScontrino();
    
}