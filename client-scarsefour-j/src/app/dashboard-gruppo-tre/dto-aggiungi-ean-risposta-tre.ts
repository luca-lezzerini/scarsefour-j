import { RigaScontrino } from "../entità/riga-scontrino";
import { Scontrino } from "../entità/scontrino";

export class DtoAggiungiEanRispostaTre{
    scontrino: Scontrino;
    esito: boolean;
    righe: RigaScontrino[] = [];
}