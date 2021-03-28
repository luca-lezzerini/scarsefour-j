import { Scontrino } from "../entità/scontrino";
import { EventTre } from "./event-tre";

export class ConfermaEvent implements EventTre { }
export class ChiudiEvent implements EventTre { }
export class AnnullaEvent implements EventTre { }
export class AnnullaScontrinoEvent implements EventTre { }
export class VediPrezzoEvent implements EventTre { }
export class StornaUnoEvent implements EventTre { }
export class StornaPiuEvent implements EventTre { }
export class EanEvent implements EventTre{
    codiceEan: string;
    scontrino: Scontrino = new Scontrino();
    
    constructor(codiceEan: string, scontrino: Scontrino){
        this.codiceEan=codiceEan;
        this.scontrino=scontrino;
    }
}
