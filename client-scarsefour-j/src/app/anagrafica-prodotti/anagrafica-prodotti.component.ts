import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CriterioCercaDto } from '../dto/criterio-certca-dto';
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

  constructor(private http: HttpClient, private router: Router) { }

  ngOnInit(): void {
  }

  nuova() {

  }

  cercaPerCodice() {
    let dto: CriterioCercaDto = new CriterioCercaDto();
    dto.searchCriterion = this.searchCriterion;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>(
      "localhost:8080/cerca-prodotto-quattro", dto);
    oss.subscribe(c => this.prodotto = c.prodotto);


  }

  modifica() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>(
      "localhost:8080/conferma-prodotto-quattro", dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);

  }

  conferma() {
    let dto: ProdottoDto = new ProdottoDto();
    dto.prodotto = this.prodotto;

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>(
      "localhost:8080/conferma-prodotto-quattro", dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);
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

    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("localhost:8080/conferma-prodotto-quattro", dto);
    oss.subscribe(c => this.listaProdotti = c.listaProdotti);
  }

  aggiorna() {
    let oz: Observable<ListaProdottiDto> = this.http.get<ListaProdottiDto>(
      "localhost:8080/aggiorna-prodotto-quattro");
    oz.subscribe(a => this.listaProdotti = a.listaProdotti);

  }
  vaiA(s: string) {
    this.router.navigateByUrl(s);
  }
  seleziona() { }
}
