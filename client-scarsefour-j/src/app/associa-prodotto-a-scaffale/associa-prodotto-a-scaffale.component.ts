import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { ListaProdottiDto } from '../dto/lista-prodotti-dto';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { Prodotto } from '../entità/prodotto';

@Component({
  selector: 'app-associa-prodotto-a-scaffale',
  templateUrl: './associa-prodotto-a-scaffale.component.html',
  styleUrls: ['./associa-prodotto-a-scaffale.component.css']
})
export class AssociaProdottoAScaffaleComponent implements OnInit {

  //Proprietà
  prodotto: Prodotto = new Prodotto();
  posizioni: PosizioneScaffale[] = [];
  posizioneScaffale: PosizioneScaffale;
  prodotti: Prodotto[] = [];

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }


  cercaPosizioneScaffale() {

  }


  seleziona(p){

  }

  cercaProdottiNonAssociati(){
    let dto: PosizioneScaffaleDto = new PosizioneScaffaleDto();
    dto.posizione = this.posizioneScaffale;
    let oss: Observable<ListaProdottiDto> = this.http.post<ListaProdottiDto>("http://localhost:8080/cerca-prodotti-non-associati", dto);
   oss.subscribe(s=> this.prodotti = s.listaProdotti);



  }

  associa(p){

  }
}
