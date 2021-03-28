import { Automabile } from "../../automa/state";
import { EventGruppoQuattro } from "./event-gruppo-quattro";
import { AutomabileGruppoQuattro, StateGruppoQuattro } from "./state-gruppo-quattro";

export class AutomaGruppoQuattro implements StateGruppoQuattro {
    next(event: EventGruppoQuattro)
    {
        throw new Error("Method not implemented.");
    }
    gui:AutomabileGruppoQuattro;
    stato:StateGruppoQuattro

    constructor(autom: AutomaGruppoQuattro){
        
    }

}