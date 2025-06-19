package pe.proyecto.agrario.agrario.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.dtos.CitaDTO;
import pe.proyecto.agrario.agrario.mappers.CitaMapper;
import pe.proyecto.agrario.agrario.modelo.Cita;
import pe.proyecto.agrario.agrario.modelo.Cliente;
import pe.proyecto.agrario.agrario.modelo.Servicio;
import pe.proyecto.agrario.agrario.repository.IClienteRepository;
import pe.proyecto.agrario.agrario.repository.ICitaRepository;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IServicioRepository;
import pe.proyecto.agrario.agrario.service.ICitaService;

import javax.sql.DataSource;

@Transactional
@Service
@RequiredArgsConstructor
public class CitaServicieImpl extends CrudGenericServiceImpl<Cita, Long> implements ICitaService {

    @Autowired
    private DataSource dataSource;

    private final ICitaRepository citaRepository;
    private final IClienteRepository clienteRepository;
    private final IServicioRepository servicioRepository;
    private final CitaMapper citaMapper;

    @Override
    protected ICrudGenericRepository<Cita, Long> getRepo() {
        return citaRepository;
    }

    @Override
    public CitaDTO saveD(CitaDTO.CitaCADTO dto) {
        Cita cita = citaMapper.toEntityFromCADTO(dto);

        Cliente cliente = clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        Servicio servicio = servicioRepository.findById(dto.servicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));

        cita.setCliente(cliente);
        cita.setServicio(servicio);

        Cita citaGuardada = citaRepository.save(cita);
        return citaMapper.toDTO(citaGuardada);
    }

    @Override
    public CitaDTO updateD(CitaDTO.CitaCADTO dto, Long id) {
        Cita citaExistente = citaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        Cita cita = citaMapper.toEntityFromCADTO(dto);
        cita.setIdCita(citaExistente.getIdCita());

        Cliente cliente = clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
        Servicio servicio = servicioRepository.findById(dto.servicio())
                .orElseThrow(() -> new EntityNotFoundException("Servicio no encontrado"));

        cita.setCliente(cliente);
        cita.setServicio(servicio);

        Cita citaActualizada = citaRepository.save(cita);
        return citaMapper.toDTO(citaActualizada);
    }

    @Override
    public Page<Cita> listaPage(Pageable pageable) {
        return citaRepository.findAll(pageable);
    }
}
