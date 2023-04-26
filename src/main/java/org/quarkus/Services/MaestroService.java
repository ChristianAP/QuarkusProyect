package org.quarkus.Services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.quarkus.Models.Maestro;
import org.quarkus.Repository.MaestroRepository;
import org.quarkus.View.MaestroView;

import java.util.List;

@ApplicationScoped
public class MaestroService implements MaestroView {
    @Inject
    MaestroRepository maestroRepository;
    @Override
    public List<Maestro> getAllMaestroService() {
        return maestroRepository.getAllMaestrosRepository();
    }

    @Override
    public List<Maestro> getMestroByIDService(int idMestro) {
        List<Maestro> maestroByID = maestroRepository.getMaestroByIDRepository(idMestro);
        return maestroByID;
    }

    @Override
    public String createMaestroService(Maestro maestro) {
        return maestroRepository.createMaestroRepository(maestro);
    }

    @Override
    public String updateMaestroService(int idMaestro, Maestro maestro) {
        return maestroRepository.updateMaestroRepository(idMaestro, maestro);
    }

    @Override
    public String deleteMaestroService(int idMaestro) {
        return maestroRepository.deleteMaestroRepository(idMaestro);
    }
}
