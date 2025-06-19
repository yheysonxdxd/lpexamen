export class Cliente {
  constructor(
    public idCliente: number | null,  // <-- aquí el cambio
    public nombre: string,
    public tipoCliente: string,
    public documentoIdentidad: number,
    public telefono: string,
    public email: string,
    public codigoCliente: string
  ) {}
}
