package pe.proyecto.agrario.agrario.service;

import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;

import java.util.List;

public interface ICrudGenericService<T,ID> {

    T update(ID id ,T t);
    T save(T t);
    List<T> findAll();
    T findById(ID id);
    CustomErrorResponse delete(ID id);

}
