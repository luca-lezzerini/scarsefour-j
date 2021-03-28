import { timeStamp } from "console";
import { Scontrino } from "../entità/scontrino";
import { EventDashboardDue } from "./event2";

export class ConfermaEvent implements EventDashboardDue{}
export class AnnullaEvent implements EventDashboardDue{}
export class VediPrezzoEvent implements EventDashboardDue{}
export class AnnullaScontrinoEvent implements EventDashboardDue{}
export class EanEvent implements EventDashboardDue{
    codiceEan: String;
    scontrino : Scontrino = new Scontrino();

    constructor (codiceEan: String, scontrino: Scontrino){
        this.codiceEan = codiceEan;
        this.scontrino = scontrino;
    }
}
export class StornaUltimoEvent implements EventDashboardDue{
    quantita: Number;

    constructor(quantita : Number){
        this.quantita = quantita;
    }
}
export class ChiudiEvent implements EventDashboardDue{}





