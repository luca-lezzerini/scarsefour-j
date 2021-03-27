import { Component, OnInit } from '@angular/core';
import { Automa } from '../automa/automa';
import { State } from '../automa/state';
import { PosizioneScaffale } from '../entità/posizione-scaffale';

@Component({
  selector: 'app-anagrafica-posizioni',
  templateUrl: './anagrafica-posizioni.component.html',
  styleUrls: ['./anagrafica-posizioni.component.css']
})
export class AnagraficaPosizioniComponent implements OnInit {

  posizione: PosizioneScaffale = new PosizioneScaffale();
  posizioni: PosizioneScaffale [] = [];
  criterio: string = "";
  automa: Automa;
  stato: State;

  buttonNuovaVisible: boolean = false;
  formDivVisible: boolean = false;
  campiNonEditabili: boolean = false;
  confAnnVisible: boolean = false;
  searchVisible: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

}
