import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Automa } from '../automa/automa';
import { AddEvent, AnnullaEvent, ConfermaEvent, ModificaEvent, RimuoviEvent, SelezionaEvent } from '../automa/eventi';
import { AutomabileCrud, State } from '../automa/state';
import { AggiungiState, ModificaState, RimuoviState } from '../automa/stati';
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

  constructor(private http: HttpClient, private router: Router) { this.aggiorna() }
  aggiungiAction() {
    throw new Error('Method not implemented.');
  }
  modificaAction() {
    throw new Error('Method not implemented.');
  }

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

  rimuoviAction() {
    console.log("Siamo in rimuoviAction");
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;
    console.log("Stiamo per rimuovere ", + dto);
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/rimuovi-prodotto-quattro', dto);
    oss.subscribe(r => this.listaProdotti = r.listaProdotti);
  }

  nuova() {
    this.stato = this.automa.next(new AddEvent());

  }

  cercaPerCodice() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.searchCriterion;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>("http://localhost:8080/cerca-prodotto-quattro", dto);
    oss.subscribe(c => this.prodotto = c.prodotto);
    this.automa.next(new SelezionaEvent());


  }

  modifica() {
    this.stato = this.automa.next(new ModificaEvent());
  }

  conferma() {

    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    if (this.stato instanceof AggiungiState) {
      if (this.prodotto.codice == "" ||
        this.prodotto.descrizione == "" ||
        this.prodotto.ean == "" ||
        this.prodotto.prezzo == null ||
        this.prodotto.scortaMinMagazzinoDefault == null ||
        this.prodotto.scortaMinScaffaleDefault == null) {
        this.labelMessaggioErrore = true;

      }


      else {

        let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/conferma-prodotto-quattro', dto);
        oss.subscribe(c => this.listaProdotti = c.listaProdotti);
        this.tabellaProdottiVisibile = true;
      }
    }
    else if (this.stato instanceof ModificaState) {
      let ox: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/modifica-prodotto-quattro', dto);
      ox.subscribe(r => this.listaProdotti = r.listaProdotti);

    }
    else if (this.stato instanceof RimuoviState) {
      let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>('http://localhost:8080/rimuovi-prodotto-quattro', dto);
      oss.subscribe(r => this.listaProdotti = r.listaProdotti);
    }
    this.automa.next(new ConfermaEvent());
    this.prodotto = new Prodotto();

  }


  annulla() {

    this.automa.next(new AnnullaEvent());


  }
  rimuovi() {
    this.stato = this.automa.next(new RimuoviEvent());
    /*let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("http://localhost:8080/conferma-prodotto-quattro", dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);*/
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

    this.prodotto = Object.assign({}, p);
    // this.prodotto.id = p.id;
    // this.prodotto.codice = p.codice;
    // this.prodotto.descrizione = p.descrizione;
    // this.prodotto.ean = p.ean;
    // this.prodotto.prezzo = p.prezzo;
    // this.prodotto.scortaMinMagazzinoDefault = p.scortaMinMagazzinoDefault;
    // this.prodotto.scortaMinMagazzinoDefault = p.scortaMinMagazzinoDefault;
    this.automa.next(new SelezionaEvent());

  }
}
