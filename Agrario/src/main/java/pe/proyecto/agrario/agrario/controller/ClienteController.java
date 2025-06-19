package pe.proyecto.agrario.agrario.controller;




import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pe.proyecto.agrario.agrario.dtos.ClienteDTO;
import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;
import pe.proyecto.agrario.agrario.mappers.ClienteMapper;
import pe.proyecto.agrario.agrario.modelo.Cliente;
import pe.proyecto.agrario.agrario.service.IClienteService;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
@CrossOrigin("*")

@RequiredArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private final IClienteService clienteService;
    private final ClienteMapper clienteMapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> list = clienteMapper.toDTOs(clienteService.findAll());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Long id) {
        Cliente obj = clienteService.findById(id);
        return ResponseEntity.ok(clienteMapper.toDTO(obj));
    }

    @PostMapping
    public ResponseEntity<CustomErrorResponse> save(@Valid @RequestBody ClienteDTO dto) {
        Cliente obj = clienteService.save(clienteMapper.toEntity(dto));
        return ResponseEntity.ok(new CustomErrorResponse(200, LocalDateTime.now(), (obj!=null?"true":"false"), String.valueOf(obj.getIdCliente())));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable("id") Long id, @RequestBody ClienteDTO dto) {
        dto.setIdCliente(id);
        Cliente obj = clienteService.update(id, clienteMapper.toEntity(dto));
        return ResponseEntity.ok(clienteMapper.toDTO(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomErrorResponse> delete(@PathVariable("id") Long id) {
        CustomErrorResponse operacion= clienteService.delete(id);
        return ResponseEntity.ok(operacion);
    }

}