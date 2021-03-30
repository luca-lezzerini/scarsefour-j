import { RigaScontrino } from "./riga-scontrino";

export class Scontrino{
    id: number;
    timeStamp: Date;
    numero: number;
    totale: number;
    righe: RigaScontrino[] = [];
}