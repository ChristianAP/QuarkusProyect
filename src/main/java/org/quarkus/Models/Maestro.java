package org.quarkus.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Maestro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMaestro;

    @Column
    @NotNull(message = "EL NOMBRE NO PUEDE SER UN CAMPO NULO!!")
    @NotBlank(message = "EL NOMBRE NO PUEDE SER UN CAMPO VACIO!!")
    private String nombre;

    @Column
    @NotNull(message = "EL APELLIDO NO PUEDE SER UN CAMPO NULO!!")
    @NotBlank(message = "EL APELLIDO NO PUEDE SER UN CAMPO VACIO!!")
    private String apellido;

    @Column
    @NotNull(message = "EL TITULO NO PUEDE SER UN CAMPO NULO!!")
    @NotBlank(message = "EL TITULO NO PUEDE SER UN CAMPO VACIO!!")
    private String titulo;

    public Maestro(){

    }
    public Maestro(int idMaestro, String nombre, String apellido, String titulo) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.titulo = titulo;
    }

    public int getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(int idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


}
