import { HttpClient } from '@angular/common/http';
import { Component, OnInit, SystemJsNgModuleLoader } from '@angular/core';
import { Observable } from 'rxjs';
import { CaricaMerceDto } from '../dto/carica-merce-dto';
import { EsitoDtoDue } from '../dto/esito-dto-due';
import { IdPosizioneScaffaleDto } from '../dto/id-posizione-scaffale';
import { ListaPosizioneScaffaleDto } from '../dto/lista-posizione-scaffale-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ProdottoGiacenza } from '../entità/prodotto-giacenza';
import { Prodotto } from '../entità/prodotto';
import { ListaProdottiGiacenzaDto } from '../dto/lista-prodotti-giacenza-dto';
import { PageDto } from '../dto/page-dto';
import { DatiPageDto } from '../dto/dati-page-dto';

@Component({
  selector: 'app-carica-merce',
  templateUrl: './carica-merce.component.html',
  styleUrls: ['../theme.css'],
})
export class CaricaMerceComponent implements OnInit {
  listaPosizioni: PosizioneScaffale[] = [];
  listaProdottiGiacenza: ProdottoGiacenza[] = [];

  divTabellaProdottiVisibile: boolean = false;
  divAggiungiQuantitaVisibile: boolean = false;

  quantita: number;

  id_Sku: number;
  id_Pos: number;
  codiceProdottoSel: string;
  descrProdottoSel: string;
  giacenzaAttualeProdottoSel: number;
  codiceScaffaleSel: string;
  descrizioneScaffaleSel: string;

  criterio: string;

  //variabili paginazione
  numPag: number = 0;
  numPaginaV: number = 1;
  elemPag: number = 25;
  totalPages: number;
  totalElements: number;
  first: boolean;
  last: boolean;

  numberOfElements: number;
  
  constructor(private http: HttpClient) {
    //this.caricaPosizioni();
    this.caricaPosizioniPaginati(this.numPaginaV);
  }

  ngOnInit(): void { }

  caricaPosizioni() {
    console.log('sono in carica posizioni');
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.get<ListaPosizioneScaffaleDto>(
      'http://localhost:8080/carica-posizioni'
    );

    oss.subscribe((p) => {
      console.log('ritorno dal server carica posizioni : ', p.listaPosizioni);
      this.listaPosizioni = p.listaPosizioni;
    });
  }

  cercaPosizione(criterio: string) {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.criterio;
    console.log('sono in cerca prodotti');
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>(
      'http://localhost:8080/cerca-posizioni',
      dto
    );
    oss.subscribe((p) => {
      console.log('ritorno dal server cerca posizione : ', p.listaPosizioni);
      this.listaPosizioni = p.listaPosizioni;
    });
  }

  caricaProdotti(ps: PosizioneScaffale) {
    
    this.divTabellaProdottiVisibile = false;
    this.divAggiungiQuantitaVisibile = false;
    this.id_Pos = ps.id;
    console.log('sono in carica prodotti');
    let dto: IdPosizioneScaffaleDto = new IdPosizioneScaffaleDto();
    dto.id = ps.id;
    let oss: Observable<ListaProdottiGiacenzaDto> = this.http.post<ListaProdottiGiacenzaDto>(
      'http://localhost:8080/carica-prodotti',
      dto
    );
    oss.subscribe((p) => {
      this.listaProdottiGiacenza = p.listaProdottiGiacenza;
      console.log('------ritorno dal server carica prodotti : ', this.listaProdottiGiacenza);
      // visualizzo lo scaffale selezionato
    this.codiceScaffaleSel = ps.codice;
    this.descrizioneScaffaleSel = ps.descrizione;
      this.divTabellaProdottiVisibile = true;
    });
  }

  prodottoSelezionato(p: ProdottoGiacenza) {
    this.id_Sku = p.id_Sku;
    this.codiceProdottoSel = p.codice;
    this.descrProdottoSel = p.descrizione;
    this.giacenzaAttualeProdottoSel = p.giacenza;
    this.divAggiungiQuantitaVisibile = true;
  }

