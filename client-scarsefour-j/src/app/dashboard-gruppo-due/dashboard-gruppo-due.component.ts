import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-gruppo-due',
  templateUrl: './dashboard-gruppo-due.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoDueComponent implements OnInit {

  prezzo: number;
  ultimoElemento: string; //da modificare e sistemare o come dto e lista descrizione e prezzo
  barcode: string;
  
  constructor() { }

  ngOnInit(): void {
  }

  vaiAHome() {}

  annullaScontrino() {}

  vediPrezzo() {}

  chiudiScontrino() {}

  stornaUltimo() {}

  annulla(){}

  conferma(){}

}
