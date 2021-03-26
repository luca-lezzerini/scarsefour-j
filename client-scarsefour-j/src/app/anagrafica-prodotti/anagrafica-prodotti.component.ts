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
