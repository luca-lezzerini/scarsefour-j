import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RicercaEvent, RimuoviEvent, SelezionaEvent } from '../automa/eventi';
import { AutomabileCrud, State } from '../automa/state';
import { AggiungiState, ModificaState, RimuoviState } from '../automa/stati';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ListaPosizioneScaffaleDto } from '../dto/lista-posizione-scaffale-dto';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';

@Component({
  selector: 'app-anagrafica-posizioni',
  templateUrl: './anagrafica-posizioni.component.html',
  styleUrls: ['../theme.css']
})
export class AnagraficaPosizioniComponent implements OnInit, AutomabileCrud {

  posizione: PosizioneScaffale = new PosizioneScaffale();
  posizioni: PosizioneScaffale[] = [];
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
    this.searchVisible = true;
  }

  goToModifica() {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.searchVisible = true;
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
  
  rimuoviAction() {
    console.log("Siamo in rimuoviAction");
  }

  nuova() {
    this.stato = this.automa.next(new AddEvent());
  }

  modifica() {
    this.stato = this.automa.next(new ModificaEvent());
  }

  conferma() {
    let dto: PosizioneScaffaleDto = new PosizioneScaffaleDto();
    dto.posizione = this.posizione;
    if (this.stato instanceof AggiungiState) {
      let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>('http://localhost:8080/aggiungi-posizioni', dto);
      oss.subscribe(r => this.posizioni = r.listaPosizioni);
    } else if (this.stato instanceof ModificaState) {
      let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>('http://localhost:8080/modifica-posizioni', dto);
      oss.subscribe(r => this.posizioni = r.listaPosizioni);
    } else if (this.stato instanceof RimuoviState) {
      let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>('http://localhost:8080/rimuovi-posizioni', dto);
      oss.subscribe(r => this.posizioni = r.listaPosizioni);
    }
    this.automa.next(new ConfermaEvent());
    this.posizione = new PosizioneScaffale();
  }

  annulla() {
    this.automa.next(new AnnullaEvent());
  }

  rimuovi() {
    this.stato = this.automa.next(new RimuoviEvent());
  }

  cerca() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.criterio;
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>('http://localhost:8080/ricerca-posizioni', dto);
    oss.subscribe(r => this.posizioni = r.listaPosizioni);
    this.automa.next(new RicercaEvent());
  }

  seleziona(posiz: PosizioneScaffale) {
    this.automa.next(new SelezionaEvent());
    this.posizione.id = posiz.id;
    this.posizione.codice = posiz.codice;
    this.posizione.descrizione = posiz.descrizione;
  }

  aggiorna() {
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.get<ListaPosizioneScaffaleDto>('http://localhost:8080/aggiorna-posizioni');
    oss.subscribe(r => this.posizioni = r.listaPosizioni);
  }
}
