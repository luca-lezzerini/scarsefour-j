import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Automa } from '../automa/automa';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { Prodotto } from '../entità/prodotto';


@Component({
  selector: 'app-anagrafica-prodotti',
  templateUrl: './anagrafica-prodotti.component.html',
  styleUrls: ['./anagrafica-prodotti.component.css']
})
export class AnagraficaProdottiComponent implements OnInit {

  prodotto: Prodotto = new Prodotto();
  listaProdotti: Prodotto[] = [];
  searchCriterion: string;
  automa: Automa;

  constructor(private http: HttpClient, private router: Router){this.aggiorna()}

  ngOnInit(): void {
  }

  nuova() {

  }

  cercaPerCodice() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.searchCriterion;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>("http://localhost:8080/cerca-prodotto-quattro", dto);
      
    oss.subscribe(c => this.prodotto = c.prodotto);


  }

  modifica() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>( "http://localhost:8080/conferma-prodotto-quattro", dto);
     
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);

  }

  conferma() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("http://localhost:8080/conferma-prodotto-quattro", dto);
      
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);
    this.prodotto = new Prodotto();
  }


  annulla() {
    this.prodotto.codice = "";
    this.prodotto.descrizione = "";
    this.prodotto.ean = "";
    this.prodotto.prezzo = 0;
    this.prodotto.scortaMinimaMag = 0;
    this.prodotto.scortaMinimaScaf = 0;
  }

  rimuovi() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("http://localhost:8080/conferma-prodotto-quattro", dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);
  }

  aggiorna() {
    let oz: Observable<ListaProdottiDto> = this.http.get<ListaProdottiDto>(
      "http://localhost:8080/aggiorna-prodotto-quattro");
    oz.subscribe(a => this.listaProdotti = a.listaProdotti);

  }
  vaiA(s: string) {
    this.router.navigateByUrl(s);
  }
  seleziona(p:Prodotto) {
    this.prodotto.codice = p.codice;
    this.prodotto.descrizione = p.descrizione;
    this.prodotto.ean = p.ean;
    this.prodotto.prezzo = p.prezzo;
    this.prodotto.scortaMinimaMag = p.scortaMinimaMag;
    this.prodotto.scortaMinimaScaf = p.scortaMinimaScaf;
   }
}
