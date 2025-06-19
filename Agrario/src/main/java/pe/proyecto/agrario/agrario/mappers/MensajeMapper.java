package pe.proyecto.agrario.agrario.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.proyecto.agrario.agrario.dtos.MensajeDTO;
import pe.proyecto.agrario.agrario.modelo.Mensaje;


@Mapper(componentModel = "spring")
public interface MensajeMapper extends GenericMapper<MensajeDTO, Mensaje> {

    @Mapping(target = "cliente", ignore = true)
    Mensaje toEntityFromCADTO(MensajeDTO.MensajeCADTO dto);
}
