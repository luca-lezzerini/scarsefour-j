import { Component, OnInit } from '@angular/core';
import { PosizioneScaffale } from '../entità/posizione-scaffale';

@Component({
  selector: 'app-visualizza-giacenza',
  templateUrl: './visualizza-giacenza.component.html',
  styleUrls: ['../theme.css']
})
export class VisualizzaGiacenzaComponent implements OnInit {

  posizioni: PosizioneScaffale[]= [];
  
  //variabili di visibilità
  tablePosizioniVisible: boolean;
  divPosizioniVisible: boolean;
  formDivVisible: boolean;
  formVisible: boolean;


  constructor() { }

  ngOnInit(): void {
  }

}
