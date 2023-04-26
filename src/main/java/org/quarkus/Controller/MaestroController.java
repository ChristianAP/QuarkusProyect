package org.quarkus.Controller;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.quarkus.Models.Maestro;
import org.quarkus.Models.Message;
import org.quarkus.Services.MaestroService;

import java.util.List;

@Path("/maestro")
public class MaestroController {
    @Inject
    Validator validator;
    @Inject
    MaestroService maestroService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlLMaestroController() {
        List<Maestro> maestroList = maestroService.getAllMaestroService();
        return Response.ok(maestroList).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMaestroByIDController(@PathParam("id") int id) {
        List<Maestro> maestro_response = maestroService.getMestroByIDService(id);
        if (maestro_response.contains(null)) {
            return Response.status(404).entity(new Message("NO SE ENCONTRARON REGISTROS CON EL ID INGRESADO!!", 404)).build();
        }
        return Response.ok(maestro_response).build();
    }

    @POST
    @Transactional
    public Response createMaestroController(@Valid Maestro maestroObj) {
        try {
            String maestro_response = maestroService.createMaestroService(maestroObj);
            return Response.ok(new Message(maestro_response, 200)).status(200).build();
        } catch (PersistenceException e) {
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();

        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMaestroController(@PathParam("id") int id, @Valid Maestro maestro) {
        try {
            String updateMaestroResponse = maestroService.updateMaestroService(id, maestro);
            return Response.ok(new Message(updateMaestroResponse, 200)).status(200).build();
        } catch (PersistenceException e) {
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMaestroController(@PathParam("id") int id) {
        try {
            String deleteMaestroResponse = maestroService.deleteMaestroService(id);
            return Response.ok(new Message(deleteMaestroResponse, 200)).status(200).build();
        } catch (PersistenceException e) {
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }
}
