import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
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

  righeScontrino: Array<RigaScontrino>;
  constructor(private http: HttpClient) {}

  ngOnInit(): void {
  }

  annullaScontrino() {
    const dto: ScontrinoDto = new ScontrinoDto();
    dto.scontrino = this.scontrino;
    
   }

  vediPrezzo() { }

  chiudiScontrino() { }

  stornaUltimo() { }

  annulla() { }

  conferma() { }

}
