import { AutomaTre } from "./automa-tre";
import { EventTre } from "./event-tre";
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaPiuEvent, StornaUnoEvent, VediPrezzoEvent } from "./eventi-tre";
import { StateTre } from "./state-tre";

export class ScontrinoVuotoState implements StateTre {
    constructor(public automa: AutomaTre) {
        automa.gui.goToScontrinoVuoto();
    }
    next(e: EventTre): StateTre {
        if (e instanceof EanEvent) {
            if (e.codiceEan) {
                this.automa.gui.aggiungiRigaScontrinoAction();
                return new ScontrinoNonVuotoState(this.automa);
            } else {
                return new ScontrinoVuotoState(this.automa);
            }
        } else if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        } else {
            console.log("Ricevuto evento inatteso");
        }
    }
}

export class ScontrinoNonVuotoState implements StateTre {
    constructor(public automa: AutomaTre) {
        automa.gui.goToScontrinoNonVuoto();
    }
    next(e: EventTre): StateTre {
        if (e instanceof ChiudiEvent) {
            return new ScontrinoVuotoState(this.automa);
        } else if (e instanceof StornaUnoEvent) {
            return new ScontrinoVuotoState(this.automa);
        } else if (e instanceof AnnullaScontrinoEvent) {
            return new AnnullamentoScontrinoState(this.automa);
        } else if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        } else if (e instanceof StornaPiuEvent) {
            return new ScontrinoNonVuotoState(this.automa);
        } else if (e instanceof EanEvent) {
            if (!e.codiceEan) {
                return new ScontrinoNonVuotoState(this.automa);
            } else if (e.codiceEan) {
                this.automa.gui.aggiungiRigaScontrinoAction();
                return new ScontrinoNonVuotoState(this.automa);
            }
        } else {
            console.log("Ricevuto evento inatteso");
        }
    }
}

export class AnnullamentoScontrinoState implements StateTre {
    constructor(public automa: AutomaTre) {
        automa.gui.goToAnnullamentoScontrino();
    }
    next(e: EventTre): StateTre {
        if (e instanceof AnnullaEvent) {
            return new ScontrinoNonVuotoState(this.automa);
        } else if (e instanceof ConfermaEvent) {
            this.automa.gui.annullaScontrinoAction();
            return new ScontrinoVuotoState(this.automa);
        } else {
            console.log("Ricevuto evento inatteso");
        }
    }
}

export class VediPrezzoState implements StateTre {
    constructor(public automa: AutomaTre) {
        automa.gui.goToVediPrezzo();       
    }
    next(e: EventTre): StateTre {
        if (e instanceof EanEvent) {
            this.automa.gui.vediPrezzoAction();
            if (!e.scontrino && e.codiceEan) {
                // caso scontrino vuoto e ean conosciuto
                return new ScontrinoVuotoState(this.automa);
            } else if (!e.scontrino && !e.codiceEan) {
                // caso scontrino vuoto e ean sconosciuto
                return new ScontrinoVuotoState(this.automa);
            } else if (e.scontrino && !e.codiceEan) {
                // caso scontrino non vuoto e ean sconosciuto
                return new ScontrinoNonVuotoState(this.automa);
            } else if (e.scontrino && e.codiceEan) {
                // caso scontrino non vuoto e ean conosciuto
                return new ScontrinoNonVuotoState(this.automa);
            } else {
                console.log("Ricevuto evento inatteso - A");
            }
        // }else if (e instanceof StornaUnoEvent){
        //     return new ScontrinoVuotoState(this.automa);
        // }else if (e instanceof StornaPiuEvent){
        //     return new ScontrinoNonVuotoState(this.automa);
        // }else if (e instanceof VediPrezzoEvent){
        //     return new VediPrezzoState(this.automa);    
        } else {
            console.log("Ricevuto evento inatteso - B");
        }
    }
}