package pe.proyecto.agrario.agrario.service.impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.modelo.Cliente;
import pe.proyecto.agrario.agrario.repository.IClienteRepository;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.service.IClienteService;

@Transactional
@Service
@RequiredArgsConstructor
public class ClienteServicieImpl extends CrudGenericServiceImpl<Cliente,Long> implements IClienteService {
    private final IClienteRepository clienteRepository;
    @Override
    protected ICrudGenericRepository<Cliente, Long> getRepo() {
        return clienteRepository;
    }
}
