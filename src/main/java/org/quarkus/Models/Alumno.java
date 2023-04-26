package org.quarkus.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAlumno;
    @Column
    @NotBlank(message="EL NOMBRE NO PUEDE ESTAR EN BLANCO!!")
    @NotNull(message="EL APELLIDO NO PUEDE ESTAR EN BLANCO!!")
    private String nombre;
    @Column
    @NotBlank(message="EL APELLIDO NO PUEDE ESTAR EN BLANCO!!")
    @NotNull(message="EL APELLIDO NO PUEDE ESTAR EN BLANCO!!")
    private String Apellido;
    @Column
    @NotNull(message="EL ID DEL MAESTRO NO PUEDE ESTAR EN BLANCO!!")
    private int maestro_idMaestro;

    public Alumno() {
    }
    private Alumno(int idAlumno, String nombre, String apellido, int maestro_idMaestro) {
        this.idAlumno = idAlumno;
        this.nombre = nombre;
        Apellido = apellido;
        this.maestro_idMaestro = maestro_idMaestro;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public int getMaestro_idMaestro() {
        return maestro_idMaestro;
    }

    public void setMaestro_idMaestro(int maestro_idMaestro) {
        this.maestro_idMaestro = maestro_idMaestro;
    }
}