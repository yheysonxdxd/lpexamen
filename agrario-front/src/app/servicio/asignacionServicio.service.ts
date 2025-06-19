import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment.development";

import { BehaviorSubject, Observable, Subject } from "rxjs";
import { GenericService } from './generic.service';
import { AsignacionServicio, AsignacionServicioReport } from '../modelos/AsignacionServicio';

@Injectable({
  providedIn: 'root'
})
export class AsignacionServicioService extends GenericService<AsignacionServicio> {
  private asignacionesSubject: Subject<AsignacionServicioReport[]> = new Subject<AsignacionServicioReport[]>();

  private asignacionSeleccionadaSubject = new BehaviorSubject<AsignacionServicioReport | null>(null);
  asignacionSeleccionada$ = this.asignacionSeleccionadaSubject.asObservable();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/asignacion-servicio`);
  }

  findAllOT(): void {
    this.http.get<AsignacionServicioReport[]>(this.url).subscribe(data => {
      this.asignacionesSubject.next(data);
    });
  }

  findByIdOT(id: number) {
    return this.http.get<AsignacionServicioReport>(`${this.url}/${id}`);
  }

  seleccionarAsignacion(asignacion: AsignacionServicioReport) {
    this.asignacionSeleccionadaSubject.next(asignacion);
  }

  setAsignacionesSubject(data: AsignacionServicioReport[]) {
    this.asignacionesSubject.next(data);
  }

  getAsignacionesSubject(): Observable<AsignacionServicioReport[]> {
    return this.asignacionesSubject.asObservable();
  }

  listPageable(p: number, s: number) {
    return this.http.get<any>(`${this.url}/pageable?page=${p}&size=${s}`);
  }
}
