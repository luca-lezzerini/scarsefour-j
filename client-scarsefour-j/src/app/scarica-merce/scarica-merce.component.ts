import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { EsitoDtoDue } from '../dto/esito-dto-due';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { Prodotto } from '../entità/prodotto';

@Component({
  selector: 'app-scarica-merce',
  templateUrl: './scarica-merce.component.html',
  styleUrls: ['../theme.css']
})

export class ScaricaMerceComponent implements OnInit {
  inserisciScaffale: string;
  prodotti: Prodotto[] = [];

  posizione: PosizioneScaffale;
  esito: boolean;

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }
  
  cercaScaffale():void {
    const dto: PosizioneScaffaleDto = new PosizioneScaffaleDto();
    dto.posizione = this.posizione;
    const oss: Observable<EsitoDtoDue> = this.http
    .post<EsitoDtoDue>('http://localhost:8080/cerca-posizione-scaffale', dto);
    oss.subscribe(p => {
      this.esito = p.esito;
      //ci vuole un cambiamento di stato????
    });
  }

  selezionaProdotto():void {}

}
