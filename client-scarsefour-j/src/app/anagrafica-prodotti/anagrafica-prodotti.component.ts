import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RicercaEvent, RimuoviEvent, SelezionaEvent } from '../automa/eventi';
import { AutomabileCrud, State } from '../automa/state';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { Prodotto } from '../entità/prodotto';


@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['../theme.css']
})
export class AnagraficaProdottiComponent implements OnInit, AutomabileCrud {

  prodotto: Prodotto = new Prodotto();
  listaProdotti: Prodotto[] = [];
  searchCriterion: string;
  automa: Automa;
  stato: State;
  buttonNuovaVisible: boolean = true;
  formDivVisible: boolean;
  campiNonEditabili: boolean = false;
  confAnnVisible: boolean = false;
  modRimVisible: boolean;
  searchVisible: boolean = true;
  tabellaProdottiVisibile: boolean = true;
  labelNuovoProdotto: boolean;
  labelMessaggioErrore: boolean = false;
  errore: string = "Errore, inserire dati validi";

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
    this.aggiorna();
    this.automa = new Automa(this);
  }
  goToRicerca() {
    this.labelNuovoProdotto = false;
    this.confAnnVisible = false;
    this.buttonNuovaVisible = true;
    this.formDivVisible = false;
    this.searchVisible = true;

  }
  goToAggiungi() {
    this.labelNuovoProdotto = true;
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.modRimVisible = false;
    this.searchVisible = false;
    this.tabellaProdottiVisibile = false;
    this.labelMessaggioErrore = false;


  }
  goToVisualizza() {
    this.labelNuovoProdotto = false;
    this.buttonNuovaVisible = true;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.confAnnVisible = false;
    this.modRimVisible = true;
    this.searchVisible = true;
    this.tabellaProdottiVisibile = true;
    this.labelMessaggioErrore = false;


  }
  goToModifica() {
    this.buttonNuovaVisible = false;
    this.formDivVisible = true;
    this.campiNonEditabili = false;
    this.confAnnVisible = true;
    this.modRimVisible = false;
    this.searchVisible = false;
  }

  goToRimuovi() {
    this.tabellaProdottiVisibile = true;
    this.campiNonEditabili = false;
    this.buttonNuovaVisible = true;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.modRimVisible = false;
    this.confAnnVisible = true;
    this.searchVisible = false;

  }
  aggiungiAction() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/conferma-prodotto-quattro', dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);
    this.tabellaProdottiVisibile = true;

    this.prodotto = new Prodotto();
    //Errore , se passato il metodo next genera loop
    //this.automa.next(new ConfermaEvent());

  }

  modificaAction() {
    console.log("Siamo in ModificaAction");
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;
    console.log("Stiamo per modificare ", + dto)
    let ox: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/modifica-prodotto-quattro', dto);
    ox.subscribe(r => this.listaProdotti = r.listaProdotti);
    //this.stato = this.automa.next(new ModificaEvent());

  }

  rimuoviAction() {
    console.log("Siamo in rimuoviAction");
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;
    console.log("Stiamo per rimuovere ", + dto);
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/rimuovi-prodotto-quattro', dto);
    oss.subscribe(r => this.listaProdotti = r.listaProdotti);
    //this.stato = this.automa.next(new RimuoviEvent());
  }

  nuova() {
    this.stato = this.automa.next(new AddEvent());

  }

  cercaPerCodice() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.searchCriterion;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>("http://localhost:8080/cerca-prodotto-quattro", dto);
    oss.subscribe(c => this.prodotto = c.prodotto);
    this.automa.next(new RicercaEvent());


  }

  modifica() {
    this.stato = this.automa.next(new ModificaEvent());
  }

  conferma() {

    /*if (this.prodotto.codice == "" ||
      this.prodotto.descrizione == "" ||
      this.prodotto.ean == "" ||
      this.prodotto.prezzo == null ||
      this.prodotto.scortaMinMagazzinoDefault == null ||
      this.prodotto.scortaMinScaffaleDefault == null) {
      this.labelMessaggioErrore = true;
*/
      this.automa.next(new ConfermaEvent());
      this.prodotto = new Prodotto();
    }

  

  annulla() {

    this.automa.next(new AnnullaEvent());

  }
  rimuovi() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;
    this.stato = this.automa.next(new RimuoviEvent());
  }
  aggiorna() {
    let oz: Observable<ListaProdottiDto> = this.http.get<ListaProdottiDto>(
      "http://localhost:8080/aggiorna-prodotto-quattro");
    oz.subscribe(a => this.listaProdotti = a.listaProdotti);

  }
  vaiA(s: string) {
    this.router.navigateByUrl(s);
  }
  seleziona(p: Prodotto) {

    this.buttonNuovaVisible = true;
    this.formDivVisible = true;
    this.campiNonEditabili = true;
    this.modRimVisible = true;
    this.confAnnVisible = true;
    this.searchVisible = true;
    this.tabellaProdottiVisibile = true;
    //Valorizzazione di tutte le proprietà della classe con quelle del oggetto selezionato
    this.prodotto = Object.assign({}, p);

    this.automa.next(new SelezionaEvent());

  }
}
