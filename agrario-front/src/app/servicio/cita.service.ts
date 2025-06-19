import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment.development";

import { BehaviorSubject, Observable, Subject } from "rxjs";
import { GenericService } from './generic.service';
import { Cita, CitaReport } from '../modelos/Cita';

@Injectable({
  providedIn: 'root'
})
export class CitaService extends GenericService<Cita> {
  private citasSubject: Subject<CitaReport[]> = new Subject<CitaReport[]>();

  private citaSeleccionadaSubject = new BehaviorSubject<CitaReport | null>(null);
  citaSeleccionada$ = this.citaSeleccionadaSubject.asObservable();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/citas`);
  }

  findAllOT(): void {
    this.http.get<CitaReport[]>(this.url).subscribe(data => {
      this.citasSubject.next(data);
    });
  }

  findByIdOT(id: number) {
    return this.http.get<CitaReport>(`${this.url}/${id}`);
  }

  seleccionarCita(cita: CitaReport) {
    this.citaSeleccionadaSubject.next(cita);
  }

  setCitasSubject(data: CitaReport[]) {
    this.citasSubject.next(data);
  }

  getCitasSubject(): Observable<CitaReport[]> {
    return this.citasSubject.asObservable();
  }

  listPageable(p: number, s: number) {
    return this.http.get<any>(`${this.url}/pageable?page=${p}&size=${s}`);
  }
}
