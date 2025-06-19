package pe.proyecto.agrario.agrario.service.impl;


import pe.proyecto.agrario.agrario.exception.CustomErrorResponse;
import pe.proyecto.agrario.agrario.exception.ModelNotFoundException;
import pe.proyecto.agrario.agrario.repository.ICrudGenericRepository;
import pe.proyecto.agrario.agrario.service.ICrudGenericService;

import java.time.LocalDateTime;
import java.util.List;

public abstract class CrudGenericServiceImpl<T,ID> implements
        ICrudGenericService<T,ID> {
    protected abstract ICrudGenericRepository<T,ID> getRepo();
    @Override
    public T save(T t) {
        return getRepo().save(t);
    }
    @Override
    public T update(ID id, T t) {
        getRepo().findById(id).orElseThrow(() -> new
                ModelNotFoundException("ID NOT FOUND: " + id));
        return getRepo().save(t);
    }
    @Override
    public List<T> findAll() {
        return getRepo().findAll();
    }
    @Override
    public T findById(ID id) {
        return getRepo().findById(id).orElseThrow(() -> new
                ModelNotFoundException("ID NOT FOUND: " + id));
    }
    @Override
    public CustomErrorResponse delete(ID id) {
        getRepo().findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));
        getRepo().deleteById(id);
        CustomErrorResponse cer=new CustomErrorResponse();
        cer.setStatusCode(200);
        cer.setDatetime(LocalDateTime.now());
        cer.setMessage("true");
        cer.setDetails("Todo Ok");
        return cer;
    }
}