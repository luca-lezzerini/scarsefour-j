import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';
import { AutomaTre } from './automa-tre';
import { AnnullaEvent, AnnullaScontrinoEvent, ChiudiEvent, ConfermaEvent, EanEvent, StornaUnoEvent, VediPrezzoEvent } from './eventi-tre';
import { StateTre } from './state-tre';
import { ScontrinoNonVuotoState, ScontrinoVuotoState, VediPrezzoState } from './stati';

@Component({
  selector: 'app-dashboard-gruppo-tre',
  templateUrl: './dashboard-gruppo-tre.component.html',
  styleUrls: ['./dashboard-gruppo-tre.component.css']
})
export class DashboardGruppoTreComponent implements OnInit {

  barcode: string;
  // riportare l'ultimo elemento della tabella, solo descrizione e prezzo
  ultimoElemento: string;
  prodotto: Prodotto = new Prodotto();
  righe: RigaScontrino [] = [];
  scontrino: Scontrino= new Scontrino();
  rigaScontrino: RigaScontrino = new RigaScontrino();
  prezzoProdotto: number;
  inputEnabled: boolean;
  prezzoEnabled: boolean;
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

  constructor() { }

  ngOnInit(): void {
    this.automa = new AutomaTre(this);
  }

  goToScontrinoVuoto(){
    this.inputEnabled = true;
    this.prezzoEnabled = false;
    this.chiudiEnabled = false;
    this.prezzoVisible = false;
    this.stornaVisible = true;
    this.annullaScontrinoEnabled = false;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = false;
  }

  goToScontrinoNonVuoto(){
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

  goToVediPrezzo(){
    this.inputEnabled = true;
    this.prezzoEnabled = false;
    this.chiudiEnabled = false;
    this.prezzoVisible = false;
    this.stornaVisible = true;
    this.annullaScontrinoEnabled = false;
    this.annullaEnabled = false;
    this.confermaEnabled = false;
    this.listaVisible = false;
  }
    

  goToAnnullamentoScontrino(){
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

  // aggiugni il numero del gruppo alle chiamate al metodo
  vediPrezzo() {
    this.stato = this.automa.next(new VediPrezzoEvent());
  }

  chiudiScontrino() {
    this.stato = this.automa.next(new ChiudiEvent());
  }

  stornaUltimo() {
    this.stato = this.automa.next(new StornaUnoEvent());
  }
  annullaScontrino() {
    this.stato = this.automa.next(new AnnullaScontrinoEvent());
  }
  annulla() {
    this.stato = this.automa.next(new AnnullaEvent());
  }
  conferma() {
  }


}
