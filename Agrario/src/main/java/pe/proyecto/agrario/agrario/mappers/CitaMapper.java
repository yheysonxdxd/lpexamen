package pe.proyecto.agrario.agrario.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.proyecto.agrario.agrario.dtos.CitaDTO;
import pe.proyecto.agrario.agrario.modelo.Cita;


@Mapper(componentModel = "spring")
public interface CitaMapper extends GenericMapper<CitaDTO, Cita> {

    @Mapping(target = "cliente", ignore = true)
    @Mapping(target = "servicio", ignore = true)
    Cita toEntityFromCADTO(CitaDTO.CitaCADTO citaCADto);
}