  caricaMerce(quantita: number) {
    let dto: CaricaMerceDto = new CaricaMerceDto();
    dto.id_Pos = this.id_Pos;
    dto.id_Sku = this.id_Sku;
    dto.quantita = quantita;

    console.log("caricaMerceDto : " + CaricaMerceDto);
    
    let oss: Observable<ListaProdottiGiacenzaDto> = this.http.post<ListaProdottiGiacenzaDto>(
      'http://localhost:8080/carica-merce',
      dto
    );
    oss.subscribe((p) => {
      this.listaProdottiGiacenza = p.listaProdottiGiacenza;
      console.log('------ritorno dal server carica Merce : ', this.listaProdottiGiacenza);            
    });
    this.quantita = null;
    this.divAggiungiQuantitaVisibile = false;
  }

  //metodi paginazione
  caricaPosizioniPaginati(numPaginaV: number) {
    this.numPaginaV = numPaginaV;
    if (this.numPaginaV > 0) {
      let dto: DatiPageDto = new DatiPageDto();
      dto.numPag = this.numPaginaV - 1;
      dto.elemPag = this.elemPag;
      let oss: Observable<PageDto> = this.http.post<PageDto>
        ("http://localhost:8080/carica-posizioni-paginati", dto);
      oss.subscribe(v => {
        this.listaPosizioni = v.listaElemPag.content;
        this.totalPages = v.listaElemPag.totalPages;
        this.numPag = this.numPaginaV - 1;
        console.log("totalPages: " + v.listaElemPag.totalPages);
        console.log("totalElements: " + v.listaElemPag.totalElements);
        console.log("number: " + v.listaElemPag.number);
        console.log("first: " + v.listaElemPag.first);
        console.log("last: " + v.listaElemPag.last);
        console.log("size. " + v.listaElemPag.size);
        console.log("numberOfElements: " + v.listaElemPag.numberOfElements);
        if (this.numPaginaV > this.totalPages) {
          this.numPag = this.totalPages - 1;
          this.numPaginaV = this.numPag + 1;
          this.caricaPosizioniPaginati(this.numPaginaV);
        }
      });
    }
    else {
      this.numPag = 0;
      this.numPaginaV = this.numPag + 1;
      this.caricaPosizioniPaginati(this.numPaginaV);
    }
  }

  goToNext(numPaginaV: number) {
    console.log("sono in next del padre");
    console.log("val numPaginaV: ", numPaginaV);
    this.numPaginaV = numPaginaV;
    this.numPag += 1;
    this.numPaginaV = this.numPag + 1;
    this.caricaPosizioniPaginati(this.numPaginaV);
  }

  goToPrevious(numPaginaV: number) {
    console.log("sono in prev del padre");
    console.log("val numPaginaV: ", numPaginaV);
    this.numPaginaV = numPaginaV;
    this.numPag -= 1;
    this.numPaginaV = this.numPag + 1;
    this.caricaPosizioniPaginati(this.numPaginaV);
  }

  goToFirst(numPaginaV: number) {
    console.log("sono in first del padre");
    console.log("val numPaginaV: ", numPaginaV);
    this.numPaginaV = numPaginaV;
    this.numPag = numPaginaV - 1;
    this.caricaPosizioniPaginati(this.numPaginaV);
  }

  goToLast(numPaginaV: number) {
    console.log("sono in last del padre");
    console.log("val numPaginaV: ", numPaginaV);
    this.numPag = this.totalPages - 1;
    this.numPaginaV = this.numPag + 1;
    this.caricaPosizioniPaginati(this.numPaginaV);
  }

  setElemPag(elemPag: number) {
    console.log("sono in setElemPage del padre")
    this.elemPag = elemPag;
    console.log("elemPag: ", elemPag);
    this.numPag = 0;
    this.numPaginaV = this.numPag + 1;
    this.caricaPosizioniPaginati(this.numPaginaV);
    this.divTabellaProdottiVisibile = false;
    this.divAggiungiQuantitaVisibile = false;
  }
}
