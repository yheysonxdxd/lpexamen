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
public class CitaDTO {

    private Long idCita;

    @NotNull(message = "El cliente no puede ser nulo")
    private ClienteDTO cliente;

    @NotNull(message = "El servicio no puede ser nulo")
    private ServicioDTO servicio;

    @NotNull(message = "La fecha no puede ser nula")
    private LocalDateTime fecha;

    @Size(max = 50, message = "El estado no debe exceder los 50 caracteres")
    private String estado;

    public record CitaCADTO(

            Long idCita,

            @NotNull(message = "El ID del cliente no puede ser nulo")
            Long cliente,

            @NotNull(message = "El ID del servicio no puede ser nulo")
            Long servicio,

            @NotNull(message = "La fecha no puede ser nula")
            LocalDateTime fecha,

            @Size(max = 50, message = "El estado no debe exceder los 50 caracteres")
            String estado

    ) {}
}
