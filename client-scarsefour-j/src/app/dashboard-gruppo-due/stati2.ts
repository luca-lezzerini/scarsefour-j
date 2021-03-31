import { Automa2 } from "./automa-gruppo-due";
import { EventDashboardDue } from "./event2";
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaUltimoEvent, VediPrezzoEvent } from "./eventi2";
import { StateDashboardDue } from "./state2";

export class ScontrinoVuotoState implements StateDashboardDue {

    constructor(public automa: Automa2) {
        automa.gui.goToScontrinoVuoto();
    }

    next(e: EventDashboardDue): StateDashboardDue {
        if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        }
        else if (e instanceof EanEvent) {
            if(e.esito){
                return new ScontrinoNonVuotoState(this.automa);
            }else{
                return new ScontrinoVuotoState(this.automa);
            }
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}

export class ScontrinoNonVuotoState implements StateDashboardDue {

    constructor(public automa: Automa2) {
        automa.gui.goToScontrinoNonVuoto();
    }

    next(e: EventDashboardDue): StateDashboardDue {
        if (e instanceof ChiudiEvent) {
            return new ScontrinoVuotoState(this.automa);
        }
        else if (e instanceof StornaUltimoEvent) {
            if (e.numeroRighe == 1) {
                return new ScontrinoVuotoState(this.automa);
            }
            else if (e.numeroRighe > 1){
                return this.automa.stato;
            }
        }
        else if (e instanceof EanEvent){ 
            if (e.esito){
                return this.automa.stato;
            }
            else{
                return this.automa.stato;
            }
        }
        else if (e instanceof AnnullaScontrinoEvent){
            return new AnnullamentoScontrinoState(this.automa);
        }
        else if (e instanceof VediPrezzoEvent){
            return new VediPrezzoState(this.automa);
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}

export class VediPrezzoState implements StateDashboardDue {

    constructor(public automa: Automa2) {
        automa.gui.goToVediPrezzo();
    }

    next(e: EventDashboardDue): StateDashboardDue {
        if (e instanceof EanEvent) {
            if (e.esito && e.numeroRighe == 0){
                return new ScontrinoVuotoState(this.automa);
            } else if (!e.esito && e.numeroRighe == 0){
                return new ScontrinoVuotoState(this.automa);
            }else if (e.esito && e.numeroRighe > 0){
                return new ScontrinoNonVuotoState(this.automa);
            }else if (!e.esito && e.numeroRighe > 0){
                return new ScontrinoNonVuotoState(this.automa);
            }
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}


export class AnnullamentoScontrinoState implements StateDashboardDue {

    constructor(public automa: Automa2) {
        automa.gui.goToAnnullamentoScontrino();
    }

    next(e: EventDashboardDue): StateDashboardDue {
        if (e instanceof AnnullaEvent) {
           return new ScontrinoNonVuotoState(this.automa);
        }
        else if (e instanceof ConfermaEvent){
            return new ScontrinoVuotoState(this.automa);
        }
        else {
            console.log("evento inaspettato!");
        }
    }
}