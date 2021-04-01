import {HttpClient} from '@angular/common/http';
import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {EanDto} from '../dashboard-gruppo-uno/dto-dashboard-uno/ean-dto';
import {Prodotto} from '../entità/prodotto';
import {RigaScontrino} from '../entità/riga-scontrino';
import {Scontrino} from '../entità/scontrino';
import {Automa2} from './automa-gruppo-due';
import {AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, VediPrezzoEvent} from './eventi2';
import {AutomabileDue} from './state2';
import {PrezzoDto} from '../dashboard-gruppo-uno/dto-dashboard-uno/prezzo-dto';
import { BarcodeDto } from '../dto/barcode-dto';
import { AggiungiDto } from '../dto/aggiungi-dto';
import { ScontrinoDto } from '../dto/scontrino-dto';

@Component({
  selector: 'app-dashboard-gruppo-due',
  templateUrl: './dashboard-gruppo-due.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoDueComponent implements OnInit, AutomabileDue {
  automa: Automa2;
  scontrino: Scontrino = new Scontrino();
  prezzo: number;
  ultimoElemento: Prodotto;
  barcode: string;
  prodotto: Prodotto = new Prodotto();
  righeScontrino: Array<RigaScontrino>;
  esito: Boolean;
  // variabili booleane
  eanEditabile = false;
  vediPrezzoVisibile = false;
  listaVisibile = false;
  stornaVisibile = false;
  annullaScontrinoVisibile = false;
  confermaVisibile = false;
  annullaVisibile = false;
  chiudiScontrinoVisibile = false;
  annullaConfermaDisabled = false;
  annullaScontrinoDisabled = false;

  constructor(private http: HttpClient) {
    this.automa = new Automa2(this);
  }

  goToScontrinoVuoto(): void {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = true;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile = true;
    this.annullaScontrinoDisabled = true;
    this.confermaVisibile = true;
    this.annullaConfermaDisabled = true;
    this.chiudiScontrinoVisibile = false;
  }

  goToScontrinoNonVuoto(): void {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = true;
    this.listaVisibile = true;
    this.stornaVisibile = true;
    this.annullaScontrinoVisibile = true;
    this.annullaScontrinoDisabled = false;
    this.chiudiScontrinoVisibile = true;
    this.confermaVisibile = false;
    this.annullaVisibile = false;
  }

  goToAnnullamentoScontrino(): void {
    this.confermaVisibile = true;
    this.annullaVisibile = true;
    this.eanEditabile = false;
    this.vediPrezzoVisibile = false;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile = false;
    this.chiudiScontrinoVisibile = false;
    this.annullaConfermaDisabled = false;
  }

  goToVediPrezzo(): void {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = false;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile = false;
    this.confermaVisibile = false;
    this.annullaVisibile = false;
    this.chiudiScontrinoVisibile = false;
  }

  ngOnInit(): void {
  }

  annullaScontrino(): void {
    this.automa.next(new AnnullaScontrinoEvent());
  }

  vediPrezzo(): void {
    this.automa.next(new VediPrezzoEvent());
  }

  chiudiScontrino(): void {
    this.automa.next(new ChiudiEvent());
  }

  stornaUltimoAction(): void {
    const dto: ScontrinoDto = new ScontrinoDto;
    dto.scontrino = this.scontrino;
    const oss: Observable<ScontrinoDto> = this.http
    .post<ScontrinoDto>('http://localhost:8080/storna-ultimo-due', dto);
  oss.subscribe(t => this.scontrino = t.scontrino);
  }

  annulla(): void {
    this.automa.next(new AnnullaEvent());
  }

  conferma(): void {
    this.automa.next(new ConfermaEvent());
  }

  EventoEan(): void {
    this.automa.next(new EanEvent());
  }

  aggiungiProdottoAction(): void {
    const dto: BarcodeDto = new BarcodeDto();
    dto.barcode = this.barcode;
    dto.scontrino = this.scontrino;
    const oss: Observable<AggiungiDto> = this.http
    .post<AggiungiDto>('http://localhost:8080/aggiungi-due', dto);
  oss.subscribe(t => {
    this.scontrino = t.scontrino;
    this.esito = t.esito;
  });
  }

  annullaScontrinoAction(): void {
    const dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    const oss: Observable<ScontrinoDto> = this.http
    .post<ScontrinoDto>('http://localhost:8080/annulla-scontrino-due', dto);
  oss.subscribe(t => this.scontrino = t.scontrino);
  }

  chiudiScontrinoAction(): void {
    const dto: ScontrinoDto = new ScontrinoDto;
    dto.scontrino = this.scontrino;
    const oss: Observable<ScontrinoDto> = this.http
    .post<ScontrinoDto>('http://localhost:8080/chiudi-scontrino-due', dto);
  oss.subscribe(t => this.scontrino = t.scontrino);
  }

  vediPrezzoAction(): void {
    const dto: EanDto = new EanDto();
    console.log('Codice: ' + this.barcode);
    dto.barcode = this.barcode;
    const oss: Observable<PrezzoDto> = this.http
      .post<PrezzoDto>('http://localhost:8080/vedi-prezzo-due', dto);
    oss.subscribe(t => this.prezzo = t.prezzo);
  }
}
