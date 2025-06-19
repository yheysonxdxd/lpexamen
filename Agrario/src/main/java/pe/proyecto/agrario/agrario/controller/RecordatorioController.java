package pe.proyecto.agrario.agrario.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.modelo.Recordatorio;
import pe.proyecto.agrario.agrario.service.IRecordatorioService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/recordatorio")
public class RecordatorioController {
    private final IRecordatorioService recordatorioService;
    @GetMapping
    public ResponseEntity<List<Recordatorio>> findAll() {
        List<Recordatorio> list = recordatorioService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Recordatorio> findById(@PathVariable("id") Long
                                                    id) {
        Recordatorio obj = recordatorioService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Recordatorio dto) {
        Recordatorio obj = recordatorioService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdRecordatorio()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Recordatorio> update(@PathVariable("id") Long
                                                  id, @RequestBody
    Recordatorio dto) {
        dto.setIdRecordatorio(id);
        Recordatorio obj = recordatorioService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        recordatorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}