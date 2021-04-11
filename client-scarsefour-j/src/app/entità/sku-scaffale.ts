import { PosizioneScaffale } from "./posizione-scaffale";
import { Prodotto } from "./prodotto";

export class SkuScaffale{
    id: number;
    giacenza: number;
    scortaMinima: number;
    prodotti: Prodotto[]=[];
    posizioni: PosizioneScaffale[]=[];
}