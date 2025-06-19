package pe.proyecto.agrario.agrario.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO {

    private Long idCliente;

    @NotNull(message = "El nombre no puede ser nulo")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotNull(message = "El tipo de cliente no puede ser nulo")
    @Size(max = 50, message = "El tipo de cliente no debe exceder los 50 caracteres")
    private String tipoCliente;

    @NotNull(message = "El número de documento no puede ser nulo")
    private Long documentoIdentidad;

    @NotNull(message = "El teléfono no puede ser nulo")
    @Size(min = 6, max = 20, message = "El teléfono debe tener entre 6 y 20 caracteres")
    private String telefono;

    @Email(message = "Debe proporcionar un email válido")
    private String email;

    @NotNull(message = "El código de cliente no puede ser nulo")
    @Size(max = 20, message = "El código de cliente no debe exceder los 20 caracteres")
    private String codigoCliente;
}
