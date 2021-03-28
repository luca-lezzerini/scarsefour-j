import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-gruppo-quattro',
  templateUrl: './dashboard-gruppo-quattro.component.html',
  styleUrls: ['./dashboard-gruppo-quattro.component.css']
})
export class DashboardGruppoQuattroComponent implements OnInit {

  barcode:string;
  Descrizione:string;
  prezzo:string;
  totale:string;
  scontrini:string;



  constructor() { }

  ngOnInit(): void {
  }


  vediPrezzo(){

  }

  chiudiScontrino(){

  }

  stornaUltimo(){

  }

  annullaScontrino(){

  }

  Annulla(){

  }

  Conferma(){

  }



}
