import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-anagrafica-sconti',
  templateUrl: './anagrafica-sconti.component.html',
  styleUrls: ['./anagrafica-sconti.component.css']
})
export class AnagraficaScontiComponent implements OnInit {

  codice: string = "";
  descrizione: string = "";
  searchCriterion: string = "";
  dallaData: string ="";
  allaData: string="";
  sconto:number=0;
  sconti:string="";
  


  constructor() { }

  ngOnInit(): void {
  }

  nuova() {

  }

  tornaHome() {

  }

  cerca() {

  }

  modifica() {

  }

  conferma() {

  }

  annulla() {

  }

  rimuovi() {

  }

}
