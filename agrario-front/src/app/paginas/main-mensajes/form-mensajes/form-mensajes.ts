import { NgIf, NgForOf, CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Cliente } from '../../../modelos/Cliente';
import { ClienteService } from '../../../servicio/cliente.service';
import { MensajeService } from '../../../servicio/mensaje.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { switchMap } from 'rxjs';

@Component({
  selector: 'app-form-mensajes',
  standalone: true,
  imports: [MaterialModule, ReactiveFormsModule, NgIf, RouterLink, NgForOf,CommonModule],
  templateUrl: './form-mensajes.html',
  styleUrl: './form-mensajes.css'
})
export class FormMensajes implements OnInit{
  
  mensajeForm!: FormGroup;

  clientes: Cliente[] = [];

  id!: number;
  isEdit!: boolean;

  constructor(
    private serviceMensaje: MensajeService,
    private serviceCliente: ClienteService,
    private route: ActivatedRoute,
    private router: Router,
    private _snackBar: MatSnackBar
  ) {}

  ngOnInit(): void {
    this.mensajeForm = new FormGroup({
      idMensaje: new FormControl(null),
      contenido: new FormControl('', [Validators.required]),
      cliente: new FormControl(null, [Validators.required]),
      leido: new FormControl('', [Validators.required]),
      fechaEnvio: new FormControl('', [Validators.required]),
    });

    this.serviceCliente.findAll().subscribe((data) => {
      this.clientes = data;
    });



    this.route.params.subscribe((data) => {
      this.id = data['id'];
      this.isEdit = this.id != null;
      this.initForm();
    });
  }

  initForm() {
    if (this.isEdit) {
      this.serviceMensaje.findById(this.id).subscribe((data) => {
        this.mensajeForm = new FormGroup({
          idMensaje: new FormControl(data.idMensaje),
          contenido: new FormControl(data.contenido, [Validators.required]),
          cliente: new FormControl(data.cliente, [Validators.required]),
          leido: new FormControl(data.leido, [Validators.required]),
          fechaEnvio: new FormControl(data.fechaEnvio, [
            Validators.required,
          ]),
        });
      });
    }
  }

 operar() {
  const formValue = this.mensajeForm.value;

  const mensaje = {
    idMensaje: formValue.idMensaje,
    contenido: formValue.contenido,
    fechaEnvio: formValue.fechaEnvio,
    leido: formValue.leido,
    cliente: formValue.cliente.idCliente, // Asegúrate que `cliente` del formulario es un objeto Cliente
  };

  const operacion = this.isEdit
    ? this.serviceMensaje.update(mensaje.idMensaje!, mensaje)
    : this.serviceMensaje.save(mensaje);

  operacion
    .pipe(switchMap(() => this.serviceMensaje.listPageable(0, 2)))
    .subscribe({
      next: (data: any) => {
        // Si tienes un subject o algo, colócalo aquí, si no, simplemente omite esta línea:
        // this.serviceAsignacion.setAsignacionesSubject(data);
        const msg = this.isEdit ? 'Modificado correctamente' : 'Creado correctamente';
        this.toastMsg(msg);
        this.router.navigate(['pages/mensajes']); // Corrige la ruta si era 'asignacion'
      },
      error: () => {
        this.toastMsg('Ocurrió un error al guardar');
      }
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
