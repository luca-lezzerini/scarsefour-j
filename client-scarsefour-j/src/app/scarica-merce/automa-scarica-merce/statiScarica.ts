import { AutomaScarica } from "./automaScarica";
import { ScaricaEvent, SelezionaPosizioneEvent, SelezionaProdottoEvent, TrovaEvent } from "./eventiScarica";
import { EventScarica } from "./eventScarica";
import { StateScarica } from "./stateScarica";

export class RicercaScaricaState implements StateScarica {
    constructor(public automa: AutomaScarica) {
        this.automa.gui.goToRicerca();
    }
    next(event: EventScarica): StateScarica {
        if (event instanceof SelezionaPosizioneEvent) {
            return new PosizioneSelezionataState(this.automa);
        }
        else if (event instanceof TrovaEvent) {
            return this.automa.stato;
        }
        else {
            console.log("evento inaspettato")
            return this.automa.stato;
        }
    }
}
export class PosizioneSelezionataState implements StateScarica {
    constructor(public automa: AutomaScarica) {
        this.automa.gui.goToPosizioneSelezionata();
    }
    next(event: EventScarica): StateScarica {
        if (event instanceof TrovaEvent) {
            return new RicercaScaricaState(this.automa);
        }
        else if (event instanceof SelezionaProdottoEvent) {
            return new ProdottoSelezionatoState(this.automa);
        }
        else {
            console.log("evento inaspettato")
            return this.automa.stato;
        }
    }
}
export class ProdottoSelezionatoState implements StateScarica {
    constructor(public automa: AutomaScarica) {
        this.automa.gui.goToProdottoSelezionato();
    }
    next(event: EventScarica): StateScarica {
        if (event instanceof SelezionaProdottoEvent) {
            return this.automa.stato;
        }
        else if (event instanceof ScaricaEvent) {
            return this.automa.stato;
        }
        else if (event instanceof TrovaEvent) {
            return new RicercaScaricaState(this.automa);
        }
        else {
            console.log("evento inaspettato")
            return this.automa.stato;
        }
    }
}
