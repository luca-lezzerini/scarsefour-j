export interface StateTre{
    next(e : Event) : StateTre;
}

export interface Automabile{
    goToScontrinoVuoto();
    goToScontrinoNonVuoto();
    goToVediPrezzo();
    goToAnnullamentoScontrino();
}