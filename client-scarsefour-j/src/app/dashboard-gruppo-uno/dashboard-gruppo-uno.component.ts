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
import { RispostaPrezzoDto } from './dto-dashboard-uno/risposta-prezzo-dto';

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

  //variabili
  barcode: string = "";
  prezzoE: number;
  descrizioneE: string;
  prezzoTot: number;
  righeScontrino : RigaScontrino[];
  scontrino = new Scontrino();



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
    this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = false;
    // this.confermaAble = false;
    // this.confermaVis = false;
    // this.annullaAble = false;
    // this.annullaVis = false;
    // this.chiudiAble = false;
    // this.chiudiVis = false;
    // this.prezzoVis = true;
  }

  goToScontrinoVuotoFromAnnulla() {
    this.eanEdit = true;
    this.vediPrezzoVis = true;
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

  goToScontrinoVuotoFromAll() {
    this.eanEdit = true;
    this.vediPrezzoVis = true;
    this.listaVis = true;
    this.stornaVis = false;
    this.annullaScontrinoAble = false;
    this.annullaScontrinoVis = true;
    this.confermaAble = false;
    this.confermaVis = false;
    this.annullaAble = false;
    this.annullaVis = false;
    this.chiudiAble = false;
    this.chiudiVis = true;
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
    let dto : RichiestaEanDto = new RichiestaEanDto();
    dto.barcode = this.barcode;
    dto.scontrino = this.scontrino;
    let oss: Observable<RispostaEanDto> = this.http.post<RispostaEanDto>(
      'http://localhost:8080/ricerca-cassiere',
      dto
    );
    oss.subscribe( r => {this.scontrino = r.scontrino;
                        this.righeScontrino = r.righeScontrino;
                        this.barcode = r.barcode;
                        this.automaD.next(new EanEvent(this.barcode,this.scontrino));
    });
  }

  chiudiScontrino() {
    console.log("timestamp :" , this.scontrino.timeStamp);
    console.log("numero scontrino : ", this.scontrino.numero);
    console.log("righe scontrino : ", this.righeScontrino);
    console.log("totale : ", this.scontrino.totale);
    this.automaD.next(new ChiudiEvent());
    //this.scontrino = new Scontrino();
  }

  vediPrezzo() {
    /*let dto : RichiestaEanDto = new RichiestaEanDto();
    dto.barcode = this.barcode;
    dto.scontrino = this.scontrino;
    let oss: Observable<RispostaPrezzoDto> = this.http.post<RispostaPrezzoDto>(
      'http://localhost:8080/vedi-prezzo-1',
      dto
    );
    oss.subscribe( p => this.prezzoE = p.prezzo);*/
    this.automaD.next(new VediPrezzoEvent());
  }

  stornaUltimo() { 
    //aggiungere chiamata Rest
    this.automaD.next(new StornaEvent(1));
  }

  annullaScontrino() { 
    this.automaD.next(new AnnullaScontrinoEvent());
  }

  conferma() { 
    //conferma AnnullaScontrino
    this.automaD.next(new ConfermaEvent());

  }

  annulla() {
    this.automaD.next(new AnnullaEvent());
   }
}
