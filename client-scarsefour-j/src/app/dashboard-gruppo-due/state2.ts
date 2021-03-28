import { EventDashboardDue } from "./event2";

export interface StateDashboardDue{
    next(event: EventDashboardDue): StateDashboardDue;
}

export interface AutomabileDue{

    goToScontrinoVuoto();
    goToScontrinoNonVuoto();
    goToAnnullamentoScontrino();
    goToVediPrezzo();
}