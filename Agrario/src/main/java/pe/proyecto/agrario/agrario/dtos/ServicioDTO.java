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
public class ServicioDTO {

    private Long idServicio;

    @NotNull(message = "La descripción no puede ser nula")
    @Size(max = 300, message = "La descripción no debe exceder los 300 caracteres")
    private String descripcion;

    private LocalDateTime fechaRegistro;

    @NotNull(message = "El estado del servicio no puede ser nulo")
    @Size(max = 50, message = "El estado del servicio no debe exceder los 50 caracteres")
    private String estadoServicio;

    public record ServicioCADTO(

            Long idServicio,

            @NotNull(message = "La descripción no puede ser nula")
            @Size(max = 300, message = "La descripción no debe exceder los 300 caracteres")
            String descripcion,

            LocalDateTime fechaRegistro,

            @NotNull(message = "El estado del servicio no puede ser nulo")
            @Size(max = 50, message = "El estado del servicio no debe exceder los 50 caracteres")
            String estadoServicio

    ) {}
}
