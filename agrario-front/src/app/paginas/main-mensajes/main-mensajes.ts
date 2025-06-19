import { Component, OnInit, ViewChild } from '@angular/core';
import { MaterialModule } from '../../material/material.module';
import { RouterLink, RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatTableDataSource } from '@angular/material/table';
import { AsignacionServicioReport } from '../../modelos/AsignacionServicio';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MensajeService } from '../../servicio/mensaje.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-main-mensajes',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink, CommonModule],
  templateUrl: './main-mensajes.html',
  styleUrl: './main-mensajes.css'
})
export class MainMensajeComponent implements OnInit{
  

  columnsDefinitions = [
    { def: 'idMensaje', label: 'ID', hide: false },
    { def: 'contenido', label: 'Contenido', hide: false },
    { def: 'cliente', label: 'Cliente', hide: false },
    { def: 'fechaEnvio', label: 'Fecha Envio', hide: false },
    { def: 'leido', label: 'Leido', hide: false },
    { def: 'acciones', label: 'Acciones', hide: false },
  ];

  dataSource!: MatTableDataSource<AsignacionServicioReport>;
  totalElements: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private mensajeService: MensajeService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.mensajeService.listPageable(0, 2).subscribe((data) => {
      this.mensajeService.setMensajesSubject(data);
    });

    this.mensajeService.getMensajesSubject().subscribe((data) => {
      this.createTable(data);
    });
  }

  createTable(data: any) {
    this.totalElements = data.totalElements;
    this.dataSource = new MatTableDataSource(data.content);
    this.dataSource.sort = this.sort;
  }

  showMore(e: any) {
    this.mensajeService
      .listPageable(e.pageIndex, e.pageSize)
      .subscribe((data) => this.createTable(data));
  }

  eliminar(id: number) {
    if (confirm('¿Desea eliminar esta asignación?')) {
      this.mensajeService
        .delete(id)
        .pipe(switchMap(() => this.mensajeService.listPageable(0, 2)))
        .subscribe((data) => {
          this.mensajeService.setMensajesSubject(data);
          this.toastMsg('Mensaje eliminada correctamente');
        });
    }
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  getDisplayedColumns() {
    return this.columnsDefinitions.filter(cd => !cd.hide).map(cd => cd.def);
  }

  toastMsg(msg: string): void {
    this._snackBar.open(msg, 'INFO', {
      duration: 2000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
    });
  }


}
