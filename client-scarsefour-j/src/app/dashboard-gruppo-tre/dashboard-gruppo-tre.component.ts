import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { AutomaTre } from './automa-tre';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaUnoEvent, VediPrezzoEvent } from './eventi-tre';
import { AutomabileTre, StateTre } from './state-tre';
import { ScontrinoNonVuotoState, ScontrinoVuotoState, VediPrezzoState } from './stati-tre';
import { Observable } from 'rxjs';
import { DtoScontrinoTre } from './dto-scontrino-tre';
import { DtoListaRigaScontrinoTre } from './dto-lista-riga-scontrino-tre';

@Component({
  selector: 'app-dashboard-gruppo-tre',
  templateUrl: './dashboard-gruppo-tre.component.html',
  styleUrls: ['./dashboard-gruppo-tre.component.css']
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
  inputEnabled: boolean;
  prezzoEnabled: boolean = false;
  chiudiEnabled: boolean;
  //prezzoVisible e stornaVisible vogliono false per apparire e true per sparire
  prezzoVisible: boolean;
  stornaVisible: boolean;
  annullaScontrinoEnabled: boolean;
  annullaEnabled: boolean;
  confermaEnabled: boolean;
  listaVisible: boolean;
  automa: AutomaTre;
  stato: StateTre;

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.automa = new AutomaTre(this);
  }

  goToScontrinoVuoto() {
    this.inputEnabled = true;
    this.prezzoEnabled = false;
    this.chiudiEnabled = false;
    this.prezzoVisible = true;
    this.stornaVisible = true;
    this.annullaScontrinoEnabled = false;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = false;
  }

  goToScontrinoNonVuoto() {
    this.inputEnabled = true;
    this.prezzoEnabled = true;
    this.chiudiEnabled = true;
    this.prezzoVisible = false;
    this.stornaVisible = false;
    this.annullaScontrinoEnabled = true;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = true;
  }

  goToVediPrezzo() {
    this.inputEnabled = true;
    this.prezzoEnabled = true;
    this.chiudiEnabled = false;
    this.prezzoVisible = false;
    this.stornaVisible = true;
    this.annullaScontrinoEnabled = false;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = false;
  }


  goToAnnullamentoScontrino() {
    this.inputEnabled = true;
    this.prezzoEnabled = true;
    this.chiudiEnabled = false;
    this.prezzoVisible = false;
    this.stornaVisible = true;
    this.annullaScontrinoEnabled = false;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = false;
  }

  onKey(event: any) {
    this.prezzoEnabled = false;
    this.vediPrezzo();
    this.stato = this.automa.next(new EanEvent(this.barcode, this.scontrino));
  }

  attivaVediPrezzo(){
    this.stato = this.automa.next(new VediPrezzoEvent());
  }

  vediPrezzo() {
    //deve solo visualizzare il prezzo
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    console.log("BARCODE: " + this.barcode);
    dto.criterio = this.barcode;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>
      ('http://localhost:8080/vedi-prezzo-tre', dto);
    oss.subscribe(t => this.prodotto = t.prodotto);
    this.prezzoProdotto = this.prodotto.prezzo;
  }
    
  aggiungiRigaScontrino(){
    //deve visualizzare prezzo e salvare
    //aggiungi-scontrino-tre
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/aggiungi-scontrino-tre', dto);
    oss.subscribe(t => this.scontrino = t.scontrino);
  }
    

  chiudiScontrino() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/chiudi-scontrino-tre', dto);
    oss.subscribe(t => this.scontrino = t.scontrino);
    this.stato = this.automa.next(new ChiudiEvent());
  }

  stornaUltimo() {
    this.stato = this.automa.next(new ConfermaEvent());
  }
  annullaScontrino() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoScontrinoTre> = this.http.post<DtoScontrinoTre>
      ('http://localhost:8080/annulla-scontrino-tre', dto);
    oss.subscribe(t => this.scontrino = t.scontrino);
    this.stato = this.automa.next(new AnnullaScontrinoEvent());
  }
  annulla() {
    this.stato = this.automa.next(new AnnullaEvent());
  }
  conferma() {
    let dto: DtoScontrinoTre = new DtoScontrinoTre();
    dto.scontrino = this.scontrino;
    let oss: Observable<DtoListaRigaScontrinoTre> = this.http.post<DtoListaRigaScontrinoTre>
      ('http://localhost:8080/storna-ultimo-tre', dto);
    oss.subscribe(t => this.righe = t.righeScontrino);
    this.stato = this.automa.next(new StornaUnoEvent());
  }
}
