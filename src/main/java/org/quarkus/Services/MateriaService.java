package org.quarkus.Services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quarkus.Models.Materia;
import org.quarkus.Repository.MateriaRepository;
import org.quarkus.View.MateriaView;

import java.util.List;

@ApplicationScoped
public class MateriaService implements MateriaView {
    @Inject
    MateriaRepository materiaRepository;
    @Override
    public List<Materia> getAllMateriaService() {
        return materiaRepository.getAllMateriaRepository();
    }

    @Override
    public List<Materia> getMateriaByIDService(int idMateria) {
        return materiaRepository.getMateriaByIDRepository(idMateria);
    }

    @Override
    public String createMateriaService(Materia materia) {
        return materiaRepository.createMateriaRepository(materia);
    }

    @Override
    public String updateMateriaService(Materia materia, int idMateria) {
        return materiaRepository.updateMateriaRepository(idMateria, materia);
    }

    @Override
    public String deleteMateriaService(int idMateria) {
        return materiaRepository.deleteMateriaRepository(idMateria);
    }
}
