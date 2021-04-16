import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { CriterioRicercaDto } from '../dto/criterio-ricerca-dto';
import { PosizioneScaffaleDto } from '../dto/posizione-scaffale-dto';
import { PosizioneScaffale } from '../entità/posizione-scaffale';
import { Prodotto } from '../entità/prodotto';
import { AutomaScarica } from './automa-scarica-merce/automaScarica';
import { TrovaEvent } from './automa-scarica-merce/eventiScarica';

@Component({
  selector: 'app-scarica-merce',
  templateUrl: './scarica-merce.component.html',
  styleUrls: ['../theme.css']
})

export class ScaricaMerceComponent implements OnInit {
  criterioRicerca: string;
  prodotti: Prodotto[] = [];
  posizione: PosizioneScaffale;
  automa: AutomaScarica;


  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }
  
  cercaScaffale():void {
    const dto: CriterioRicercaDto = new CriterioRicercaDto();
    dto.criterio = this.criterioRicerca;
    const oss: Observable<PosizioneScaffaleDto> = this.http
    .post<PosizioneScaffaleDto>('http://localhost:8080/cerca-posizione-scaffale', dto);
    oss.subscribe(p => {
      this.posizione = p.posizione;
      this.automa.next(new TrovaEvent(this.posizione));
    });
  }

  selezionaProdotto():void {}

}
