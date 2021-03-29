import { RigaScontrino } from "src/app/entità/riga-scontrino";
import { Scontrino } from "src/app/entità/scontrino";

export class ScontrinoRigheDto{
    scontrino : Scontrino;
    righeScontrino : RigaScontrino[];
}