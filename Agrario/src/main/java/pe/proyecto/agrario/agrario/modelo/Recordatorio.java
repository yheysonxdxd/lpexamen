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
@Table(name = "recordatorio")
public class Recordatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recordatorio")
    private Long idRecordatorio;

    @ManyToOne
    @JoinColumn(name = "id_cita", nullable = false, foreignKey = @ForeignKey(name = "cita_recordatorio_fk"))
    private Cita cita;

    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    private boolean enviado;
}

