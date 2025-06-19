
import { Cliente } from "./Cliente";
import { Servicio } from "./Servicio";
export class AsignacionServicio {
  constructor(
    public idAsignacion: number | null,
    public servicio: number,        // ID del servicio
    public cliente: number,         // ID del cliente
    public metodo: string,
    public fechaAsignacion: string  // Formato ISO
  ) {}
}


export class AsignacionServicioReport {
  constructor(
    public idAsignacion: number | null,
    public servicio: Servicio,        // Objeto Servicio completo
    public cliente: Cliente,          // Objeto Cliente completo
    public metodo: string,
    public fechaAsignacion: string
  ) {}
}
