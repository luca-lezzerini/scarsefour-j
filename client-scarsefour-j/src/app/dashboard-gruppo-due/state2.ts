import { EventDashboardDue } from './event2';

export interface StateDashboardDue{
    next(event: EventDashboardDue): StateDashboardDue;
}

export interface AutomabileDue {
    goToScontrinoVuoto(): void;
    goToScontrinoNonVuoto(): void;
    goToAnnullamentoScontrino(): void;
    goToVediPrezzo(): void;

    vediPrezzoAction(): void;
    aggiungiProdottoAction(): void;
    annullaScontrinoAction(): void;
}
