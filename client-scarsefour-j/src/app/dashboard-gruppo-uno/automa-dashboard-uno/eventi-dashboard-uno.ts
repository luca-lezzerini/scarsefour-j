import { Scontrino } from "src/app/entità/scontrino";
import { EventDashboardUno } from "./event-dashboard-uno";

export class EanEvent implements EventDashboardUno{

    codiceEan : String;
    scontrino : Scontrino = new Scontrino(); 

    constructor(codiceEan : String, scontrino : Scontrino){
        this.codiceEan = codiceEan;
        this.scontrino = scontrino;
    }
}

export class ConfermaEvent implements EventDashboardUno{}

export class StornaEvent implements EventDashboardUno{

    numElem : number;

    constructor(numElem : number){
        this.numElem = numElem;
    }
}

export class ChiudiEvent implements EventDashboardUno{}

export class VediPrezzoEvent implements EventDashboardUno{}

export class AnnullaEvent implements EventDashboardUno{}

export class AnnullaScontrinoEvent implements EventDashboardUno{}