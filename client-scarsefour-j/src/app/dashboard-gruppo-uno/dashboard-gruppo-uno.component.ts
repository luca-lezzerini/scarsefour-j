import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RichiestaEanDto } from './dto-dashboard-uno/richiesta-ean-dto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { AutomaDashboardUno } from './automa-dashboard-uno/automa-dashboard-uno';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaEvent, VediPrezzoEvent } from './automa-dashboard-uno/eventi-dashboard-uno';
import { AutomabileDashboardUno } from './automa-dashboard-uno/state-dashboard-uno';
import { RispostaEanDto } from './dto-dashboard-uno/risposta-ean-dto';
import { PrezzoDto } from './dto-dashboard-uno/prezzo-dto';
import { ScontrinoDto } from './dto-dashboard-uno/scontrino-dto';
import { EanDto } from './dto-dashboard-uno/ean-dto';
import { ScontrinoRigheDto } from './dto-dashboard-uno/scontrino-righe-dto';

@Component({
  selector: 'app-dashboard-gruppo-uno',
  templateUrl: './dashboard-gruppo-uno.component.html',
  styleUrls: ['./dashboard-gruppo-uno.component.css']
})
export class DashboardGruppoUnoComponent implements OnInit, AutomabileDashboardUno {

  automaD: AutomaDashboardUno;


  //variabili di visualizzazione
  eanEdit: boolean = false;
  vediPrezzoVis: boolean = false;
  listaVis: boolean = false;
  stornaVis: boolean = false;
  annullaScontrinoAble: boolean = false;
  annullaScontrinoVis: boolean = false;
  confermaAble: boolean = false;
  confermaVis: boolean = false;
  annullaAble: boolean = false;
  annullaVis: boolean = false;
  chiudiAble: boolean = false;
  chiudiVis: boolean = false;
  prezzoVis: boolean = false;
  prezzoRVis: boolean = false;

  //variabili
  barcode: string = "";
  prezzoR: number;
  prezzoE: number;
  descrizioneE: string;
  prezzoTot: number;
  righeScontrino: RigaScontrino[];
  scontrino : Scontrino;
  blurVar: boolean = true;


  constructor(private http: HttpClient, private router: Router) {
    this.automaD = new AutomaDashboardUno(this);
  }


  //metodi Automabile
  goToScontrinoVuotoInitial() {
    this.eanEdit = true;
    this.vediPrezzoVis = true;
    this.listaVis = false;
    this.stornaVis = false;
    this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = true;
    this.confermaAble = false;
    this.confermaVis = true;
    this.annullaAble = false;
    this.annullaVis = true;
    this.chiudiAble = false;
    this.chiudiVis = true;
    this.prezzoVis = false;
  }

  goToScontrinoVuotoPrimoEan() {
    // this.eanEdit = true;
    // this.vediPrezzoVis = true;
    // this.listaVis = false;
    // this.stornaVis = false;
    //this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = false;
    // this.confermaAble = false;
    this.confermaVis = false;
    // this.annullaAble = false;
    this.annullaVis = false;
    // this.chiudiAble = false;
    this.chiudiVis = false;
    this.prezzoVis = true;
  }

  goToScontrinoVuotoFromAnnulla() {
    //this.eanEdit = true;
    //this.vediPrezzoVis = true;
    //this.listaVis = false;
    //this.stornaVis = false;
    //this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = false;
    //this.confermaAble = false;
    this.confermaVis = false;
    //this.annullaAble = false;
    this.annullaVis = false;
    //this.chiudiAble = false;
    this.chiudiVis = false;
    //this.prezzoVis = false;
  }

  goToScontrinoVuotoFromAll() {
    //this.eanEdit = true;
    //this.vediPrezzoVis = true;
    this.listaVis = true;
    this.stornaVis = false;
    this.annullaScontrinoAble = false;
    //this.annullaScontrinoVis = true;
    //this.confermaAble = false;
    this.confermaVis = false;
    //this.annullaAble = false;
    this.annullaVis = false;
    this.chiudiAble = false;
    //this.chiudiVis = true;
    this.prezzoVis = true;
  }

  goToVediPrezzo() {
    this.eanEdit = true;
    this.vediPrezzoVis = false;
    this.listaVis = false;
    this.stornaVis = false;
    this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = false;
    this.confermaAble = false;
    this.confermaVis = false;
    this.annullaAble = false;
    this.annullaVis = false;
    this.chiudiAble = false;
    this.chiudiVis = false;
    this.prezzoVis = false;
  }

  goToScontrinoNonVuoto() {
    this.eanEdit = true;
    this.vediPrezzoVis = true;
    this.listaVis = true;
    this.stornaVis = true;
    this.annullaScontrinoAble = true;
    this.annullaScontrinoVis = true;
    this.confermaAble = false;
    this.confermaVis = false;
    this.annullaAble = false;
    this.annullaVis = false;
    this.chiudiAble = true;
    this.chiudiVis = true;
    this.prezzoVis = true;
  }

