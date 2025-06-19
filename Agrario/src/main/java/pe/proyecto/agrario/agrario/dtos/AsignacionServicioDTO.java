    package pe.proyecto.agrario.agrario.dtos;

    import jakarta.validation.constraints.NotNull;
    import jakarta.validation.constraints.Size;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import pe.proyecto.agrario.agrario.modelo.AsignacionServicio;

    import java.time.LocalDate;
    import java.time.LocalDateTime;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class AsignacionServicioDTO {

        private Long idAsignacion;

        @NotNull(message = "El servicio no puede ser nulo")
        private ServicioDTO servicio;

        @NotNull(message = "El cliente no puede ser nulo")
        private ClienteDTO cliente;

        @Size(max = 100, message = "El método no debe exceder los 100 caracteres")
        private String metodo;

        private LocalDate fechaAsignacion;

        public record AsignacionServicioCADTO(

                Long idAsignacion,

                @NotNull(message = "El ID del servicio no puede ser nulo")
                Long servicio,

                @NotNull(message = "El ID del cliente no puede ser nulo")
                Long cliente,

                @Size(max = 100, message = "El método no debe exceder los 100 caracteres")
                String metodo,

                LocalDate fechaAsignacion

        ) {}
    }
