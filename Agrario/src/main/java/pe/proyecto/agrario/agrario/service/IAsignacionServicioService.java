package pe.proyecto.agrario.agrario.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.proyecto.agrario.agrario.dtos.AsignacionServicioDTO;
import pe.proyecto.agrario.agrario.modelo.AsignacionServicio;

public interface IAsignacionServicioService extends ICrudGenericService<AsignacionServicio, Long> {

    AsignacionServicioDTO saveD(AsignacionServicioDTO.AsignacionServicioCADTO dto);

    AsignacionServicioDTO updateD(AsignacionServicioDTO.AsignacionServicioCADTO dto, Long id);

    Page<AsignacionServicio> listaPage(Pageable pageable);
}
