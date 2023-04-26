package org.quarkus.Repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.quarkus.Models.Materia;

import java.util.Collections;
import java.util.List;

@Transactional
@ApplicationScoped
public class MateriaRepository {
    @PersistenceContext
    EntityManager em;
    @Transactional
    public List<Materia> getAllMateriaRepository() {

        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Materia> query = criteriaBuilder.createQuery(Materia.class);
        query.from(Materia.class);
        return em.createQuery(query).getResultList();

    }

    @Transactional
    public List<Materia> getMateriaByIDRepository(int idMateria){
        return Collections.singletonList(em.find(Materia.class, idMateria));
    }
    @Transactional
    public String createMateriaRepository(Materia materiaObj) {
        try {
            em.persist(materiaObj);
            em.flush();
            return "MATERIA CREADA CORRECTAMENTE!!";

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
    public String updateMateriaRepository(int idMateria, Materia materia){
        try {
            Materia exist_materia = em.find(Materia.class, idMateria);

            if (exist_materia == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }

            exist_materia.setDescripcion(materia.getDescripcion());
            exist_materia.setPuntos(materia.getPuntos());

            em.merge(exist_materia);
            return "MATERIA MODIFICADA CORRECTAMENTE!!";

        }catch(PersistenceException e){
            return e.getMessage();
        }
    }

    @Transactional
    public String deleteMateriaRepository(int idMateria){
        try {
            Materia exist_materia = em.find(Materia.class, idMateria);

            if (exist_materia == null){
                return "NO SE ENCONTRÓ UN ID QUE COINCIDA CON EL QUE INGRESÓ!!";
            }


            em.remove(exist_materia);
            return "MATERIA ELIMINADA CORRECTAMENTE!!";

        }catch(PersistenceException e){
            return e.getMessage();
        }
    }

}
