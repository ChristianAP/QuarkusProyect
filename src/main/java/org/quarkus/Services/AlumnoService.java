package org.quarkus.Services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quarkus.Models.Alumno;
import org.quarkus.Repository.AlumnoRepository;
import org.quarkus.View.AlumnoView;

import java.util.List;

@ApplicationScoped
public class AlumnoService implements AlumnoView {
    @Inject
    AlumnoRepository alumnoRepository;
    @Override
    public List<Alumno> GetAlumnos() {
        return alumnoRepository.getAll();
    }

    @Override
    public List<Alumno> GetAlumnoByID(int id) {
        List<Alumno> alumno = alumnoRepository.getAlumnoByID(id);
        return alumno;
    }

    @Override
    public String createAlumno(Alumno alumno) {
        return alumnoRepository.create(alumno);
    }

    @Override
    public String updateAlumno(int idAlumno, Alumno alumno) {
        return alumnoRepository.updateAlumno(idAlumno, alumno);
    }

    @Override
    public String deleteAlumno(int idAlumno) {
        return alumnoRepository.deleteAlumno(idAlumno);
    }
}
