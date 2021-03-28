import { EventDashboardUno } from "./event-dashboard-uno";
import { AutomabileDashboardUno, StateDashboardUno } from "./state-dashboard-uno";
import { ScontrinoVuotoState } from "./stati-dashboard-uno";

export class AutomaDashboardUno implements StateDashboardUno{
    stato : StateDashboardUno;
    gui : AutomabileDashboardUno;

    constructor(autom : AutomabileDashboardUno){
        this.gui = autom;
        this.stato = new ScontrinoVuotoState(this);
    }

    next(e : EventDashboardUno) : StateDashboardUno {
        console.log("siamo nello stato ", this.stato);
        console.log("ricevuto evento ", e);
        this.stato = this.stato.next(e);
        console.log("siamo passati nello stato ", this.stato);
        return this.stato;
    }

}
