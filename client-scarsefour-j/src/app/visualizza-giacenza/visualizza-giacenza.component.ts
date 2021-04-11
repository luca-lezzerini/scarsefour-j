import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { SkuScaffale } from '../entità/sku-scaffale';
import { ListaPosizioneScaffaleDto } from '../dto/lista-posizione-scaffale-dto';
import { HttpClient } from '@angular/common/http';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { Prodotto } from '../entità/prodotto';

@Component({
  selector: 'app-visualizza-giacenza',
  templateUrl: './visualizza-giacenza.component.html',
  styleUrls: ['../theme.css']
})
export class VisualizzaGiacenzaComponent implements OnInit {

  posizioni: PosizioneScaffale[] = [];
  sku: SkuScaffale[] = [];
  prodotti: Prodotto[] = [];
  posizione: PosizioneScaffale;

  //variabili di visibilità
  // tablePosizioniVisible: boolean = false;
  // divPosizioniVisible: boolean = false;
  formDivVisible: boolean;
  formVisible: boolean;


  constructor(private http: HttpClient) {
    this.aggiornaPosizioni();
  }

  ngOnInit(): void {
  }

  aggiornaPosizioni() {
    let oss: Observable<ListaPosizioneScaffaleDto> = this.http.get<ListaPosizioneScaffaleDto>(
      'http://localhost:8080/aggiorna-posizioni-giacenza');
      oss.subscribe((c) => (this.posizioni = c.listaPosizioni));
  }

  // visualizzaGiacenza() { 
  //   let dto : PosizioneScaffaleDto = new PosizioneScaffaleDto();
  //   dto.posizione = this.posizione;
  //   let oss: Observable<ListaProdottiDto> = this.http.get<ListaProdottiDto>(
  //     'http://localhost:8080/visualizza-giacenza');
  //     oss.subscribe(p =>{
  //       this.prodotti=p.listaProdotti;
  //     });
  // }

}
