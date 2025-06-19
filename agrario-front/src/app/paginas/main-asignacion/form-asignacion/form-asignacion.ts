import { AsyncPipe, CommonModule, NgForOf, NgIf } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import {
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';

import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';
import { Cliente } from '../../../modelos/Cliente';
import { Servicio } from '../../../modelos/Servicio';
import { ClienteService } from '../../../servicio/cliente.service';
import { ServicioService } from '../../../servicio/servicio.service';
import { AsignacionServicioService } from '../../../servicio/asignacionServicio.service';
import { AsignacionServicio } from '../../../modelos/AsignacionServicio';

@Component({
  selector: 'app-form-asignacion',

  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, NgIf, RouterLink, NgForOf,CommonModule],
  templateUrl: './form-asignacion.html',
  styleUrl: './form-asignacion.css'
})
export class FormxAsignacion implements OnInit {
  asignacionForm!: FormGroup;

  clientes: Cliente[] = [];
  servicios: Servicio[] = [];

  id!: number;
  isEdit!: boolean;

  constructor(
    private serviceAsignacion: AsignacionServicioService,
    private serviceCliente: ClienteService,
    private serviceServicio: ServicioService,
    private route: ActivatedRoute,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.asignacionForm = new FormGroup({
      idAsignacion: new FormControl(null),
      metodo: new FormControl('', [Validators.required]),
      cliente: new FormControl(null, [Validators.required]),
      servicio: new FormControl(null, [Validators.required]),
      fechaAsignacion: new FormControl('', [Validators.required]),
    });

    this.serviceCliente.findAll().subscribe((data) => {
      this.clientes = data;
    });

    this.serviceServicio.findAll().subscribe((data) => {
      this.servicios = data;
    });

    this.route.params.subscribe((data) => {
      this.id = data['id'];
      this.isEdit = this.id != null;
      this.initForm();
    });
  }

  initForm() {
    if (this.isEdit) {
      this.serviceAsignacion.findById(this.id).subscribe((data) => {
        this.asignacionForm = new FormGroup({
          idAsignacion: new FormControl(data.idAsignacion),
          metodo: new FormControl(data.metodo, [Validators.required]),
          cliente: new FormControl(data.cliente, [Validators.required]),
          servicio: new FormControl(data.servicio, [Validators.required]),
          fechaAsignacion: new FormControl(data.fechaAsignacion, [
            Validators.required,
          ]),
        });
      });
    }
  }

 operar() {
  const formValue = this.asignacionForm.value;

  const asignacion: AsignacionServicio = {
    idAsignacion: formValue.idAsignacion,
    metodo: formValue.metodo,
    fechaAsignacion: formValue.fechaAsignacion,
    cliente: formValue.cliente.idCliente,       // 👈 solo el ID
    servicio: formValue.servicio.idServicio      // 👈 solo el ID
  };

  const operacion = this.isEdit
    ? this.serviceAsignacion.update(asignacion.idAsignacion!, asignacion)
    : this.serviceAsignacion.save(asignacion);

  operacion
    .pipe(switchMap(() => this.serviceAsignacion.listPageable(0, 2)))
    .subscribe((data) => {
      this.serviceAsignacion.setAsignacionesSubject(data);
      const mensaje = this.isEdit
        ? 'Modificado correctamente'
        : 'Creado correctamente';
      this.toastMsg(mensaje);
      this.router.navigate(['pages/asignacion']);
    });
}


  toastMsg(msg: string): void {
    this._snackBar.open(msg, 'INFO', {
      duration: 2000,
      verticalPosition: 'top',
      horizontalPosition: 'right',
    });
  }
}
