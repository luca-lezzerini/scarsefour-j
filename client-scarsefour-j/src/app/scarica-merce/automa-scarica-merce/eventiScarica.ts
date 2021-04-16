import { PosizioneScaffale } from "src/app/entità/posizione-scaffale";
import { EventScarica } from "./eventScarica";

export class TrovaEvent implements EventScarica{
    posizione: PosizioneScaffale;
    constructor(posizione: PosizioneScaffale){
        
    }
}
export class ScaricaEvent implements EventScarica{}
export class SelezionaProdottoEvent implements EventScarica{}
export class SelezionaPosizioneEvent implements EventScarica{}
