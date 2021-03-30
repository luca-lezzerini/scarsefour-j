import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { ProdottoDto } from '../dto/prodotto-dto';
import { ScontrinoDto } from '../dto/scontrino-dto';
import { Prodotto } from '../entità/prodotto';
import { RigaScontrino } from '../entità/riga-scontrino';
import { Scontrino } from '../entità/scontrino';

@Component({
  selector: 'app-dashboard-gruppo-due',
  templateUrl: './dashboard-gruppo-due.component.html',
  styleUrls: ['../theme.css']
})
export class DashboardGruppoDueComponent implements OnInit {

  scontrino: Scontrino = new Scontrino();

  prezzo: number;
  ultimoElemento: Prodotto;
  barcode: string;
  prodotto: Prodotto = new Prodotto();

  righeScontrino: Array<RigaScontrino>;
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
  }

  annullaScontrino() {
    const dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    
   }

  vediPrezzo() {
    let dto: CriterioRicercaDto = new CriterioRicercaDto();
    console.log("Codice: " + this.barcode);
    dto.criterio = this.barcode;
    let oss: Observable<ProdottoDto> = this.http.post<ProdottoDto>
      ('http://localhost:8080/vedi-prezzo-due', dto);
    oss.subscribe(t => this.prodotto = t.prodotto);
    this.prezzo = this.prodotto.prezzo;
   }

  chiudiScontrino() { }

  stornaUltimo() { }

  annulla() { }

  conferma() { }

}
