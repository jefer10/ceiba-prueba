package com.ceiba.biblioteca;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;


@Entity
@Table(name="prestamo")
public class User{

  @Id
  private String identificacionUsuario;

  private String isbn;

  private Integer tipoUsuario;


}