
import { EventScarica } from "./eventScarica";
import { AutomabileScarica, StateScarica } from "./stateScarica";
import { RicercaScaricaState } from "./statiScarica";

export class AutomaScarica implements StateScarica {
    stato: StateScarica;
    gui: AutomabileScarica;

    constructor(automabile: AutomabileScarica){
       this.gui = automabile;
       this.stato= new RicercaScaricaState(this);
    }

    next(event: EventScarica): StateScarica {
        console.log("Siamo nello stato ", this.stato);
        console.log("Ricevuto evento", event);
        this.stato = this.stato.next(event);
        console.log("Siamo arrivati allo stato", this.stato);
        return this.stato;
    }
}