package pe.proyecto.agrario.agrario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.proyecto.agrario.agrario.dtos.MensajeDTO;
import pe.proyecto.agrario.agrario.modelo.Mensaje;

public interface IMensajeService extends ICrudGenericService<Mensaje, Long> {

    MensajeDTO saveD(MensajeDTO.MensajeCADTO dto);

    MensajeDTO updateD(MensajeDTO.MensajeCADTO dto, Long id);

    Page<Mensaje> listaPage(Pageable pageable);
}
