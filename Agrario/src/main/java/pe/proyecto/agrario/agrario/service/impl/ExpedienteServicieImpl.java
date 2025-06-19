package pe.proyecto.agrario.agrario.service.impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.modelo.Expediente;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IExpedienteRepository;
import pe.proyecto.agrario.agrario.service.IExpedienteService;

@Transactional
@Service
@RequiredArgsConstructor
public class ExpedienteServicieImpl extends CrudGenericServiceImpl<Expediente,Long> implements IExpedienteService {
    private final IExpedienteRepository expedienteRepository;
    @Override
    protected ICrudGenericRepository<Expediente, Long> getRepo() {
        return expedienteRepository;
    }
}
