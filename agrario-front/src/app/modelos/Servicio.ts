export class Servicio {
  constructor(
    public idServicio: number,
    public descripcion: string,
    public fechaRegistro: string, // formato ISO: YYYY-MM-DDTHH:mm:ss
    public estadoServicio: string
  ) {}
}
