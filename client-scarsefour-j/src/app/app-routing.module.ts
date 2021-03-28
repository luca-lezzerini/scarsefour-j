import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnagraficaCassiereComponent } from './anagrafica-cassiere/anagrafica-cassiere.component';
import { AnagraficaPosizioniComponent } from './anagrafica-posizioni/anagrafica-posizioni.component';
import { AnagraficaProdottiComponent } from './anagrafica-prodotti/anagrafica-prodotti.component';
import { AnagraficaScontiComponent } from './anagrafica-sconti/anagrafica-sconti.component';
import { DashboardGruppoQuattroComponent } from './dashboard-gruppo-quattro/dashboard-gruppo-quattro.component';
import { DashboardGruppoTreComponent } from './dashboard-gruppo-tre/dashboard-gruppo-tre.component';
import { DashboardGruppoUnoComponent } from './dashboard-gruppo-uno/dashboard-gruppo-uno.component';
import { HomePageComponent } from './home-page/home-page.component';

const routes: Routes = [
 { path:'anagrafica-casse', component:AnagraficaProdottiComponent},
 { path:'anagrafica-cassiere', component:AnagraficaCassiereComponent},
 { path:'anagrafica-posizioni', component:AnagraficaPosizioniComponent},
 { path:'anagrafica-prodotti', component:AnagraficaProdottiComponent},
 { path:'anagrafica-sconti', component:AnagraficaScontiComponent},
 { path:'dashboard-gruppo-tre', component:DashboardGruppoTreComponent},
 { path:'dashboard-gruppo-uno', component:DashboardGruppoUnoComponent},
 { path:'dashboard-gruppo-quattro', component:DashboardGruppoQuattroComponent},
 { path:'home-page', component:HomePageComponent},
 { path:"", redirectTo:"/home-page", pathMatch: "full"}
 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
