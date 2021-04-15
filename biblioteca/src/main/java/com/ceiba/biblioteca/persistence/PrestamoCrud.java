package com.ceiba.biblioteca.persistence;

import com.ceiba.biblioteca.persistence.entity.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrestamoCrud extends JpaRepository<Prestamo,Integer> {

    public Optional<Prestamo> findByIdentificacionUsuario(String identificacionUsuario);
}
