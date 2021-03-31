import { timeStamp } from "console";
import { EventDashboardDue } from "./event2";

export class ConfermaEvent implements EventDashboardDue{}
export class AnnullaEvent implements EventDashboardDue{}
export class VediPrezzoEvent implements EventDashboardDue{}
export class AnnullaScontrinoEvent implements EventDashboardDue{}
export class EanEvent implements EventDashboardDue{
    esito: Boolean;
    numeroRighe: Number;
}
export class StornaUltimoEvent implements EventDashboardDue{
    numeroRighe: Number;
}
export class ChiudiEvent implements EventDashboardDue{}
export class AggiungiAction implements EventDashboardDue{}
export class VediPrezzoAction implements EventDashboardDue{}





