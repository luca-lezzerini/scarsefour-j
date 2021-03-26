import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AnagraficaProdottiComponent } from './anagrafica-prodotti/anagrafica-prodotti.component';

const routes: Routes = [
 { path:"/anagrafica-prodotti", component:AnagraficaProdottiComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
