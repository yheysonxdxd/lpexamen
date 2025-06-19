package pe.proyecto.agrario.agrario.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.dtos.AsignacionServicioDTO;
import pe.proyecto.agrario.agrario.mappers.AsignacionServicioMapper;
import pe.proyecto.agrario.agrario.modelo.AsignacionServicio;
import pe.proyecto.agrario.agrario.modelo.Cliente;
import pe.proyecto.agrario.agrario.modelo.Servicio;
import pe.proyecto.agrario.agrario.repository.IAsignacionServicioRepository;
import pe.proyecto.agrario.agrario.repository.IClienteRepository;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IServicioRepository;
import pe.proyecto.agrario.agrario.service.IAsignacionServicioService;

import javax.sql.DataSource;

@Service
@Transactional
@RequiredArgsConstructor
public class AsignacionServicioServicieImpl extends CrudGenericServiceImpl<AsignacionServicio, Long>
        implements IAsignacionServicioService {

    @Autowired
    private DataSource dataSource;

    private final IAsignacionServicioRepository asignacionServicioRepository;
    private final IClienteRepository clienteRepository;
    private final IServicioRepository servicioRepository;
    private final AsignacionServicioMapper asignacionServicioMapper;

    @Override
    protected ICrudGenericRepository<AsignacionServicio, Long> getRepo() {
        return asignacionServicioRepository;
    }



    @Override
    public AsignacionServicioDTO saveD(AsignacionServicioDTO.AsignacionServicioCADTO dto) {
        AsignacionServicio asignacionServicio = asignacionServicioMapper.toEntityFromCADTO(dto);
        Cliente cliente =clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no  encontrado"));
        Servicio servicio = servicioRepository.findById(dto.servicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrada"));

        asignacionServicio.setCliente(cliente);
        asignacionServicio.setServicio(servicio);
        AsignacionServicio asignacionServicioGuardado = asignacionServicioRepository.save(asignacionServicio);
        return asignacionServicioMapper.toDTO(asignacionServicioGuardado);


    }

    @Override
    public AsignacionServicioDTO updateD(AsignacionServicioDTO.AsignacionServicioCADTO dto, Long id) {

        AsignacionServicio asignacionServicio = asignacionServicioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Asignacion servicio no encontrado"));
        AsignacionServicio asignacionServiciox = asignacionServicioMapper.toEntityFromCADTO(dto);
        asignacionServiciox.setIdAsignacion(asignacionServicio.getIdAsignacion());
        Cliente cliente = clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrada"));
        Servicio servicio = servicioRepository.findById(dto.servicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio  no  encontrada"));

        asignacionServiciox.setCliente(cliente);
        asignacionServiciox.setServicio(servicio);
        AsignacionServicio asignacionServicioActualizado = asignacionServicioRepository.save(asignacionServiciox);
        return asignacionServicioMapper.toDTO(asignacionServicioActualizado);

    }
    @Override
    public Page<AsignacionServicio> listaPage(Pageable pageable) {
        return asignacionServicioRepository.findAll(pageable);
    }

}
