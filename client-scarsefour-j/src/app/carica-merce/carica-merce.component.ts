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
import { Prodotto } from '../entità/prodotto';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';

@Component({
  selector: 'app-carica-merce',
  templateUrl: './carica-merce.component.html',
  styleUrls: ['../theme.css'],
})
export class CaricaMerceComponent implements OnInit {
  listaPosizioni: PosizioneScaffale[] = [];
  listaProdotti: Prodotto[] = [];
  tabellaProdottiVisibile: boolean = false;
  divAggiungiQuantita: boolean = false;
  quantita: number;
  prodottoSel: Prodotto;
  posizioneScaffaleSel: PosizioneScaffale;
  esito: string;
  criterio: string;
  
  constructor(private http: HttpClient) {
    this.caricaPosizioni();
  }

  ngOnInit(): void {}

  seleziona(pos: PosizioneScaffale) {}

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
    dto.criterio=this.criterio;
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

  //  oss.subscribe(r => this.posizioni = r.listaPosizioni);

  selezionaPS(ps: PosizioneScaffale) {
    this.posizioneScaffaleSel = ps;
    console.log('sono in carica prodotti');
    let dto: IdPosizioneScaffaleDto = new IdPosizioneScaffaleDto();
    dto.id = ps.id;
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>(
      'http://localhost:8080/carica-prodotti',
      dto
    );
    oss.subscribe((p) => {
      console.log('ritorno dal server carica prodotti : ', p.listaProdotti);
      this.listaProdotti = p.listaProdotti;
      this.tabellaProdottiVisibile = true;
    });
  }

  selezionaProdotto(p: Prodotto) {
    this.prodottoSel = p;
    this.divAggiungiQuantita = true;
  }

  caricaMerce(quantita: number) {
    let dto: CaricaMerceDto = new CaricaMerceDto();
    dto.prodotto = this.prodottoSel;
    dto.posizioneScaffale = this.posizioneScaffaleSel;
    dto.quantita = quantita;
    let oss: Observable<EsitoDtoDue> = this.http.post<EsitoDtoDue>(
      'http://localhost:8080/carica-merce',
      dto
    );
    oss.subscribe((p) => {
      if (p.esito) {
        console.log('Merce caricata correttamente');
        this.esito = 'Merce caricata correttamente';
      } else {
        console.log('Elaborazione terminata con errori');
        this.esito = 'EElaborazione terminata con errori';
      }
    });
  }
}
