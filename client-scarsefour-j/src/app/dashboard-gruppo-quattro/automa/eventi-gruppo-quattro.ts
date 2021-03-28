import { Scontrino } from "../../entità/scontrino";
import { EventGruppoQuattro } from "./event-gruppo-quattro";

export class VediPrezzoEvent implements EventGruppoQuattro{}
export class EanEvent implements EventGruppoQuattro{
    codiceEan:string;
    scontrino:Scontrino = new Scontrino();

    constructor(codiceEan:string, scontrino:Scontrino){
        this.codiceEan = codiceEan;
        this.scontrino = scontrino;
    }
}
export class ChiudiEvent implements EventGruppoQuattro{}
export class StornaEvent implements EventGruppoQuattro{
    element:number;

    constructor(element:number){
        this.element = element;
    }
}
export class ConfermaEvent implements EventGruppoQuattro{}
export class AnnullaEvent implements EventGruppoQuattro{}
export class AnnullaScontrinoEvent implements EventGruppoQuattro{}
