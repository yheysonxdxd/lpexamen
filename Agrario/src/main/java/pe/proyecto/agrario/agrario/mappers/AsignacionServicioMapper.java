package pe.proyecto.agrario.agrario.mappers;



import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import pe.proyecto.agrario.agrario.dtos.AsignacionServicioDTO;
import pe.proyecto.agrario.agrario.modelo.AsignacionServicio;


@Mapper(componentModel = "spring")
public interface AsignacionServicioMapper extends GenericMapper<AsignacionServicioDTO, AsignacionServicio> {

    @Mapping(target = "servicio", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    AsignacionServicio toEntityFromCADTO(AsignacionServicioDTO.AsignacionServicioCADTO dto);
    @Override
    AsignacionServicio toEntity(AsignacionServicioDTO dto);


}
