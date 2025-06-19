package pe.proyecto.agrario.agrario.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.modelo.Expediente;
import pe.proyecto.agrario.agrario.service.IExpedienteService;

import java.net.URI;
import java.util.List;
@CrossOrigin("*")

@RequiredArgsConstructor
@RestController
@RequestMapping("/expediente")
public class ExpedienteController {
    private final IExpedienteService expedienteService;
    @GetMapping
    public ResponseEntity<List<Expediente>> findAll() {
        List<Expediente> list = expedienteService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Expediente> findById(@PathVariable("id") Long
                                                    id) {
        Expediente obj = expedienteService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Expediente dto) {
        Expediente obj = expedienteService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdExpediente()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Expediente> update(@PathVariable("id") Long
                                                  id, @RequestBody
    Expediente dto) {
        dto.setIdExpediente(id);
        Expediente obj = expedienteService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        expedienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}