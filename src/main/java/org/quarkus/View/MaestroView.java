package org.quarkus.View;

import org.quarkus.Models.Maestro;

import java.util.List;

public interface MaestroView {
    List<Maestro> getAllMaestroService();
    List<Maestro> getMestroByIDService(int idMestro);
    String createMaestroService(Maestro maestro);
    String updateMaestroService(int idMaestro, Maestro maestro);
    String deleteMaestroService(int idMaestro);
}
