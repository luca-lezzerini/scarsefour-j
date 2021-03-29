import { EventTre } from "./event-tre";
import { AutomabileTre, StateTre } from "./state-tre";
import { ScontrinoVuotoState } from "./stati-tre";

export class AutomaTre implements StateTre {
    stato: StateTre;
    gui: AutomabileTre;

    constructor(autom: AutomabileTre) {
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