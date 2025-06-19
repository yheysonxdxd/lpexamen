package pe.proyecto.agrario.agrario.modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "servicio")
public class Servicio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_servicio")
    private Long idServicio;

    private String descripcion;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    @Column(name = "estado_servicio")
    private String estadoServicio;
}
