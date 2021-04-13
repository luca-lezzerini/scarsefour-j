import { Component, OnInit } from '@angular/core';
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

  constructor() { }

  ngOnInit(): void {
  }


  cercaPosizioneScaffale() {

  }


  seleziona(p){

  }

  cercaProdottiNonAssociati(){

  }

  associa(p){

  }
}
