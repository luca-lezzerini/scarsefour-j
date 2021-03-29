import { Event } from "./event";

export interface State {
    next(event: Event): State;
}

export interface AutomabileCrud{
    // metodi cambiamento stato
    goToRicerca();
    goToAggiungi();
    goToVisualizza();
    goToModifica();
    goToRimuovi();

    // metodi esecuzione azioni
    rimuoviAction();
    aggiungiAction();
    modificaAction();
}