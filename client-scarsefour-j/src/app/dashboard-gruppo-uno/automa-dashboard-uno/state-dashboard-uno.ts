import { EventDashboardUno } from "./event-dashboard-uno";

export interface StateDashboardUno{
    next(event: EventDashboardUno): StateDashboardUno;
}

export interface AutomabileDashboardUno{

    goToScontrinoVuoto();
    goToVediPrezzo();
    goToScontrinoNonVuoto();
    goToAnnullamentoScontrino();
    
}