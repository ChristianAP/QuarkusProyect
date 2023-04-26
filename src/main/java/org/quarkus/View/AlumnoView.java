package org.quarkus.View;

import org.quarkus.Models.Alumno;

import java.util.List;

public interface AlumnoView {
    List<Alumno> GetAlumnos();
    List<Alumno> GetAlumnoByID(int id);
    String createAlumno(Alumno alumno);
    String updateAlumno(int idAlumno, Alumno alumno);
    String deleteAlumno(int idAlumno);
}
