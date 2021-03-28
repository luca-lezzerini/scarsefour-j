import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AutomaDashboardUno } from './automa-dashboard-uno/automa-dashboard-uno';
import { EanEvent } from './automa-dashboard-uno/eventi-dashboard-uno';
import { AutomabileDashboardUno } from './automa-dashboard-uno/state-dashboard-uno';
import { ScontrinoNonVuotoState } from './automa-dashboard-uno/stati-dashboard-uno';

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
    this.prezzoVis = true;
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

  vaiAHome() { }

  cercaEan() {
  }

  chiudiScontrino() { }

  vediPrezzo() { }

  stornaUltimo() { }

  annullaScontrino() { }

  conferma() { }

  annulla() { }
}
