import { ClienteService } from './../../../servicio/cliente.service';
import { Component, OnInit } from '@angular/core';
import { MaterialModule } from '../../../material/material.module';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Cliente } from '../../../modelos/Cliente';
import { switchMap } from 'rxjs';

import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} 
from '@angular/forms';

@Component({
  selector: 'app-formx-cliente',
  standalone: true,
  imports: [MaterialModule, FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './formx-cliente.html',
  styleUrl: './formx-cliente.css',
})
export class FormxClienteComponent implements OnInit {
  form!: FormGroup;
  isEdit: boolean = false;
  id!: number;

  constructor(
    private clienteService: ClienteService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.form = new FormGroup({
      idCliente: new FormControl(null), // null por defecto, no 0

      nombre: new FormControl('', [
        Validators.required,
        Validators.minLength(2),
        Validators.maxLength(100),
      ]),

      tipoCliente: new FormControl('', [
        Validators.required,
        Validators.maxLength(50),
      ]),

      documentoIdentidad: new FormControl('', [
        Validators.required,
        Validators.pattern('^[0-9]{1,20}$'), // números, máximo 20 dígitos
      ]),

      telefono: new FormControl('', [
        Validators.required,
        Validators.minLength(6),
        Validators.maxLength(20),
      ]),

      email: new FormControl('', [Validators.required, Validators.email]),

      codigoCliente: new FormControl('', [
        Validators.required,
        Validators.maxLength(20),
      ]),
    });

    this.route.params.subscribe((params) => {
      this.id = Number(params['id']);
      this.isEdit = !isNaN(this.id); // Si no es un número, no es edición
      if (this.isEdit) {
        this.initForm();
      }
    });
  }

  initForm() {
    if (this.isEdit) {
      this.clienteService.findById(this.id).subscribe((data) => {
        this.form.setValue({
          idCliente: data.idCliente,
          nombre: data.nombre,
          tipoCliente: data.tipoCliente,
          documentoIdentidad: data.documentoIdentidad,
          telefono: data.telefono,
          email: data.email,
          codigoCliente: data.codigoCliente,
        });
      });
    }
  }

  operate() {
    const cliente: Cliente = new Cliente(
      this.isEdit ? this.id : null, // solo enviar ID si es edición
      this.form.value['nombre'],
      this.form.value['tipoCliente'],
      this.form.value['documentoIdentidad'],
      this.form.value['telefono'],
      this.form.value['email'],
      this.form.value['codigoCliente']
    );

    if (this.isEdit) {
      this.clienteService.update(this.id, cliente).subscribe(() => {
        this.clienteService.findAll().subscribe((data) => {
          this.clienteService.setEntidadChange(data);
          this.clienteService.setMessageChange('Actualizado correctamente');
          this.router.navigate(['pages/cliente']);
        });
      });
    } else {
      this.clienteService
        .save(cliente)
        .pipe(switchMap(() => this.clienteService.findAll()))
        .subscribe((data) => {
          this.clienteService.setEntidadChange(data);
          this.clienteService.setMessageChange('Creado correctamente');
          this.router.navigate(['pages/cliente']);
        });
    }
  }

  get f() {
    return this.form.controls;
  }
}
