import { AutomaDashboardUno } from "./automa-dashboard-uno";
import { EventDashboardUno } from "./event-dashboard-uno";
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaEvent, VediPrezzoEvent } from "./eventi-dashboard-uno";
import { StateDashboardUno } from "./state-dashboard-uno";

export class ScontrinoVuotoState implements StateDashboardUno {

    constructor(public automa: AutomaDashboardUno) {
        automa.gui.goToScontrinoVuotoInitial();
        // if (automa.stato instanceof ScontrinoVuotoState) {
        //     automa.gui.goToScontrinoVuotoPrimoEan();
        //     console.log("goToScontrinoVuotoPrimoEan()");
        // } else if (automa.stato instanceof ScontrinoNonVuotoState) {
        //     automa.gui.goToScontrinoVuotoFromAll();
        //     console.log("goToScontrinoVuotoFromAll()");
        // } else if (automa.stato instanceof VediPrezzoState) {
        //     automa.gui.goToScontrinoVuotoFromAll();
        //     console.log("goToScontrinoVuotoFromAll()");
        // } else if (automa.stato instanceof AnnullamentoScontrinoState) {
        //     automa.gui.goToScontrinoVuotoFromAnnulla();
        //     console.log("goToScontrinoVuotoFromAnnulla()");
        // }
        // else {
        //     automa.gui.goToScontrinoVuotoInitial();
        //     console.log("goToScontrinoVuotoInitial()");
        // }
    }

    next(e: EventDashboardUno): StateDashboardUno {
        if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        }
        else if (e instanceof EanEvent) {
            if (e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            }
            else {
                let st = new ScontrinoVuotoState(this.automa);
                this.automa.gui.goToScontrinoVuotoPrimoEan();
                console.log("sono in goToScontrinoVuotoPrimoEan")
                return st;
            }
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}


export class ScontrinoNonVuotoState implements StateDashboardUno {

    constructor(public automa: AutomaDashboardUno) {
        automa.gui.goToScontrinoNonVuoto();
    }

    next(e: EventDashboardUno): StateDashboardUno {
        if (e instanceof ChiudiEvent) {
            //this.automa.gui.goToScontrinoVuotoFromAll();
            return new ScontrinoVuotoState(this.automa);
        }
        else if (e instanceof StornaEvent) {
            if (e.numElem == 1) {
                //this.automa.gui.goToScontrinoVuotoFromAll();
                return new ScontrinoVuotoState(this.automa);
            }
            else if (e.numElem > 1) {
                return new ScontrinoNonVuotoState(this.automa);
            }
        }
        else if (e instanceof EanEvent) {
            if (!e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            }
            else if (e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            }
        }
        else if (e instanceof AnnullaScontrinoEvent) {
            return new AnnullamentoScontrinoState(this.automa);
        }
        else if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}

export class VediPrezzoState implements StateDashboardUno {

    constructor(public automa: AutomaDashboardUno) {
        automa.gui.goToVediPrezzo();
    }

    next(e: EventDashboardUno): StateDashboardUno {
        if (e instanceof EanEvent) {
            if (e.scontrino && e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            }
            else if (e.scontrino && !e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            }
            else if (!e.scontrino && !e.codiceEan) {
                //this.automa.gui.goToScontrinoVuotoFromAll();
                return new ScontrinoVuotoState(this.automa);
            }
            else if (!e.scontrino && e.codiceEan) {
                //this.automa.gui.goToScontrinoVuotoFromAll();
                return new ScontrinoVuotoState(this.automa);
            }
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}


export class AnnullamentoScontrinoState implements StateDashboardUno {

    constructor(public automa: AutomaDashboardUno) {
        automa.gui.goToAnnullamentoScontrino();
    }

    next(e: EventDashboardUno): StateDashboardUno {
        if (e instanceof AnnullaEvent) {
            return new ScontrinoNonVuotoState(this.automa);
        }
        else if (e instanceof ConfermaEvent) {
            //this.automa.gui.goToScontrinoVuotoFromAnnulla();
            return new ScontrinoVuotoState(this.automa);
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}

