import { PosizioneScaffale } from "../entità/posizione-scaffale";
import { Prodotto } from "../entità/prodotto";

export class CaricaMerceDto{
    prodotto: Prodotto;
    posizioneScaffale : PosizioneScaffale;
    quantita : number;

}