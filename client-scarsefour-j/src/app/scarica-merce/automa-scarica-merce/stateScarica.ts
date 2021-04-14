import { EventScarica } from "./eventScarica";

export interface StateScarica{
    next(event: EventScarica): StateScarica;
}

export interface AutomabileScarica{
    goToRicerca(): void;
    goToPosizioneSelezionata(): void;
    goToProdottoSelezionato(): void;
}