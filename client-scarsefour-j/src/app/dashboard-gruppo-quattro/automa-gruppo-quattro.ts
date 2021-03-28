import { EventGruppoQuattro } from "./event-gruppo-quattro";
import { StateGruppoQuattro } from "./state-gruppo-quattro";

export class AutomaGruppoQuattro implements StateGruppoQuattro {
    next(event: EventGruppoQuattro) {
        throw new Error("Method not implemented.");
    }

}