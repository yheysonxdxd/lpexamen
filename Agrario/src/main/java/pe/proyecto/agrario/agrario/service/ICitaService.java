package pe.proyecto.agrario.agrario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.proyecto.agrario.agrario.dtos.CitaDTO;
import pe.proyecto.agrario.agrario.modelo.Cita;

public interface ICitaService extends ICrudGenericService<Cita, Long> {

    CitaDTO saveD(CitaDTO.CitaCADTO dto);

    CitaDTO updateD(CitaDTO.CitaCADTO dto, Long id);

    Page<Cita> listaPage(Pageable pageable);
}
