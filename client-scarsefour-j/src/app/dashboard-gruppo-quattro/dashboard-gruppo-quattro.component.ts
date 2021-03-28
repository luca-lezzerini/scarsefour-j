import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-gruppo-quattro',
  templateUrl: './dashboard-gruppo-quattro.component.html',
  styleUrls: ['./dashboard-gruppo-quattro.component.css']
})
export class DashboardGruppoQuattroComponent implements OnInit {
  
  ean:string;
  Descrizione:string;
  prezzo:string;
  totale:string;
  scontrini:string;

  eanNonEditabile:boolean;
  vediPrezzoVisibleB:boolean;
  chiudiB:boolean;
  stornaB:boolean;
  annullaScontrinoB:boolean;
  annullaB:boolean;
  confermaB:boolean;
  prezzoB:boolean;
  lista:boolean;
  
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
