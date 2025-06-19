export class Cita {
  constructor(
    public idCita: number,
    public cliente: number,       // ID del cliente
    public servicio: number,      // ID del servicio
    public fecha: string,         // formato ISO: YYYY-MM-DDTHH:mm:ss
    public estado: string
  ) {}
}
import { Cliente } from "./Cliente";
import { Servicio } from "./Servicio";

export class CitaReport {
  constructor(
    public idCita: number,
    public cliente: Cliente,      // Objeto Cliente completo
    public servicio: Servicio,    // Objeto Servicio completo
    public fecha: string,
    public estado: string
  ) {}
}
