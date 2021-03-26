import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-anagrafica-sconti',
  templateUrl: './anagrafica-sconti.component.html',
  styleUrls: ['./anagrafica-sconti.component.css']
})
export class AnagraficaScontiComponent implements OnInit {

  codice:string="";
  descrizione:string="";
  searchCriterion:string="";
  prodotti:string="";
  ean:string="";
   prezzo:number=0;
   scortaScaffale:number=0;
   scortaMagazzino:number=0;
   lottoRiordino:number=0;


  constructor() { }

  ngOnInit(): void {
  }

  nuova(){

  }

  tornaHome(){

  }

  cerca(){

  }

  modifica(){

  }

  conferma(){

  }

  annulla(){

  }

  rimuovi(){

  }

}
