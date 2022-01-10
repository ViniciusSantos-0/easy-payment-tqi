import { Component, OnInit } from '@angular/core';
import { Cliente } from '../models/cliente';
import { ClienteService } from '../services/cliente.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  clientes: Cliente[] = [];

  constructor(
    private clienteService: ClienteService
  ) { }

  ngOnInit(): void {
    this.clienteService.list().subscribe(data => {
      this.clientes = data;
    });
  }

}
