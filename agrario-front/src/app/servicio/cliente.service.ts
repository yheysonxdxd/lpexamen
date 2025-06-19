import { Injectable } from '@angular/core';
import {GenericService} from './generic.service';
import {Cliente} from '../modelos/Cliente';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {BehaviorSubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClienteService extends GenericService<Cliente>{

  private entidadSubject = new BehaviorSubject<Cliente[]>([]);
  private messageChange: Subject<string> = new Subject<string>;

  constructor(protected override http:HttpClient) {
    super(http, `${environment.HOST}/cliente`);
  }

  setEntidadChange(data: Cliente[]) { this.entidadSubject.next(data);}
  getEntidadChange() { return this.entidadSubject.asObservable();}

  setMessageChange(data: string) { this.messageChange.next(data);}
  getMessageChange() { return this.messageChange.asObservable();}

}
