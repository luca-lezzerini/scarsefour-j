import { RigaScontrino } from "../entità/riga-scontrino";
import { Scontrino } from "../entità/scontrino";

export class DtoScontrinoTre{
    scontrino: Scontrino;
    righeScontrino: RigaScontrino[] = [];
}