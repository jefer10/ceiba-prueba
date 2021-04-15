package com.ceiba.biblioteca.service;

import com.ceiba.biblioteca.persistence.entity.Prestamo;
import com.ceiba.biblioteca.persistence.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class PrestamoService {


    @Autowired
    private PrestamoRepository prestamoRepository;

    public boolean existePrestamoInvitado(String identificacionUsuario){
        Optional<Prestamo> existente= prestamoRepository.poseeLibro(identificacionUsuario);
        if(existente.isPresent() && (existente.get().getTipoUsuario()==3))
        {
            return true;
        }else {
            return false;
        }
    }

    public Optional<Prestamo> usuario(int id){
        return prestamoRepository.usuario(id);
    }

    public Prestamo save(Prestamo usuario){

        LocalDate fecha=LocalDate.now();
        switch (usuario.getTipoUsuario()){
            case 1:
                fecha=fechaMaxima(10);
                break;
            case 2:
                fecha=fechaMaxima(8);
                break;
            case 3:
                fecha=fechaMaxima(7);
                break;
            default:
                break;
        }
        usuario.setFechaMaximaDevolucion(fecha(fecha));
        return prestamoRepository.save(usuario);
    }

    private String fecha(LocalDate localDate){
        LocalDate fechaPrestamo = localDate;
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fechaPrestamo.format(formato);
    }

    private LocalDate fecha1(String fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(fecha,formato);
    }

    private LocalDate fechaMaxima(int dias){
        LocalDate fechaPrestamo=LocalDate.now();
        for (int i = 0; i < dias; i++) {
            fechaPrestamo=fechaPrestamo.plusDays(1);
            if (fechaPrestamo.getDayOfWeek()== DayOfWeek.SATURDAY){
                fechaPrestamo=fechaPrestamo.plusDays(2);
            }
            if (fechaPrestamo.getDayOfWeek()==DayOfWeek.SUNDAY){
                fechaPrestamo=fechaPrestamo.plusDays(1);
            }
        }
        return fechaPrestamo;
    }

    public boolean verificacionTipoUsiario(Prestamo usuario){
        int tipo=usuario.getTipoUsuario();
        if (tipo>=1 && tipo<=3){
            return true;
        }else{
            return false;
        }
    }
}
