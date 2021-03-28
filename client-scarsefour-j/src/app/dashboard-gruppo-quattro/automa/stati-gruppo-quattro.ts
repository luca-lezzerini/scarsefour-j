import { AutomaGruppoQuattro } from "./automa-gruppo-quattro";
import { EventGruppoQuattro } from "./event-gruppo-quattro";
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaEvent, VediPrezzoEvent } from "./eventi-gruppo-quattro";
import { StateGruppoQuattro } from "./state-gruppo-quattro";

export class ScontrinoVuotoState implements StateGruppoQuattro {
    constructor(public automa: AutomaGruppoQuattro) {
        automa.gui.goToScontrinoVuoto();
    }
    next(e: Event): StateGruppoQuattro {
        if (e instanceof VediPrezzoEvent) {
            return new VediPrezzoState(this.automa);
        }
        if (e instanceof EanEvent) {
            return new ScontrinoNonVuotoState(this.automa);
        }
        if (e instanceof EanEvent) {
            return new ScontrinoVuotoState(this.automa);
        }
        else {
            console.log('Ricevuto evento inatteso');
        }
    }
}
export class ScontrinoNonVuotoState implements StateGruppoQuattro {
    element: Number;
    constructor(public automa: AutomaGruppoQuattro) {
        automa.gui.gotoScontrinoNonVuoto();
    }
    next(e: Event): StateGruppoQuattro {
        if (e instanceof AnnullaScontrinoEvent) {
            return new AnnullamentoScontrinoState(this.automa);
        }
        if (e instanceof ChiudiEvent) {
            return new ScontrinoVuotoState(this.automa);
        }
        if (e instanceof StornaEvent) {
            if (e.element == 1) {
                return new ScontrinoVuotoState(this.automa);
            }


            if (e.element > 1) {
                return new ScontrinoNonVuotoState(this.automa);

            }
        }
        if (e instanceof EanEvent) {
            if (e.codiceEan == "conosciuto") {
                return new ScontrinoNonVuotoState(this.automa);
            }

            if (e.codiceEan == "sconosciuto") {
                return new ScontrinoNonVuotoState(this.automa);
            }
            if (e instanceof VediPrezzoEvent) {
                return new VediPrezzoState(this.automa);
            }

            else {
                console.log('Ricevuto evento inatteso');
            }
        }
    }

}
export class VediPrezzoState implements StateGruppoQuattro {

    constructor(public automa: AutomaGruppoQuattro) {
        automa.gui.goToVediPrezzo();
    }
    next(e: Event): StateGruppoQuattro {
        if (e instanceof EanEvent) {

            if (e.scontrino != null && e.codiceEan == "sconosciuto") {
                return new ScontrinoNonVuotoState(this.automa);
            }
            else if (e.scontrino != null && e.codiceEan == "conosciuto") {
                return new ScontrinoNonVuotoState(this.automa);
            }

            else if (e.scontrino == null && e.codiceEan == "sconosciuto") {
                return new ScontrinoVuotoState(this.automa);
            }
            else if (e.scontrino == null && e.codiceEan == "conosciuto") {
                return new ScontrinoVuotoState(this.automa);
            }
        }
        else {
            console.log('Ricevuto evento inatteso');
        }
    }

}

export class AnnullamentoScontrinoState implements StateGruppoQuattro {
    constructor(public automa: AutomaGruppoQuattro) {
        automa.gui.gotoAnnullamentoScontrino();
    }
    next(e: EventGruppoQuattro) {
        if (e instanceof AnnullaEvent) {
            return new ScontrinoNonVuotoState(this.automa);
        }
        if (e instanceof ConfermaEvent) {
            return new ScontrinoVuotoState(this.automa);
        }
    }
}
