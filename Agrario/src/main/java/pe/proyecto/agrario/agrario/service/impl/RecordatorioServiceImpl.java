package pe.proyecto.agrario.agrario.service.impl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.proyecto.agrario.agrario.modelo.Recordatorio;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.repository.IRecordatorioRepository;
import pe.proyecto.agrario.agrario.service.IRecordatorioService;

@Transactional
@Service
@RequiredArgsConstructor
public class RecordatorioServiceImpl extends CrudGenericServiceImpl<Recordatorio,Long>implements IRecordatorioService {
    private final IRecordatorioRepository recordatorioRepository;
    @Override
    protected ICrudGenericRepository<Recordatorio, Long> getRepo() {
        return recordatorioRepository;
    }
}
