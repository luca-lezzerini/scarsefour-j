import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { SkuScaffale } from '../entità/sku-scaffale';
import { ListaPosizioneScaffaleDto } from '../dto/lista-posizione-scaffale-dto';
import { HttpClient } from '@angular/common/http';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { Prodotto } from '../entità/prodotto';
import { ListaGiacenzaDto } from '../dto/lista-giacenza-dto';
import { Giacenza } from '../entità/giacenza';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { DatiPageDto } from '../dto/dati-page-dto';
import { PageDto } from '../dto/page-dto';

@Component({
  selector: 'app-visualizza-giacenza',
  templateUrl: './visualizza-giacenza.component.html',
  styleUrls: ['../theme.css']
})
export class VisualizzaGiacenzaComponent implements OnInit {

  posizioni: PosizioneScaffale[] = [];
  listaGiacenza: Giacenza[] = [];
  posizione: PosizioneScaffale;
  criterio: string;

  //variabili di visibilità
  // tablePosizioniVisible: boolean = false;
  formDivVisible: boolean;
  formVisible: boolean;

  //variabili paginazione
  numPag: number = 0;
  numPaginaV: number = 1;
  elemPag: number = 10;
  totalPages: number;
  totalElements: number;
  first: boolean;
  last: boolean;

  numberOfElements: number;


  constructor(private http: HttpClient) {
    this.caricaPosizioniPaginati(this.numPaginaV);
    console.log(this.numPaginaV);
  }

  ngOnInit(): void {
  }

  aggiornaPosizioni() {
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.get<ListaPosizioneScaffaleDto>(
      'http://localhost:8080/aggiorna-posizioni-giacenza');
      oss.subscribe((c) => (this.posizioni = c.listaPosizioni));
  }

   visualizzaGiacenza(p: PosizioneScaffale) { 
     let dto : PosizioneScaffaleDto = new PosizioneScaffaleDto();
     dto.posizione = p;
     let oss: Observable<ListaGiacenzaDto> = this.http.post<ListaGiacenzaDto>(
       'http://localhost:8080/visualizza-giacenza', dto);
       oss.subscribe(p =>{
         this.listaGiacenza=p.listaGiacenza;
       });

       this.formDivVisible = true;
       this.formVisible = true;
   }

   cerca(){
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.criterio;
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>('http://localhost:8080/ricerca-posizioni', dto);
    oss.subscribe(r => this.posizioni = r.listaPosizioni);
   }

    //metodi paginazione
  caricaPosizioniPaginati(numPaginaV: number) {
    this.numPaginaV = numPaginaV;
    console.log(numPaginaV);
    if (this.numPaginaV > 0) {
      let dto: DatiPageDto = new DatiPageDto();
      dto.numPag = this.numPaginaV - 1;
      dto.elemPag = this.elemPag;
      let oss: Observable<PageDto> = this.http.post<PageDto>
        ("http://localhost:8080/carica-posizioni-paginati", dto);
      oss.subscribe(v => {
        this.posizioni = v.listaElemPag.content;
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
    //VISIBILITA??
  }

}
