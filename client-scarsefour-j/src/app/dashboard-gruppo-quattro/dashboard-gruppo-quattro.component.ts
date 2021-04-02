import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ConfermaEvent } from '../automa/eventi';
import { ScontrinoDto } from '../dashboard-gruppo-uno/dto-dashboard-uno/scontrino-dto';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { AutomaGruppoQuattro } from './automa/automa-gruppo-quattro';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, EanEvent, StornaEvent, VediPrezzoEvent } from './automa/eventi-gruppo-quattro';
import { AutomabileGruppoQuattro } from './automa/state-gruppo-quattro';
import { EsitoRicercaDto } from './dto/esito-ricerca-dto';
import { RichiestaEanDto4 } from './dto/Richiesta-Ean-dto-4';
import { RispostaEanDto4 } from './dto/Risposta-Ean-dto-4';

@Component({
  selector: 'app-dashboard-gruppo-quattro',
  templateUrl: './dashboard-gruppo-quattro.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoQuattroComponent implements OnInit, AutomabileGruppoQuattro {

  ean: string;
  Descrizione: string;
  messaggio: string = "";
  prezzo: number;
  totale: number;
  scontrini: string;
  automa: AutomaGruppoQuattro;
  scontrino: Scontrino;
  prodotto: Prodotto;
  righeScontrino: RigaScontrino[];

  barCodeNonVisibile: boolean;
  eanNonEditabile: boolean;
  vediPrezzoVisibleB: boolean;
  chiudiB: boolean;
  stornaB: boolean;
  annullaScontrinoB: boolean;
  annullaB: boolean;
  confermaB: boolean;
  prezzoB: boolean;
  lista: boolean;
  prezzoLabel: boolean;
  totaleScontrinoLabel: boolean;
  tastoTabMes: boolean;
  esito: boolean;
  quantitaStampa:number;
  descrizioneStampa:string;
  prezzoStampa:number;

  bottoneVediPrezzo: boolean;
  bottoneStornaUltimo: boolean;
  bottoneAnnullaScontrino: boolean;
  bottoneAnnulla: boolean;
  bottoneConferma: boolean;
  bottoneChiudiScontrino: boolean;



  constructor(private http: HttpClient, private router: Router) {
  }
  vediPrezzoAction() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.ean;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>("http://localhost:8080/vedi-prezzo-quattro", dto);
    oss.subscribe(v => this.prezzo = v.prodotto.prezzo);
  }
  chiudiScontrinoAction() {
    //inviamo uno scontrino
    let dto:ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    //creiamo un osservabile
    let oss:Observable<ScontrinoDto> = this.http.post<ScontrinoDto>("http://localhost:8080/chiudi-scontrino-quattro",dto);
    oss.subscribe(s => {
      this.scontrino = s.scontrino;
      this.totale = s.scontrino.totale;
    });

    
  }
  stornaUltimoAction() {
    let dto:ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    let oss:Observable<ScontrinoDto> = this.http.post<ScontrinoDto>("http://localhost:8080/storna-ultimo-quattro",dto);
    oss.subscribe(s => {
      this.scontrino = s.scontrino;
      this.totale = s.scontrino.totale;
    });
      
      
  }
  annullaScontrinoAction() {
    let dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    let ox: Observable<ScontrinoDto> = this.http.post<ScontrinoDto>("http://localhost:8080/annulla-scontrino-quattro", dto);
    ox.subscribe(a => {this.scontrino = a.scontrino;
      this.righeScontrino = [];
     }); 
      
    
    //Mesaggio di conferma della rimozione
    this.messaggio = "tutti prodotti dello scontrino rimossi"
    //Azzeramento delle interpolazioni della tabella
  //this.quantitaStampa =0;
  //this.descrizioneStampa = "";
  //this.prezzoStampa = 0;
  this.scontrino.id
  
  }

  inserisciEan() {
    this.inserisciEanAction();
  }

  stornaAction() {

    throw new Error('Method not implemented.');

  }

  verificaEanAction() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.ean;
    let oss: Observable<EsitoRicercaDto> = this.http.post<EsitoRicercaDto>("http://localhost:8080/verifica-ean-quattro", dto);
    oss.subscribe(v => this.esito = v.esito);
  }


  ngOnInit(): void {
    this.automa = new AutomaGruppoQuattro(this);
  }

  goToScontrinoVuoto() {
    this.barCodeNonVisibile = true;
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = true;
    this.chiudiB = true;
    this.stornaB = false;
    this.annullaScontrinoB = true;
    this.annullaB = true;
    this.confermaB = true;
    this.prezzoB = false;
    this.lista = false;
    this.prezzoLabel = false;
    this.totaleScontrinoLabel = false;
    this.tastoTabMes = true;

    this.bottoneVediPrezzo = false;
    this.bottoneStornaUltimo = true;
    this.bottoneAnnullaScontrino = true;
    this.bottoneAnnulla = true;
    this.bottoneConferma = true;
    this.bottoneChiudiScontrino = true;

  }
  goToVediPrezzo() {
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = false;
    this.chiudiB = false;
    this.stornaB = false;
    this.annullaScontrinoB = false;
    this.annullaB = false;
    this.confermaB = false;
    this.prezzoB = true;
    this.lista = false;
    this.tastoTabMes = false;

    this.bottoneVediPrezzo = true;
    this.bottoneStornaUltimo = true;
    this.bottoneAnnullaScontrino = true;
    this.bottoneAnnulla = true;
    this.bottoneConferma = true;
    this.bottoneChiudiScontrino = true;

  }
  gotoScontrinoNonVuoto() {
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = true;
    this.chiudiB = true;
    this.stornaB = true;
    this.annullaScontrinoB = true;
    this.annullaB = false;
    this.confermaB = false;
    this.prezzoB = true;
    this.lista = true;
    this.tastoTabMes = true;

    this.bottoneVediPrezzo = true;
    this.bottoneStornaUltimo = true;
    this.bottoneAnnullaScontrino = true;
    this.bottoneAnnulla = true;
    this.bottoneConferma = true;
    this.bottoneChiudiScontrino = true;

  }
  gotoAnnullamentoScontrino() {
    this.barCodeNonVisibile = true;
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = false;
    this.chiudiB = false;
    this.stornaB = false;
    this.annullaScontrinoB = true;
    this.annullaB = true;
    this.confermaB = true;
    this.prezzoB = false;
    this.lista = false;
    this.tastoTabMes = false;

    this.bottoneVediPrezzo = true;
    this.bottoneStornaUltimo = true;
    this.bottoneAnnullaScontrino = true;
    this.bottoneAnnulla = true;
    this.bottoneConferma = true;
    this.bottoneChiudiScontrino = true;

  }

  vediPrezzo() {
    this.automa.next(new VediPrezzoEvent());
  }


  chiudiScontrino() {
    this.automa.next(new ChiudiEvent());

  }

  stornaUltimo() {

    this.automa.next(new StornaEvent(1));

  }

  annullaScontrino() {
    this.automa.next(new AnnullaScontrinoEvent());

  }

  annulla() {
    this.automa.next(new AnnullaEvent());

  }

  conferma() {
    this.automa.next(new ConfermaEvent());
  }

  inserisciEanAction() {
    let dto: RichiestaEanDto4 = new RichiestaEanDto4();
    dto.barcode = this.ean;
    dto.scontrino = this.scontrino;
    let oss: Observable<RispostaEanDto4> = this.http.post<RispostaEanDto4>("http://localhost:8080/inserisci-ean-quattro", dto);
    oss.subscribe(e => {
      if (e.rigaSuccesso) {
        this.scontrino = e.scontrino;
        this.righeScontrino = e.righeScontrino;
        this.automa.next(new EanEvent(this.ean, this.scontrino));
        //dobbiamo inserire il totale???
      } else {
        // l'automa rimane nello stesso stato
        this.messaggio = "ean inesistente";
      }
      this.ean = ""
    });
    //Puliamo l'input ean
    // this.automa.next(new EanEvent(this.ean, this.scontrino));
  }
}


