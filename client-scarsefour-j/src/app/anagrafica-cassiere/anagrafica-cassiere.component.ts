import { Component, OnInit } from '@angular/core';
import { Automa } from '../automa/automa';
import { Automabile } from '../automa/state';
import { Cassiere } from '../entità/cassiere';
import { HttpClient } from '@angular/common/http';
import {
  AddEvent,
  AnnullaEvent,
  ConfermaEvent,
  ModificaEvent,
  RicercaEvent,
  RimuoviEvent,
  SelezionaEvent,
} from '../automa/eventi';
import { AggiungiState, ModificaState, RimuoviState } from '../automa/stati';
import { CassiereDto } from '../dto/cassiere-dto';
import { Observable } from 'rxjs';
import { ListaCassieriDto } from '../dto/lista-cassieri-dto';
import { CassiereRicercaDto } from '../dto/cassiere-ricerca-dto';

import { Router } from '@angular/router';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';

@Component({
  selector: 'app-anagrafica-cassiere',
  templateUrl: './anagrafica-cassiere.component.html',
  styleUrls: ['../theme.css'],
})
export class AnagraficaCassiereComponent implements OnInit, Automabile {
  automa: Automa;
  cassiere: Cassiere = new Cassiere();
  cassieri: Cassiere[] = [];
  strRicerca: string;

  //variabili visualizzazione campi e pulsanti
  buttonNuovaVisible: boolean = false;
  formDivVisible: boolean = false;
  campiNonEditabili: boolean = false;
  confAnnVisible: boolean = false;
  searchVisible: boolean = false;


  constructor(private http: HttpClient, private router: Router) {
    this.automa = new Automa(this);
    this.aggiorna();
  }

  ngOnInit(): void {}

  //metodi Automabile
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
    // this.campiNonEditabili = true;
    // this.confAnnVisible = true;
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

  //Metodi UI

  nuova() {
    if (
      this.cassiere.nome == null &&
      this.cassiere.cognome == null &&
      this.cassiere.codiceFiscale == null
    ) {
      this.automa.next(new AddEvent());
    }
  }

  modifica() {
    if (
      this.cassiere.nome != null &&
      this.cassiere.cognome != null &&
      this.cassiere.codiceFiscale != null
    ) {
      this.automa.next(new ModificaEvent());
    }
  }

  rimuovi() {
    if (
      this.cassiere.nome != null &&
      this.cassiere.cognome != null &&
      this.cassiere.codiceFiscale != null
    ) {
      this.automa.next(new RimuoviEvent());
    }
  }

  annulla() {
    this.automa.next(new AnnullaEvent());
    this.cassiere = new Cassiere();
  }

  conferma() {
    if (this.automa.stato instanceof AggiungiState) {
      console.log('sono in Conferma Aggiungi');
      if (
        this.cassiere.nome != null &&
        this.cassiere.cognome != null &&
        this.cassiere.codiceFiscale != null
      ) {
        let dto: CassiereDto = new CassiereDto();
        dto.cassiere = this.cassiere;
        let oss: Observable<ListaCassieriDto> = this.http.post<ListaCassieriDto>(
          'http://localhost:8080/aggiungi-cassiere',
          dto
        );
        oss.subscribe((c) => (this.cassieri = c.listaCassieri));
      }
    } else if (this.automa.stato instanceof ModificaState) {
      console.log('sono in Conferma Modifica');
      if (
        this.cassiere.nome != null &&
        this.cassiere.cognome != null &&
        this.cassiere.codiceFiscale != null
      ) {
        let dto: CassiereRicercaDto = new CassiereRicercaDto();
        dto.cassiere = this.cassiere;
        dto.criterio = this.strRicerca;
        let oss: Observable<ListaCassieriDto> = this.http.post<ListaCassieriDto>(
          'http://localhost:8080/modifica-cassiere',
          dto
        );
        oss.subscribe((c) => (this.cassieri = c.listaCassieri));
      }
    } else if (this.automa.stato instanceof RimuoviState) {
      console.log('sono in Conferma Rimuovi');
      if (
        this.cassiere.nome != null &&
        this.cassiere.cognome != null &&
        this.cassiere.codiceFiscale != null
      ) {
        let dto: CassiereRicercaDto = new CassiereRicercaDto();
        dto.cassiere = this.cassiere;
        dto.criterio = this.strRicerca;
        let oss: Observable<ListaCassieriDto> = this.http.post<ListaCassieriDto>(
          'http://localhost:8080/rimuovi-cassiere',
          dto
        );
        oss.subscribe((c) => (this.cassieri = c.listaCassieri));
      }
    }
    this.automa.next(new ConfermaEvent());
    this.cassiere = new Cassiere();
  }

  seleziona(c: Cassiere) {
    this.automa.next(new SelezionaEvent());
    this.cassiere.id = c.id;
    this.cassiere.nome = c.nome;
    this.cassiere.cognome = c.cognome;
    this.cassiere.codiceFiscale = c.codiceFiscale;
  }

  cerca() {
    this.automa.next(new RicercaEvent());
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.strRicerca ;
    let oss: Observable<ListaCassieriDto> = this.http.post<ListaCassieriDto>(
      'http://localhost:8080/ricerca-cassiere',
      dto
    );
    oss.subscribe((c) => (this.cassieri = c.listaCassieri));
  }

  aggiorna() {
    let oss: Observable<ListaCassieriDto> = this.http.get<ListaCassieriDto>(
      'http://localhost:8080/visualizza-lista-cassieri'
    );
    oss.subscribe((c) => (this.cassieri = c.listaCassieri));
  }

  vaiAHome() {
    this.router.navigateByUrl('home-page');
  }
}
