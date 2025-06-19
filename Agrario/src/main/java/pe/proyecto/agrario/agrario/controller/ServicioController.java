package pe.proyecto.agrario.agrario.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.modelo.Servicio;
import pe.proyecto.agrario.agrario.service.IServicioService;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/servicio")
@CrossOrigin("*")

public class ServicioController {
    private final IServicioService servicioService;
    @GetMapping
    public ResponseEntity<List<Servicio>> findAll() {
        List<Servicio> list = servicioService.findAll();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Servicio> findById(@PathVariable("id") Long
                                                    id) {
        Servicio obj = servicioService.findById(id);
        return ResponseEntity.ok(obj);
    }
    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody Servicio dto) {
        Servicio obj = servicioService.save(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(
                obj.getIdServicio()).toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<Servicio> update(@PathVariable("id") Long
                                                  id, @RequestBody
    Servicio dto) {
        dto.setIdServicio(id);
        Servicio obj = servicioService.update(id, dto);
        return ResponseEntity.ok(obj);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        servicioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}