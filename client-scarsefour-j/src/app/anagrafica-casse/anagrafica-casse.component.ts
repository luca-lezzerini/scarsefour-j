import { Component, OnInit } from '@angular/core';
import {AutomabileCrud} from '../automa/state';
import {HttpClient} from '@angular/common/http';
import {Cassa} from '../entità/cassa';

@Component({
  selector: 'app-anagrafica-casse',
  templateUrl: './anagrafica-casse.component.html',
  styleUrls: ['../theme.css']
})
export class AnagraficaCasseComponent implements OnInit, AutomabileCrud {
  cassa: Cassa = new Cassa();
  searchCriterion: string;
  // campi visibilità
  buttonNuovaVisible: boolean;
  confAnnVisible: boolean;
  searchVisible: boolean;
  listaCasse: Cassa[] = [];
  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  aggiungiAction(): void {
  }

  goToAggiungi(): void {
  }

  goToModifica(): void {
  }

  goToRicerca(): void {
  }

  goToRimuovi(): void {
  }

  goToVisualizza(): void {
  }

  modificaAction(): void {
  }

  rimuoviAction(): void {
  }

  nuova(): void {
  }

  modifica(): void {
  }

  conferma(): void {
  }

  annulla(): void {
  }

  rimuovi(): void {
  }

  cerca(): void {
  }

  seleziona(c: Cassa): void {
  }
}
