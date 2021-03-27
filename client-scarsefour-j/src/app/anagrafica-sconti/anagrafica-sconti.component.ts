import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ModificaEvent, RicercaEvent, RimuoviEvent } from '../automa/eventi';
import { Sconto } from '../entità/sconto';

@Component({
  selector: 'app-anagrafica-sconti',
  templateUrl: './anagrafica-sconti.component.html',
  styleUrls: ['./anagrafica-sconti.component.css']
})
export class AnagraficaScontiComponent implements OnInit {

  sconto: Sconto = new Sconto();
  sconti: Sconto[] = [];
  searchCriterion: string = "";
  automa: Automa;

  //Inizializzazione variabili visualizzazione campi e pulsanti
  form: boolean;
  aggiungi: boolean;
  remove: boolean;
  edit: boolean;
  conf: boolean;
  annull: boolean;
  search: boolean;
  tabella: boolean;
  codiceS: boolean;
  descrizioneS: boolean;
  dallaDataS: boolean;
  allaDataS: boolean;
  scontoS: boolean;




  constructor(private http: HttpClient, private router: Router) {
    this.aggiorna();
    this.automa = new Automa(this);

  }

  ngOnInit(): void {

  }

  goToAggiungi() {
    this.form = true;
    this.aggiungi = false;
    this.remove = false;
    this.edit = false;
    this.conf = true;
    this.annull = true;
    this.search = false;
    this.tabella = false;
    this.codiceS = false;
    this.descrizioneS = false;
    this.dallaDataS = false;
    this.allaDataS = false;
    this.scontoS = false;
  }

  goToModifica() {
    this.form = true;
    this.aggiungi = false;
    this.remove = false;
    this.edit = false;
    this.conf = true;
    this.annull = true;
    this.search = false;
    this.tabella = false;
    this.codiceS = false;
    this.descrizioneS = false;
    this.dallaDataS = false;
    this.allaDataS = false;
    this.scontoS = false;
  }

  goToRicerca() {
    this.form = false;
    this.aggiungi = true;
    this.remove = false;
    this.edit = false;
    this.conf = false;
    this.annull = false;
    this.search =true;
    this.tabella = false;
    this.codiceS = false;
    this.descrizioneS = false;
    this.dallaDataS = false;
    this.allaDataS = false;
    this.scontoS = false;
  }

  goToRimuovi() {
    this.form = true;
    this.aggiungi = false;
    this.remove = false;
    this.edit = false;
    this.conf = true;
    this.annull = true;
    this.search = false;
    this.tabella = false;
    this.codiceS = true;
    this.descrizioneS = true;
    this.dallaDataS = true;
    this.allaDataS = true;
    this.scontoS = true;
  }

  goToVisualizza() {
    this.form = true;
    this.aggiungi = true;
    this.remove = true;
    this.edit = true;
    this.conf = false;
    this.annull = false;
    this.search = true;
    this.tabella = true;
    this.codiceS = true;
    this.descrizioneS = true;
    this.dallaDataS = true;
    this.allaDataS = true;
    this.scontoS = true;
  }

  nuova() {
    this.automa.next(new AddEvent());
    console.log("sei in nuova")

  }

  tornaHome() {
    this.router.navigateByUrl('home-page');
  }

  cerca() {
    this.automa.next(new RicercaEvent());
    console.log("sei in cerca")

  }

  modifica() {
    this.automa.next(new ModificaEvent());
    console.log("sei in modifica")

  }

  conferma() {

  }

  annulla() {
    this.automa.next(new AnnullaEvent());

  }

  rimuovi() {
    this.automa.next(new RimuoviEvent());

  }

  aggiorna() {

  }

}
