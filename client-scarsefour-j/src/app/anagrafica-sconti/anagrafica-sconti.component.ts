import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RicercaEvent, RimuoviEvent, SelezionaEvent } from '../automa/eventi';
import { Sconto } from '../entità/sconto';
import { Observable } from 'rxjs';
import { ListaScontiDto } from '../dto/lista-sconti-dto';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';

import { AggiungiState, ModificaState, RimuoviState } from '../automa/stati';
import { ScontoDto } from '../dto/sconto-dto';
import { AutomabileCrud, State } from '../automa/state';

@Component({
  selector: 'app-anagrafica-sconti',
  templateUrl: './anagrafica-sconti.component.html',
  styleUrls: ['../theme.css']
})
export class AnagraficaScontiComponent implements OnInit, AutomabileCrud {
  stato: State;
  sconto: Sconto = new Sconto();
  sconti: Sconto[] = [];
  criterio: string = "";
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

    this.automa = new Automa(this);
    this.aggiorna();
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
    this.search = true;
    this.tabella = true;
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


  }

  tornaHome() {
    this.router.navigateByUrl('home-page');
  }
  rimuoviAction() {
    let dto: ScontoDto = new ScontoDto();
    dto.sconto = this.sconto;
    let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
      'http://localhost:8080/rimuovi-sconto', dto);
    oss.subscribe(c => this.sconti = c.listaSconti);
    this.sconto = new Sconto();
    this.automa.next(new ConfermaEvent());
  }

  cerca() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.criterio;
    let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
      "http://localhost:8080/ricerca-sconto", dto);
    oss.subscribe(c => this.sconti = c.listaSconti);
    this.automa.next(new RicercaEvent());
  }

  aggiungiAction() {
    let dto: ScontoDto = new ScontoDto();
    dto.sconto = this.sconto;
    let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
      'http://localhost:8080/aggiungi-sconto', dto);
    oss.subscribe(c => this.sconti = c.listaSconti);
    this.automa.next(new ConfermaEvent());
    this.sconto = new Sconto();

  }
  modificaAction() {
    let dto: ScontoDto = new ScontoDto();
    dto.sconto = this.sconto;
    let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
      'http://localhost:8080/modifica-sconto', dto);
    oss.subscribe(c => this.sconti = c.listaSconti);
    this.automa.next(new ModificaEvent());

  }

  /* modifica() {
     this.automa.next(new ModificaEvent());
   }
 
   conferma() {
       let dto: ScontoDto = new ScontoDto();
       dto.sconto = this.sconto;
       if (this.automa.stato instanceof AggiungiState) {
         let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
           'http://localhost:8080/aggiungi-sconto', dto);
         oss.subscribe(c => this.sconti = c.listaSconti);
       } else if (this.automa.stato instanceof ModificaState) {
         let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
           'http://localhost:8080/modifica-sconto', dto);
         oss.subscribe(c => this.sconti = c.listaSconti);
       } else if (this.automa.stato instanceof RimuoviState) {
   
         let oss: Observable<ListaScontiDto> = this.http.post<ListaScontiDto>(
           'http://localhost:8080/rimuovi-sconto', dto);
         oss.subscribe(c => this.sconti = c.listaSconti);
       }
       this.automa.next(new ConfermaEvent());
       this.sconto = new Sconto();
       */






  annulla() {
    this.automa.next(new AnnullaEvent());

  }

  rimuovi() {
    this.automa.next(new RimuoviEvent());

  }

  aggiorna() {
    let oss: Observable<ListaScontiDto> = this.http.get<ListaScontiDto>(
      'http://localhost:8080/aggiorna-lista-sconti');
    oss.subscribe(c => this.sconti = c.listaSconti);
  }

  seleziona(s: Sconto) {
    this.sconto = Object.assign({}, s);
    this.automa.next(new SelezionaEvent());
  }

}














