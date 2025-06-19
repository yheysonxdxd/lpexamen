
package pe.proyecto.agrario.agrario.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {

    private Long idMensaje;

    @NotNull(message = "El cliente no puede ser nulo")
    private ClienteDTO cliente;

    @NotNull(message = "El contenido no puede ser nulo")
    @Size(max = 500, message = "El contenido no debe exceder los 500 caracteres")
    private String contenido;

    private LocalDateTime fechaEnvio;

    private boolean leido;

    public record MensajeCADTO(

            Long idMensaje,

            @NotNull(message = "El ID del cliente no puede ser nulo")
            Long cliente,

            @NotNull(message = "El contenido no puede ser nulo")
            @Size(max = 500, message = "El contenido no debe exceder los 500 caracteres")
            String contenido,

            LocalDateTime fechaEnvio,

            boolean leido

    ) {}
}
