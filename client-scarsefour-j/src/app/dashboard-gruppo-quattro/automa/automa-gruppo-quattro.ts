import { EventGruppoQuattro } from "./event-gruppo-quattro";
import { AutomabileGruppoQuattro, StateGruppoQuattro } from "./state-gruppo-quattro";
import { ScontrinoVuotoState } from "./stati-gruppo-quattro";

export class AutomaGruppoQuattro implements StateGruppoQuattro {
    gui: AutomabileGruppoQuattro;
    stato: StateGruppoQuattro
    constructor(autom: AutomabileGruppoQuattro) {
        this.gui = autom;
        this.stato = new ScontrinoVuotoState(this);

    }
    next(e: EventGruppoQuattro): StateGruppoQuattro {
        console.log('Siamo nello stato ', this.stato);
        console.log('Ricevuto evento', e);
        this.stato=this.stato.next(e);
        console.log('Siamo passati nello stato ', this.stato)
        return this.stato;
    }
}