  goToAnnullamentoScontrino() {
    this.eanEdit = false;
    this.vediPrezzoVis = false;
    this.listaVis = false;
    this.stornaVis = false;
    this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = false;
    this.confermaAble = true;
    this.confermaVis = true;
    this.annullaAble = true;
    this.annullaVis = true;
    this.chiudiAble = false;
    this.chiudiVis = false;
    this.prezzoVis = false;
  }

  ngOnInit(): void {
  }

  vaiAHome() {
    this.router.navigateByUrl('home-page');
  }

  cercaEan() {
    let dto: RichiestaEanDto = new RichiestaEanDto();
    dto.barcode = this.barcode;
    dto.scontrino = this.scontrino;
    let oss: Observable<RispostaEanDto> = this.http.post<RispostaEanDto>(
      'http://localhost:8080/cerca-ean-1',
      dto
    );
    oss.subscribe(r => {
      this.scontrino = r.scontrino;
      this.righeScontrino = r.righeScontrino;
      this.barcode = r.barcode;
      this.automaD.next(new EanEvent(this.barcode, this.scontrino));
      if (r.scontrino != null) {
        this.prezzoTot = r.scontrino.totale;
        this.prezzoE = r.righeScontrino[r.righeScontrino.length-1].prodotto.prezzo;
        this.descrizioneE = r.righeScontrino[r.righeScontrino.length-1].prodotto.descrizione;        
      }
      this.barcode = "";
    });
    this.prezzoRVis = false;
  }

  chiudiScontrino() {
    console.log("timestamp :", this.scontrino.timeStamp);
    console.log("numero scontrino : ", this.scontrino.numero);
    console.log("righe scontrino : ", this.righeScontrino);
    console.log("totale : ", this.scontrino.totale);
    let dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    let oss: Observable<ScontrinoDto> = this.http.post<ScontrinoDto>(
      'http://localhost:8080/chiudi-scontrino-1',
      dto
    );
    oss.subscribe(c => {
      this.scontrino = c.scontrino;
      this.righeScontrino = [];
      this.prezzoTot = null;
      this.prezzoE = null;
      this.descrizioneE = null;
    });
    this.automaD.next(new ChiudiEvent());
  }

  vediPrezzo() {
    let dto: EanDto = new EanDto();
    dto.barcode = this.barcode;
    let oss: Observable<PrezzoDto> = this.http.post<PrezzoDto>(
      'http://localhost:8080/vedi-prezzo-1',
      dto
    );
    oss.subscribe(p => this.prezzoR = p.prezzo);
    this.blurVar = true;
    this.automaD.next(new EanEvent(this.barcode, this.scontrino));
  }

  TastoVediPrezzo(){
    this.automaD.next(new VediPrezzoEvent());
    this.blurVar = false;
    this.prezzoRVis = true;
    this.prezzoR = null;
  }

  stornaUltimo() {
    let dto: ScontrinoRigheDto = new ScontrinoRigheDto();
    dto.scontrino = this.scontrino;
    dto.righeScontrino = this.righeScontrino;
    let oss: Observable<ScontrinoRigheDto> = this.http.post<ScontrinoRigheDto>(
      'http://localhost:8080/storna-ultimo-1',
      dto
    )
    oss.subscribe(s => {
      this.scontrino = s.scontrino;
      this.righeScontrino = s.righeScontrino;
      this.prezzoTot = s.scontrino.totale;
      this.prezzoE = s.righeScontrino[s.righeScontrino.length-1].prodotto.prezzo;
      this.descrizioneE = s.righeScontrino[s.righeScontrino.length-1].prodotto.descrizione;
    });
    this.automaD.next(new StornaEvent(this.righeScontrino.length));
  }

  annullaScontrino() {
    this.automaD.next(new AnnullaScontrinoEvent());
  }

  conferma() {
    //conferma AnnullaScontrino
    let dto: ScontrinoRigheDto = new ScontrinoRigheDto();
    dto.scontrino = this.scontrino;
    dto.righeScontrino = this.righeScontrino;
    let oss: Observable<ScontrinoDto> = this.http.post<ScontrinoDto>(
      'http://localhost:8080/annulla-scontrino-1',
      dto
    );
    oss.subscribe(s => {
      this.scontrino = s.scontrino
      this.righeScontrino = [];
      this.prezzoTot = null;
      this.prezzoE = null;
      this.descrizioneE = null;
    });
    this.automaD.next(new ConfermaEvent());

  }

  annulla() {
    this.automaD.next(new AnnullaEvent());
  }
}
