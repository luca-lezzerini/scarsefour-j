import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ConfermaEvent } from '../automa/eventi';
import { Prodotto } from '../entità/prodotto';
import { Scontrino } from '../entità/scontrino';
import { AutomaGruppoQuattro } from './automa/automa-gruppo-quattro';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, EanEvent, StornaEvent, VediPrezzoEvent } from './automa/eventi-gruppo-quattro';

@Component({
  selector: 'app-dashboard-gruppo-quattro',
  templateUrl: './dashboard-gruppo-quattro.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoQuattroComponent implements OnInit {

  ean: string;
  Descrizione: string;
  prezzo: string;
  totale: string;
  scontrini: string;
  automa: AutomaGruppoQuattro;
  scontrino:Scontrino;
  prodotto:Prodotto;

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
  totaleScontrinoLabel:boolean;
  tastoTabMes:boolean;
  constructor(http: HttpClient, private router: Router) {
   }

  ngOnInit(): void {
    this.automa = new AutomaGruppoQuattro(this);
  }

  goToScontrinoVuoto() {
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = true;
    this.chiudiB = false
    this.stornaB = false
    this.annullaScontrinoB = false
    this.annullaB = false;
    this.confermaB = false;
    this.prezzoB = false;
    this.lista = false;
    this.prezzoLabel = false;
    this.totaleScontrinoLabel=false;
    this.tastoTabMes=true;
  }
  goToVediPrezzo() {
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = false;
    this.chiudiB = false;
    this.stornaB = false;
    this.annullaScontrinoB = false;
    this.annullaB = false;
    this.confermaB = false;
    this.prezzoB = false;
    this.lista = false;
    this.tastoTabMes=false;
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
    this.tastoTabMes=true;
  }
  gotoAnnullamentoScontrino() {
    this.eanNonEditabile = false;
    this.vediPrezzoVisibleB = false;
    this.chiudiB = false;
    this.stornaB = false;
    this.annullaScontrinoB = true;
    this.annullaB = true;
    this.confermaB = true;
    this.prezzoB = false;
    this.lista = false;
    this.tastoTabMes=false;
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

  Annulla() {
    this.automa.next(new AnnullaEvent());

  }

  Conferma() {
    this.automa.next(new ConfermaEvent());
  }
  inserisceEan(){
    this.automa.next(new EanEvent(this.ean, this.scontrino));
  }
  
}
