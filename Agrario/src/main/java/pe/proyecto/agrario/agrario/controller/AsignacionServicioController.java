package pe.proyecto.agrario.agrario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.proyecto.agrario.agrario.dtos.AsignacionServicioDTO;
import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;
import pe.proyecto.agrario.agrario.mappers.AsignacionServicioMapper;
import pe.proyecto.agrario.agrario.modelo.AsignacionServicio;
import pe.proyecto.agrario.agrario.service.IAsignacionServicioService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/asignacion-servicio")
public class AsignacionServicioController {

    private final IAsignacionServicioService asignacionServicioService;
    private final AsignacionServicioMapper asignacionServicioMapper;

    @GetMapping
    public ResponseEntity<List<AsignacionServicioDTO>> findAll() {
        List<AsignacionServicioDTO> list = asignacionServicioMapper.toDTOs(asignacionServicioService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignacionServicioDTO> findById(@PathVariable("id") Long id) {
        AsignacionServicio obj = asignacionServicioService.findById(id);
        return ResponseEntity.ok(asignacionServicioMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody AsignacionServicioDTO.AsignacionServicioCADTO dto) {
        AsignacionServicioDTO obj = asignacionServicioService.saveD(dto);
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdAsignacion())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignacionServicioDTO> update(@Valid @RequestBody AsignacionServicioDTO.AsignacionServicioCADTO dto,
                                                        @PathVariable("id") Long id) {
        AsignacionServicioDTO obj = asignacionServicioService.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse response = asignacionServicioService.delete(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<AsignacionServicioDTO>> listPage(Pageable pageable) {
        Page<AsignacionServicioDTO> page = asignacionServicioService.listaPage(pageable)
                .map(asignacionServicioMapper::toDTO);
        return ResponseEntity.ok(page);
    }
}
