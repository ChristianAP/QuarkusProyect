package org.quarkus.View;

import org.quarkus.Models.Materia;

import java.util.List;

public interface MateriaView {
    List<Materia> getAllMateriaService();
    List<Materia> getMateriaByIDService(int idMateria);
    String createMateriaService(Materia materia);
    String updateMateriaService(Materia materia, int idMateria);
    String deleteMateriaService(int idMateria);
}
