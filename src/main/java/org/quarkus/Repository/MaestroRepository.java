package org.quarkus.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.quarkus.Models.Alumno;
import org.quarkus.Models.Maestro;

import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class MaestroRepository {
    @PersistenceContext
    EntityManager em;
    @Transactional
    public List<Maestro> getAllMaestrosRepository(){
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Maestro> query = criteriaBuilder.createQuery(Maestro.class);
        query.from(Maestro.class);
        return em.createQuery(query).getResultList();
    }

    @Transactional
    public List<Maestro> getMaestroByIDRepository(int id){
        return Collections.singletonList(em.find(Maestro.class, id));
    }

    @Transactional
    public String createMaestroRepository(Maestro maestro){
        try {
            em.persist(maestro);
            em.flush();
            return "MAESTRO CREADO CORRECTAMENTE!";
        }catch (PersistenceException e){
            Throwable cause = e.getCause();
            if (cause instanceof ConstraintViolationException) {
                String message = "Duplicate value for unique field: " + cause.getMessage();
                return message;
            }
            return e.getMessage();
        }
    }

    @Transactional
    public String updateMaestroRepository(int idMaestro, Maestro maestro){
        try {
            Maestro maestro_exist = em.find(Maestro.class, idMaestro);

            if (maestro_exist == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }

            maestro_exist.setNombre(maestro.getNombre());
            maestro_exist.setApellido(maestro.getApellido());
            maestro_exist.setTitulo(maestro.getTitulo());

            em.merge(maestro_exist);

            return "MAESTRO MODIFICADO CORRECTAMENTE!!";

        }catch (PersistenceException e){
            return e.getMessage();
        }
    }
    @Transactional
    public String deleteMaestroRepository(int idMaestro){
        try {
            Maestro maestro_exist = em.find(Maestro.class, idMaestro);

            if (maestro_exist == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }

            TypedQuery<Alumno> query = em.createQuery("SELECT a FROM Alumno as a WHERE a.maestro_idMaestro = :maestro_idMaestro", Alumno.class);
            query.setParameter("maestro_idMaestro", idMaestro);
            List<Alumno> alumno_list = query.getResultList();

            if (alumno_list.isEmpty()) {
                em.remove(maestro_exist);
                return "MAESTRO ELIMINADO CORRECTAMENTE!!";
            }
            return "NO ES POSIBLE ELIMINAR EL MAESTRO PORQUE HAY ALUMNOS ACTIVOS QUE CUENTAN CON EL MAESTRO !!";
        }catch (PersistenceException e){
            return e.getMessage();
        }
    }
}
