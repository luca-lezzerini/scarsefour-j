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

@NgModule({
  declarations: [
    AppComponent,
    AnagraficaProdottiComponent,
    AnagraficaCassiereComponent,
    HomePageComponent,
    AnagraficaScontiComponent,
    AnagraficaPosizioniComponent,
    DashboardGruppoTreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
