import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ListaPosizioneScaffaleDto } from '../dto/lista-posizione-scaffale-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { ProdottoPosizioneDto } from '../dto/prodotto-posizione-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { Prodotto } from '../entità/prodotto';

@Component({
  selector: 'app-associa-prodotto-a-scaffale',
  templateUrl: './associa-prodotto-a-scaffale.component.html',
  styleUrls: ['../theme.css']
})
export class AssociaProdottoAScaffaleComponent implements OnInit {

  //Proprietà
  prodotto: Prodotto = new Prodotto();
  posizioni: PosizioneScaffale[] = [];
  posizioneScaffale: PosizioneScaffale=new PosizioneScaffale();
  prodotti: Prodotto[] = [];
  code:string="";

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


  cercaPosizioneScaffale() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.code;
    let obs: Observable<ListaPosizioneScaffaleDto> = this.http.post<ListaPosizioneScaffaleDto>("http://localhost:8080/seleziona-posizioni", dto);
    obs.subscribe(p => this.posizioni = p.listaPosizioni);
  }


  seleziona(p) {
    this.posizioneScaffale=p;

  }

  cercaProdottiNonAssociati() {
    let dto: PosizioneScaffaleDto = new PosizioneScaffaleDto();
    dto.posizione = this.posizioneScaffale;
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("http://localhost:8080/cerca-prodotti-non-associati", dto);
    oss.subscribe(s => this.prodotti = s.listaProdotti);



  }

  associa(p) {
    let dto: ProdottoPosizioneDto = new ProdottoPosizioneDto();
    dto.posizione = this.posizioneScaffale
    this.prodotto = p;
    dto.prodotto = this.prodotto;
    
    let ox:Observable<PosizioneScaffaleDto> = this.http.post<PosizioneScaffaleDto>("http://localhost:8080/associa-prodotto-scaffale", dto );
    ox.subscribe(x => this.posizioneScaffale = x.posizione);

  }
}
