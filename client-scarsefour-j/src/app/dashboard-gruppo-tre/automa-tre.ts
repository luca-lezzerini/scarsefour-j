import { EventTre } from "./event-tre";
import { Automabile, StateTre } from "./state-tre";
import { ScontrinoVuotoState } from "./stati";

export class AutomaTre implements StateTre {
    stato: StateTre;
    gui: Automabile;

    constructor(autom: Automabile) {
        this.gui = autom;
        this.stato = new ScontrinoVuotoState(this);
    }

    next(e: EventTre): StateTre {
        console.log('Siamo nello stato ', this.stato);
        console.log('Ricevuto evento', e);
        this.stato = this.stato.next(e);
        console.log('Siamo passati nello stato ', this.stato)
        return this.stato;
    }

}