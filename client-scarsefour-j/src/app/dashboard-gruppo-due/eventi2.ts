import { EventDashboardDue } from './event2';

export class ConfermaEvent implements EventDashboardDue{}
export class AnnullaEvent implements EventDashboardDue{}
export class VediPrezzoEvent implements EventDashboardDue{}
export class AnnullaScontrinoEvent implements EventDashboardDue{}
export class EanEvent implements EventDashboardDue{
    esito: boolean;
    numeroRighe: number;
    constructor(esito: boolean, numeroRighe: number) {
      this.esito = esito;
      this.numeroRighe = numeroRighe;
    }
}
export class StornaUltimoEvent implements EventDashboardDue{
    numeroRighe: number;
    constructor(numeroRighe: number) {
      this.numeroRighe = numeroRighe;
    }
}
export class ChiudiEvent implements EventDashboardDue{}
export class AggiungiAction implements EventDashboardDue{}
export class VediPrezzoAction implements EventDashboardDue{}





