import { Cliente } from './../../modelos/Cliente';
import { Component, OnInit, ViewChild } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { MaterialModule } from '../../material/material.module';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { ClienteService } from '../../servicio/cliente.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-main-cliente',
  standalone: true,
  imports: [RouterOutlet, MaterialModule, RouterLink],
  templateUrl: './main-cliente.html',
  styleUrl: './main-cliente.css',
})
export class MainClienteComponent implements OnInit {
  dataSource!: MatTableDataSource<Cliente>;
  displayedColumns = [
    { def: 'idCliente', label: 'idCliente', hide: false },
    { def: 'nombre', label: 'Nombre', hide: false },
    { def: 'tipoCliente', label: 'Tipo cliente', hide: false },
    { def: 'documentoIdentidad', label: 'DNI', hide: false },
    { def: 'telefono', label: 'Telefono', hide: false },
    { def: 'email', label: 'email', hide: false },
    { def: 'codigoCliente', label: 'Codigo Cliente', hide: false },
    { def: 'accion', label: 'accion', hide: false },
  ];
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  constructor(
    private clienteService: ClienteService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.clienteService.findAll().subscribe((data) => {
      this.clienteService.setEntidadChange(data);
    });
    this.clienteService.getEntidadChange().subscribe((data) => {
      this.createTable(data);
    });
    this.clienteService
      .getMessageChange()
      .subscribe((data) =>
        this._snackBar.open(data, 'INFO', { duration: 2000 })
      );
  }

  createTable(cliente: Cliente[]) {
    this.dataSource = new MatTableDataSource(cliente);
    this.dataSource.sort = this.sort;
    this.dataSource.paginator = this.paginator;
  }

  getDisplayedColumns() {
    return this.displayedColumns.filter((d) => !d.hide).map((d) => d.def);
  }

  applyFilter(filter: any) {
    this.dataSource.filter = filter.target.value.trim().toLowerCase();
  }

  delete(id: number) {
    this.clienteService
      .delete(id)
      .pipe(switchMap(() => this.clienteService.findAll()))
      .subscribe((res) => {
        this.clienteService.setEntidadChange(res);
        this.clienteService.setMessageChange('DELETED!');
      });
  }
}
