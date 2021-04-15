package com.ceiba.biblioteca.controller;


import com.ceiba.biblioteca.persistence.entity.Prestamo;
import com.ceiba.biblioteca.service.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("prestamo")
public class PrestamoControlador {

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("")
    public ResponseEntity<Prestamo> prestamo(@RequestBody Prestamo usuario){

        if(prestamoService.existePrestamoInvitado(usuario.getIdentificacionUsuario())){
            return new ResponseEntity("{'mensaje':El usuario con identificación " +usuario.getIdentificacionUsuario() +" ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo}",HttpStatus.BAD_REQUEST);
        }else {
            if (prestamoService.verificacionTipoUsiario(usuario)){
                return new ResponseEntity<>(prestamoService.save(usuario),HttpStatus.OK);
            }else {
                return new ResponseEntity("{'mensaje':Tipo de usuario no permitido en la biblioteca}",HttpStatus.BAD_REQUEST);
            }
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Prestamo> prestamoId(@PathVariable("id") int id){
        return prestamoService.usuario(id).map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}

