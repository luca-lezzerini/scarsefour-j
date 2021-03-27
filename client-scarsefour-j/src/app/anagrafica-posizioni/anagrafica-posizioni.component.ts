import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RicercaEvent, RimuoviEvent, SelezionaEvent } from '../automa/eventi';
import { State } from '../automa/state';
import { AggiungiState, ModificaState, RimuoviState } from '../automa/stati';
import { PosizioneScaffale } from '../entità/posizione-scaffale';

@Component({
  selector: 'app-anagrafica-posizioni',
  templateUrl: './anagrafica-posizioni.component.html',
  styleUrls: ['./anagrafica-posizioni.component.css']
})
export class AnagraficaPosizioniComponent implements OnInit {

  posizione: PosizioneScaffale = new PosizioneScaffale();
  posizioni: PosizioneScaffale [] = [];
  criterio: string = "";
  automa: Automa;
  stato: State;

  buttonNuovaVisible: boolean = false;
  formDivVisible: boolean = false;
  campiNonEditabili: boolean = false;
  confAnnVisible: boolean = false;
  searchVisible: boolean = false;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
    this.aggiorna();
    this.automa = new Automa(this);
  }

  goToAggiungi() {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToModifica() {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToRicerca() {
    this.buttonNuovaVisible = true;
    this.formDivVisible = false;
    this.searchVisible = true;
  }

  goToRimuovi() {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToVisualizza() {
    this.buttonNuovaVisible = true;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.confAnnVisible = false;
    this.searchVisible = true;
  }

  nuova() {
    this.stato = this.automa.next(new AddEvent());
  }

  modifica() {
    this.stato = this.automa.next(new ModificaEvent());
  }

  conferma() {
   
  }

  annulla() {
    this.automa.next(new AnnullaEvent());
  }

  rimuovi() {
    this.stato = this.automa.next(new RimuoviEvent());
  }

  cerca() {
    
  }

  seleziona() {
    this.automa.next(new SelezionaEvent());
  }

  aggiorna() {
    
  }

}
