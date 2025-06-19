package pe.proyecto.agrario.agrario.mappers;


import org.mapstruct.Mapper;

import pe.proyecto.agrario.agrario.dtos.ServicioDTO;
import pe.proyecto.agrario.agrario.modelo.Servicio;


@Mapper(componentModel = "spring")
public interface ServicioMapper extends GenericMapper<ServicioDTO, Servicio> {

}
