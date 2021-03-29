import { RigaScontrino } from "src/app/entità/riga-scontrino";
import { Scontrino } from "src/app/entità/scontrino";


export class RispostaEanDto{
    barcode : string;
    scontrino : Scontrino;
    righeScontrino : RigaScontrino[];
}