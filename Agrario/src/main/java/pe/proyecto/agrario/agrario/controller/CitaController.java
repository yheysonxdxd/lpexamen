package pe.proyecto.agrario.agrario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.dtos.CitaDTO;
import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;
import pe.proyecto.agrario.agrario.mappers.CitaMapper;
import pe.proyecto.agrario.agrario.modelo.Cita;
import pe.proyecto.agrario.agrario.service.ICitaService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/citas")
public class CitaController {

    private final ICitaService citaService;
    private final CitaMapper citaMapper;

    @GetMapping
    public ResponseEntity<List<CitaDTO>> findAll() {
        List<CitaDTO> list = citaMapper.toDTOs(citaService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> findById(@PathVariable("id") Long id) {
        Cita obj = citaService.findById(id);
        return ResponseEntity.ok(citaMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody CitaDTO.CitaCADTO dto) {
        CitaDTO obj = citaService.saveD(dto);
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdCita())));

    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@Valid @RequestBody CitaDTO.CitaCADTO dto,
                                          @PathVariable("id") Long id) {
        CitaDTO obj = citaService.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse response = citaService.delete(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<CitaDTO>> listPage(Pageable pageable) {
        Page<CitaDTO> page = citaService.listaPage(pageable)
                .map(citaMapper::toDTO);
        return ResponseEntity.ok(page);
    }
}
