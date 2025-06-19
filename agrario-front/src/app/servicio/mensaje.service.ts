import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from "../../environments/environment.development";

import { BehaviorSubject, Observable, Subject } from "rxjs";
import { GenericService } from './generic.service';
import { Mensaje, MensajeReport } from '../modelos/Mensaje';

@Injectable({
  providedIn: 'root'
})
export class MensajeService extends GenericService<Mensaje> {
  private mensajesSubject: Subject<MensajeReport[]> = new Subject<MensajeReport[]>();

  private mensajeSeleccionadoSubject = new BehaviorSubject<MensajeReport | null>(null);
  mensajeSeleccionado$ = this.mensajeSeleccionadoSubject.asObservable();

  constructor(protected override http: HttpClient) {
    super(http, `${environment.HOST}/mensajes`);
  }

  findAllOT(): void {
    this.http.get<MensajeReport[]>(this.url).subscribe(data => {
      this.mensajesSubject.next(data);
    });
  }

  findByIdOT(id: number) {
    return this.http.get<MensajeReport>(`${this.url}/${id}`);
  }

  seleccionarMensaje(mensaje: MensajeReport) {
    this.mensajeSeleccionadoSubject.next(mensaje);
  }

  setMensajesSubject(data: MensajeReport[]) {
    this.mensajesSubject.next(data);
  }

  getMensajesSubject(): Observable<MensajeReport[]> {
    return this.mensajesSubject.asObservable();
  }

  listPageable(p: number, s: number) {
    return this.http.get<any>(`${this.url}/pageable?page=${p}&size=${s}`);
  }
}
