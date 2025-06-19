import { Cliente } from "./Cliente";

export class Mensaje {
  constructor(
    public idMensaje: number,
    public cliente: number, // ID del cliente
    public contenido: string,
    public fechaEnvio: string, // formato ISO: YYYY-MM-DDTHH:mm:ss
    public leido: boolean
  ) {}
}

export class MensajeReport {
  constructor(
    public idMensaje: number,
    public cliente: Cliente, // Objeto completo
    public contenido: string,
    public fechaEnvio: string,
    public leido: boolean
  ) {}
}
