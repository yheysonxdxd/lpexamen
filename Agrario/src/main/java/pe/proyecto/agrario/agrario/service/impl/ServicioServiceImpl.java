package pe.proyecto.agrario.agrario.service.impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.modelo.Servicio;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IServicioRepository;
import pe.proyecto.agrario.agrario.service.IServicioService;

@Transactional
@Service
@RequiredArgsConstructor
public class ServicioServiceImpl extends CrudGenericServiceImpl<Servicio,Long> implements IServicioService {
    private final IServicioRepository servicioRepository;
    @Override
    protected ICrudGenericRepository<Servicio, Long> getRepo() {
        return servicioRepository;
    }
}
