import { Component, OnInit } from '@angular/core';
import { Automa } from './automa/automa';
import { AutomabileCrud } from './automa/state';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent implements OnInit {
  title = 'client-scarsefour-j';
  automa: Automa;

  ngOnInit() {

  }
}
