import { RigaScontrino } from "src/app/entità/riga-scontrino";
import { Scontrino } from "src/app/entità/scontrino";

export class RispostaEanDto4 {
    rigaSuccesso: boolean = false;
    scontrino:Scontrino;
    righeScontrino: RigaScontrino[];
}