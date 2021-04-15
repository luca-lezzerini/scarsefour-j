import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AnagraficaProdottiComponent } from './anagrafica-prodotti/anagrafica-prodotti.component';
import { FormsModule } from '@angular/forms';
import { AnagraficaCassiereComponent } from './anagrafica-cassiere/anagrafica-cassiere.component';
import { HomePageComponent } from './home-page/home-page.component';
import { AnagraficaScontiComponent } from './anagrafica-sconti/anagrafica-sconti.component';
import { AnagraficaPosizioniComponent } from './anagrafica-posizioni/anagrafica-posizioni.component';
import { DashboardGruppoTreComponent } from './dashboard-gruppo-tre/dashboard-gruppo-tre.component';
import { HttpClientModule } from '@angular/common/http';
import { DashboardGruppoUnoComponent } from './dashboard-gruppo-uno/dashboard-gruppo-uno.component';
import { DashboardGruppoQuattroComponent } from './dashboard-gruppo-quattro/dashboard-gruppo-quattro.component';
import { DashboardGruppoDueComponent } from './dashboard-gruppo-due/dashboard-gruppo-due.component';
import { ToolbarMenuComponent } from './toolbar-menu/toolbar-menu.component';
import {AnagraficaCasseComponent} from './anagrafica-casse/anagrafica-casse.component';
import { VisualizzaGiacenzaComponent } from './visualizza-giacenza/visualizza-giacenza.component';
import { AssociaProdottoAScaffaleComponent } from './associa-prodotto-a-scaffale/associa-prodotto-a-scaffale.component';
import { ScaricaMerceComponent } from './scarica-merce/scarica-merce.component';
import { CaricaMerceComponent } from './carica-merce/carica-merce.component';
import { PaginationComponent } from './pagination/pagination.component';

@NgModule({
  declarations: [
    AppComponent,
    AnagraficaProdottiComponent,
    AnagraficaCassiereComponent,
    HomePageComponent,
    AnagraficaScontiComponent,
    AnagraficaPosizioniComponent,
    DashboardGruppoTreComponent,
    DashboardGruppoUnoComponent,
    DashboardGruppoQuattroComponent,
    DashboardGruppoDueComponent,
    ToolbarMenuComponent,
    AnagraficaCasseComponent,
    VisualizzaGiacenzaComponent,
    AssociaProdottoAScaffaleComponent,
    ScaricaMerceComponent,
    CaricaMerceComponent,
    PaginationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
