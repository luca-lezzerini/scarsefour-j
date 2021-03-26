import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['./anagrafica-prodotti.component.css']
})
export class AnagraficaProdottiComponent implements OnInit {

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
