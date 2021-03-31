import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EanDto } from '../dashboard-gruppo-uno/dto-dashboard-uno/ean-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { ScontrinoDto } from '../dto/scontrino-dto';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { Automa2 } from './automa-gruppo-due';
import { VediPrezzoEvent } from './eventi2';
import { AutomabileDue } from './state2';

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
  // variabili booleane
  eanEditabile = false;
  vediPrezzoVisibile = false;
  listaVisibile = false;
  stornaVisibile = false;
  annullaScontrinoVisibile = false;
  confermaVisibile = false;
  annullaVisibile = false;
  chiudiScontrinoVisibile = false;

  constructor(private http: HttpClient) { 
    this.automa = new Automa2(this);
  }
  vediPrezzoAction() {
    const dto: EanDto = new EanDto();
    console.log("Codice: " + this.barcode);
    dto.barcode = this.barcode;
    const oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>
      ('http://localhost:8080/vedi-prezzo-due', dto);
    oss.subscribe(t => {
      this.prodotto = t.prodotto;
      this.prezzo = t.prodotto.prezzo;
    });
  }
  goToScontrinoVuoto() {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = true;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile= false;
    this.confermaVisibile = false;
    this.chiudiScontrinoVisibile = false;
  }
  goToScontrinoNonVuoto() {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = true;
    this.listaVisibile = true;
    this.stornaVisibile = true;
    this.annullaScontrinoVisibile = true;
    this.chiudiScontrinoVisibile = true;
    this.confermaVisibile = false;
    this.annullaVisibile = false;
  }
  goToAnnullamentoScontrino() {
    this.confermaVisibile = true;
    this.annullaVisibile = true;
    this.eanEditabile = false;
    this.vediPrezzoVisibile = false;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile =false;
    this.chiudiScontrinoVisibile = false;
  }
  goToVediPrezzo() {
    this.eanEditabile = true;
    this.vediPrezzoVisibile = false;
    this.listaVisibile = false;
    this.stornaVisibile = false;
    this.annullaScontrinoVisibile= false;
    this.confermaVisibile = false;
    this.annullaVisibile = false;
    this.chiudiScontrinoVisibile = false;
  }

  ngOnInit(): void {
  }

  annullaScontrino() {
    const dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;

  }

  vediPrezzo() {
    this.automa.next(new VediPrezzoEvent());
  }

  chiudiScontrino() { }

  stornaUltimo() { }

  annulla() { }

  conferma() { }

}
