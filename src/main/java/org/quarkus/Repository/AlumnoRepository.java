package org.quarkus.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.quarkus.Models.Alumno;

import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class AlumnoRepository {
    @PersistenceContext
    EntityManager em;
    @Transactional
    public List<Alumno> getAll() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Alumno> query = criteriaBuilder.createQuery(Alumno.class);
        query.from(Alumno.class);
        return em.createQuery(query).getResultList();

    }

    @Transactional
    public List<Alumno> getAlumnoByID(int id){
        return Collections.singletonList(em.find(Alumno.class, id));
    }
    @Transactional
    public String create(Alumno alumno) {
        try {
            em.persist(alumno);
            em.flush();
            return "CREADO CORRECTAMENTE!!";

        }catch (PersistenceException e){
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                String message = "Duplicate value for unique field: " + cause.getMessage();
                return message;
            }
            throw e;
        }
    }

    @Transactional
    public String updateAlumno(int idAlumno, Alumno alumno){
        try {
            Alumno exist_alumno = em.find(Alumno.class, idAlumno);

            if (exist_alumno == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }

            exist_alumno.setApellido(alumno.getApellido());
            exist_alumno.setNombre(alumno.getNombre());
            exist_alumno.setMaestro_idMaestro(alumno.getMaestro_idMaestro());

            em.merge(exist_alumno);
            return "REGISTRO MODIFICADO CORRECTAMENTE!!";

        }catch(PersistenceException e){
            return e.getMessage();
        }
    }

    @Transactional
    public String deleteAlumno(int idAlumno){
        try {
            Alumno exist_alumno = em.find(Alumno.class, idAlumno);

            if (exist_alumno == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }


            em.remove(exist_alumno);
            return "REGISTRO ELIMINADO CORRECTAMENTE!!";

        }catch(PersistenceException e){
            return e.getMessage();
        }
    }


}