package pe.proyecto.agrario.agrario.service.impl;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.dtos.MensajeDTO;
import pe.proyecto.agrario.agrario.mappers.MensajeMapper;
import pe.proyecto.agrario.agrario.modelo.Cliente;
import pe.proyecto.agrario.agrario.modelo.Mensaje;
import pe.proyecto.agrario.agrario.repository.IClienteRepository;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IMensajeRepository;

import pe.proyecto.agrario.agrario.service.IMensajeService;

import javax.sql.DataSource;

@Service
@Transactional
@RequiredArgsConstructor
public class MensajeServiceImpl extends CrudGenericServiceImpl<Mensaje, Long> implements IMensajeService {

    @Autowired
    private DataSource dataSource;

    private final IMensajeRepository mensajeRepository;
    private final IClienteRepository clienteRepository;
    private final MensajeMapper mensajeMapper;

    @Override
    protected ICrudGenericRepository<Mensaje, Long> getRepo() {
        return mensajeRepository;
    }

    @Override
    public MensajeDTO saveD(MensajeDTO.MensajeCADTO dto) {
        Mensaje mensaje = mensajeMapper.toEntityFromCADTO(dto);
        Cliente cliente =clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no  encontrado"));
        mensaje.setCliente(cliente);

        Mensaje mensajeGuardado = mensajeRepository.save(mensaje);
        return mensajeMapper.toDTO(mensajeGuardado);


    }

    @Override
    public MensajeDTO updateD(MensajeDTO.MensajeCADTO dto, Long id) {

        Mensaje mensaje = mensajeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mensaje no encontrado"));
        Mensaje mensajex = mensajeMapper.toEntityFromCADTO(dto);
        mensajex.setIdMensaje(mensaje.getIdMensaje());
        Cliente cliente = clienteRepository.findById(dto.cliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrada"));
        mensajex.setCliente(cliente);

        Mensaje mensajeActualizado = mensajeRepository.save(mensajex);
        return mensajeMapper.toDTO(mensajeActualizado);

    }

    @Override
    public Page<Mensaje> listaPage(Pageable pageable) {
        return mensajeRepository.findAll(pageable);
    }
}
