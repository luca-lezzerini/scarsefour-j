import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../entità/prodotto';

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
  prodotti: Prodotto [] = [];
  // inserire nelle entità del client, scontrino e rigascontrino
  // scontrino: Scontrino= new Scontrino();
  // rigaScontrino: RigaScontrino = new RigaScontrino();
  prezzoProdotto: number;

  constructor() { }

  ngOnInit(): void {
  }

  // aggiugni il numero del gruppo alle chiamate al metodo
  vediPrezzo() {

  }

  chiudiScontrino() {

  }

  stornaUltimo() {

  }
  annullaScontrino() {

  }
  annulla() {

  }
  conferma() {

  }


}
