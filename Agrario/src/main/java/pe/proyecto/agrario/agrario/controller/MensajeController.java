package pe.proyecto.agrario.agrario.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.dtos.MensajeDTO;
import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;
import pe.proyecto.agrario.agrario.mappers.MensajeMapper;
import pe.proyecto.agrario.agrario.modelo.Mensaje;
import pe.proyecto.agrario.agrario.service.IMensajeService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin("*")
@RequiredArgsConstructor
@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final IMensajeService mensajeService;
    private final MensajeMapper mensajeMapper;

    @GetMapping
    public ResponseEntity<List<MensajeDTO>> findAll() {
        List<MensajeDTO> list = mensajeMapper.toDTOs(mensajeService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> findById(@PathVariable("id") Long id) {
        Mensaje obj = mensajeService.findById(id);
        return ResponseEntity.ok(mensajeMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody MensajeDTO.MensajeCADTO dto) {
        MensajeDTO obj = mensajeService.saveD(dto);
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdMensaje())));

    }

    @PutMapping("/{id}")
    public ResponseEntity<MensajeDTO> update(@Valid @RequestBody MensajeDTO.MensajeCADTO dto,
                                             @PathVariable("id") Long id) {
        MensajeDTO obj = mensajeService.updateD(dto, id);
        return ResponseEntity.ok(obj);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse response = mensajeService.delete(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<MensajeDTO>> listPage(Pageable pageable) {
        Page<MensajeDTO> page = mensajeService.listaPage(pageable)
                .map(mensajeMapper::toDTO);
        return ResponseEntity.ok(page);
    }
}
