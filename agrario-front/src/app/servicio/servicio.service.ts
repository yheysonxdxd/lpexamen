import { Injectable } from '@angular/core';
import {GenericService} from './generic.service';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Subject} from 'rxjs';
import { Servicio } from '../modelos/Servicio';

@Injectable({
  providedIn: 'root'
})
export class ServicioService extends GenericService<Servicio>{

  private entidadSubject = new BehaviorSubject<Servicio[]>([]);
  private messageChange: Subject<string> = new Subject<string>;

  constructor(protected override http:HttpClient) {
    super(http, `${environment.HOST}/servicio`);
  }

  setEntidadChange(data: Servicio[]) { this.entidadSubject.next(data);}
  getEntidadChange() { return this.entidadSubject.asObservable();}

  setMessageChange(data: string) { this.messageChange.next(data);}
  getMessageChange() { return this.messageChange.asObservable();}

}