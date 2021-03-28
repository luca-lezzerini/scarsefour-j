
import { EventDashboardDue } from "./event2";
import { AutomabileDue, StateDashboardDue } from "./state2";
import { ScontrinoVuotoState } from "./stati2";

export class Automa2 implements StateDashboardDue{
stato : StateDashboardDue;
    gui : AutomabileDue;

    constructor(autom : AutomabileDue){
        this.gui = autom;
        this.stato = new ScontrinoVuotoState(this);
    }

    next(e : EventDashboardDue) : StateDashboardDue {
        console.log("siamo nello stato ", this.stato);
        console.log("ricevuto evento ", e);
        this.stato = this.stato.next(e);
        console.log("siamo passati nello stato ", this.stato);
        return this.stato;
    }

}
