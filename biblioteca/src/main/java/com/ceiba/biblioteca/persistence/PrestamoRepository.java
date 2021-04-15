package com.ceiba.biblioteca.persistence;

import com.ceiba.biblioteca.persistence.entity.Prestamo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class PrestamoRepository {

    @Autowired
    private PrestamoCrud prestamoCrud;

    public Prestamo save(Prestamo usuario){
        return prestamoCrud.save(usuario);
    }

    public Optional<Prestamo> usuario(int id){
        return prestamoCrud.findById(id);
    }
    public Optional<Prestamo> poseeLibro(String identificacionUsuario){
        return prestamoCrud.findByIdentificacionUsuario(identificacionUsuario);
    }
}
