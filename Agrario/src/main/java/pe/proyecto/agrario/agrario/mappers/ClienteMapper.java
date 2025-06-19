package pe.proyecto.agrario.agrario.mappers;

import org.mapstruct.Mapper;
import pe.proyecto.agrario.agrario.dtos.ClienteDTO;
import pe.proyecto.agrario.agrario.modelo.Cliente;
@Mapper(componentModel = "spring")

public interface ClienteMapper extends GenericMapper<ClienteDTO, Cliente>{
}
