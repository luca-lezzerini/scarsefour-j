import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { AutomaTre } from './automa-tre';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaPiuEvent, StornaUnoEvent, VediPrezzoEvent } from './eventi-tre';
import { AutomabileTre, StateTre } from './state-tre';
import { ScontrinoNonVuotoState, ScontrinoVuotoState, VediPrezzoState } from './stati-tre';
import { Observable } from 'rxjs';
import { DtoScontrinoTre } from './dto-scontrino-tre';
import { DtoListaRigaScontrinoTre } from './dto-lista-riga-scontrino-tre';
import { isNull } from '@angular/compiler/src/output/output_ast';
import { DtoAggiungiEanTre } from './dto-aggiungi-ean-tre';
import { DtoAggiungiEanRispostaTre } from './dto-aggiungi-ean-risposta-tre';

@Component({
  selector: 'app-dashboard-gruppo-tre',
  templateUrl: './dashboard-gruppo-tre.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoTreComponent implements OnInit, AutomabileTre {

  barcode: string;
  // riportare l'ultimo elemento della tabella, solo descrizione e prezzo
  ultimoElemento: string;
  prodotto: Prodotto = new Prodotto();
  righe: RigaScontrino[] = [];
  scontrino: Scontrino = new Scontrino();
  rigaScontrino: RigaScontrino;
  prezzoProdotto: number;
  inputDisabled: boolean;
  prezzoDisabled: boolean = false;
  chiudiDisabled: boolean;
  //prezzoVisible e stornaVisible vogliono false per apparire e true per sparire
  prezzoVisible: boolean;
  stornaVisible: boolean;
  annullaScontrinoDisabled: boolean;
  annullaDisabled: boolean;
  confermaDisabled: boolean;
  listaVisible: boolean;
  messErroreVisible: boolean;
  automa: AutomaTre;
  stato: StateTre;
  messaggioErrore: string = "Ean Sconosciuto!";
  totale: number;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.automa = new AutomaTre(this);
  }

  //Metodi Automabile GUI
  goToScontrinoVuoto() {
    this.inputDisabled = false;
    this.prezzoDisabled = false;
    this.chiudiDisabled = true;
    this.prezzoVisible = true;
    this.stornaVisible = true;
    this.annullaScontrinoDisabled = true;
    this.annullaDisabled = true;
    this.confermaDisabled = true;
    this.listaVisible = false;
    this.messErroreVisible = true;
  }

  goToScontrinoNonVuoto() {
    this.inputDisabled = false;
    this.prezzoDisabled = false;
    this.chiudiDisabled = false;
    this.prezzoVisible = false;
    this.stornaVisible = false;
    this.annullaScontrinoDisabled = false;
    this.annullaDisabled = true;
    this.confermaDisabled = true;
    this.listaVisible = false;
    this.messErroreVisible = true;
  }

  goToVediPrezzo() {
    this.inputDisabled = false;
    this.prezzoDisabled = true;
    this.chiudiDisabled = true;
    this.prezzoVisible = true;
    this.stornaVisible = true;
    this.annullaScontrinoDisabled = true;
    this.annullaDisabled = true;
    this.confermaDisabled = true;
    this.listaVisible = true;
    this.messErroreVisible = true;
  }

  goToAnnullamentoScontrino() {
    this.inputDisabled = true;
    this.prezzoDisabled = true;
    this.chiudiDisabled = true;
    this.prezzoVisible = true;
    this.stornaVisible = true;
    this.annullaScontrinoDisabled = true;
    this.annullaDisabled = false;
    this.confermaDisabled = false;
    this.listaVisible = true;
    this.messErroreVisible = true;
  }

  // Metodi Automabile Action
  aggiungiRigaScontrinoAction() {
    // Creiamo DtoAggiungiEan da inviare al server
    let dto: DtoAggiungiEanTre = new DtoAggiungiEanTre();
    dto.scontrino = this.scontrino;
    dto.barcode = this.barcode;
    dto.righeScontrino = this.righe;
    let oss: Observable<DtoAggiungiEanRispostaTre> = this.http.post<DtoAggiungiEanRispostaTre>
      ('http://localhost:8080/aggiungi-scontrino-tre', dto);
    oss.subscribe(t => {
      console.log("Esito: " + t.esito);
      this.scontrino = t.scontrino;
      this.righe = t.righe
      this.totale = t.scontrino.totale
    });
    this.barcode = "";
  }

  annullaScontrinoAction() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/annulla-scontrino-tre', dto);
    oss.subscribe(t => this.scontrino = t.scontrino);
    this.totale = 0;
  }

  vediPrezzoAction() {
    //deve solo visualizzare il prezzo
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    console.log("BARCODE: " + this.barcode);
    dto.criterio = this.barcode;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>
      ('http://localhost:8080/vedi-prezzo-tre', dto);
    oss.subscribe(t => {
      this.prodotto.id = t.prodotto.id;
      this.prodotto.prezzo = t.prodotto.prezzo;
      this.prodotto.codice = t.prodotto.codice;
      this.prodotto.descrizione = t.prodotto.descrizione;
      console.log(this.prodotto.descrizione);
      this.prezzoProdotto = this.prodotto.prezzo;
      if (this.prodotto.descrizione == null) {
        this.erroreEanAction();
        console.log("ERRORE!");
      }
    });
  }

  erroreEanAction() {
    this.messErroreVisible = false;
    this.prezzoVisible = true;
  }

  // Metodi azione sui button
  onKey(event: any) {
    this.prezzoDisabled = false;
    this.stato = this.automa.next(new EanEvent(this.barcode, this.scontrino));
  }

  attivaVediPrezzo() {
    this.stato = this.automa.next(new VediPrezzoEvent());
  }

  chiudiScontrino() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/chiudi-scontrino-tre', dto);
    oss.subscribe(t => this.scontrino = t.scontrino);
    this.stato = this.automa.next(new ChiudiEvent());
    this.totale = 0;
    this.righe = [];
  }

  stornaUltimo() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/storna-ultimo-tre', dto);
    oss.subscribe(t => {
      this.scontrino = t.scontrino;
      this.righe = t.righeScontrino;
      this.totale = t.scontrino.totale;
    });
    //dopo aver stornato un prodotto se la lista è vuota torna a scontrino vuoto altrimenti a scontrino non vuoto
    if (this.righe.length == 0) {
      this.stato = this.automa.next(new StornaUnoEvent());
    } else {
      this.stato = this.automa.next(new StornaPiuEvent());
    }
  }

  annullaScontrino() {
    this.stato = this.automa.next(new AnnullaScontrinoEvent());
  }

  annulla() {
    this.stato = this.automa.next(new AnnullaEvent());
  }

  conferma() {
    this.stato = this.automa.next(new ConfermaEvent());
    this.righe = [];
  }

  // Metodi richiamati internamente
  aggiornaRighe() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/aggiorna-righe-tre', dto);
    oss.subscribe(r => this.righe = r.scontrino.righe);
  }
}
