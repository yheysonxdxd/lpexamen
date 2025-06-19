import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterOutlet } from '@angular/router';


import { MaterialModule } from '../../material/material.module';
import { AsignacionServicioReport } from '../../modelos/AsignacionServicio';
import { AsignacionServicioService } from '../../servicio/asignacionServicio.service';

@Component({
  selector: 'app-main-asignacion',
  standalone: true,
  imports: [MaterialModule, RouterOutlet, RouterLink, CommonModule],
  templateUrl: './main-asignacion.html',
  styleUrl: './main-asignacion.css'
})
export class MainAsignacionComponent implements OnInit {

  columnsDefinitions = [
    { def: 'idAsignacion', label: 'ID', hide: false },
    { def: 'metodo', label: 'Metodo', hide: false },
    { def: 'cliente', label: 'Cliente', hide: false },
    { def: 'servicio', label: 'Servicio', hide: false },
    { def: 'fechaAsignacion', label: 'Fecha Asignacion', hide: false },
    { def: 'acciones', label: 'Acciones', hide: false },
  ];

  dataSource!: MatTableDataSource<AsignacionServicioReport>;
  totalElements: number = 0;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private asignacionService: AsignacionServicioService,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.asignacionService.listPageable(0, 2).subscribe((data) => {
      this.asignacionService.setAsignacionesSubject(data);
    });

    this.asignacionService.getAsignacionesSubject().subscribe((data) => {
      this.createTable(data);
    });
  }

  createTable(data: any) {
    this.totalElements = data.totalElements;
    this.dataSource = new MatTableDataSource(data.content);
    this.dataSource.sort = this.sort;
  }

  showMore(e: any) {
    this.asignacionService
      .listPageable(e.pageIndex, e.pageSize)
      .subscribe((data) => this.createTable(data));
  }

  eliminar(id: number) {
    if (confirm('¿Desea eliminar esta asignación?')) {
      this.asignacionService
        .delete(id)
        .pipe(switchMap(() => this.asignacionService.listPageable(0, 2)))
        .subscribe((data) => {
          this.asignacionService.setAsignacionesSubject(data);
          this.toastMsg('Asignación eliminada correctamente');
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
