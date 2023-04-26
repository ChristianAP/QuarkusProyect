package org.quarkus.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
@Entity
public class Materia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMateria;

    @Column
    @NotBlank(message="EL NOMBRE DE LA MATERIA NO PUEDE ESTAR EN BLANCO!!")
    @NotNull(message="EL NOMBRE DE LA MATERIA NO PUEDE ESTAR EN BLANCO!!")
    private String descripcion;

    @Column
    @NotBlank(message="EL PUNTAJE NO PUEDE ESTAR EN BLANCO!!")
    @NotNull(message="EL PUNTAJE NO PUEDE ESTAR EN BLANCO!!")
    private String puntos;

    public Materia(){
    }

    public Materia(int idMateria, String descripcion, String puntos) {
        this.idMateria = idMateria;
        this.descripcion = descripcion;
        this.puntos = puntos;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
