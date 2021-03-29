import {Component, OnInit} from '@angular/core';
import {AutomabileCrud, State} from '../automa/state';
import {HttpClient} from '@angular/common/http';
import {Cassa} from '../entità/cassa';
import {AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RicercaEvent, SelezionaEvent} from '../automa/eventi';
import {Automa} from '../automa/automa';
import {AggiungiState, ModificaState, RimuoviState} from '../automa/stati';
import {CriterioRicercaDto} from '../dto/criterio-ricerca-dto';
import {Observable} from 'rxjs';
import {ListaCasseDto} from '../dto/lista-casse-dto';
import {CassaDto} from '../dto/cassa-dto';


@Component({
  selector: 'app-anagrafica-casse',
  templateUrl: './anagrafica-casse.component.html',
  styleUrls: ['../theme.css']
})
export class AnagraficaCasseComponent implements OnInit, AutomabileCrud {
  cassa: Cassa = new Cassa();
  searchCriterion: string;
  listaCasse: Cassa[] = [];
  automa: Automa;
  stato: State;
  // campi visibilità
  buttonNuovaVisible = false;
  confAnnVisible = false;
  searchVisible = false;
  formDivVisible = false;
  campiNonEditabili = false;
  constructor(private http: HttpClient) {
    this.automa = new Automa(this);
    this.aggiornaLista();
  }

  ngOnInit(): void {
  }

  aggiungiAction(): void {
  }

  goToAggiungi(): void {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToModifica(): void {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToRicerca(): void {
    this.buttonNuovaVisible = true;
    this.formDivVisible = false;
    this.campiNonEditabili = true;
    this.confAnnVisible = true;
    this.searchVisible = true;
  }

  goToRimuovi(): void {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.confAnnVisible = true;
    this.searchVisible = false;
  }

  goToVisualizza(): void {
    this.buttonNuovaVisible = true;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.confAnnVisible = false;
    this.searchVisible = true;
  }

  modificaAction(): void {
  }

  rimuoviAction(): void {
  }

  nuova(): void {
    this.stato = this.automa.next(new AddEvent());
  }

  modifica(): void {
    this.stato = this.automa.next(new ModificaEvent());
  }

  conferma(): void {
    if (this.stato instanceof AggiungiState){
      this.stato = this.automa.next(new ConfermaEvent());
    } else if (this.stato instanceof ModificaState){
      this.stato = this.automa.next(new ConfermaEvent());
    } else if (this.stato instanceof RimuoviState){
      this.stato = this.automa.next(new ConfermaEvent());
    }
  }

  annulla(): void {
    this.automa.next(new AnnullaEvent());
    this.cassa = new Cassa();
  }

  rimuovi(): void {
    const dto: CassaDto = new CassaDto();
    const oss: Observable<ListaCasseDto> = this.http
      .post<ListaCasseDto>('http://localhost:8080/elimina-cassa', dto);
    oss.subscribe(l => this.listaCasse = l.listaCasse);
  }

  cerca(): void {
    const dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.searchCriterion;
    const oss: Observable<ListaCasseDto> = this.http
      .post<ListaCasseDto>('http://localhost:8080/cerca-cassa', dto);
    oss.subscribe(l => this.listaCasse = l.listaCasse);
    this.automa.next(new RicercaEvent());
  }

  seleziona(c: Cassa): void {
    this.cassa = c;
    this.automa.next(new SelezionaEvent());
  }
  aggiornaLista(): void{
    const oss: Observable<ListaCasseDto> = this.http
      .get<ListaCasseDto>('http://localhost:8080/aggiorna-lista-casse');
    oss.subscribe(l => this.listaCasse = l.listaCasse);
  }
}
