import { Component, OnInit } from '@angular/core';
import { Prodotto } from '../entità/prodotto';

@Component({
  selector: 'app-scarica-merce',
  templateUrl: './scarica-merce.component.html',
  styleUrls: ['./scarica-merce.component.css']
})

export class ScaricaMerceComponent implements OnInit {
  inserisciScaffale: string;
  prodotti: Prodotto[] = [];

  constructor() { }

  ngOnInit(): void {
  }
  
  cercaScaffale():void {}
  selezionaProdotto():void {}

}
