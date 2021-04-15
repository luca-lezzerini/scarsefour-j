import { PosizioneScaffale } from "../entità/posizione-scaffale";

export class Page{
    content: PosizioneScaffale[];
    totalPages : number;
    totalElements: number;
    last: boolean;
    size: number;
    number : number;
    sort: boolean;
    first:boolean;
    numberOfElements: number;
}