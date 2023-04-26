package org.quarkus.Controller;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.quarkus.Models.Materia;
import org.quarkus.Models.Message;
import org.quarkus.Services.MateriaService;

import java.util.List;

@Path("/materia")
public class MateriaController {
    @Inject
    Validator validator;
    @Inject
    MateriaService materiaService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMateriaController(){
        List<Materia> materia = materiaService.getAllMateriaService();
        return Response.ok(materia).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getMateriaByIDController(@PathParam("id") int id){
        List<Materia> materia = materiaService.getMateriaByIDService(id);
        if (materia.contains(null)){
            return Response.status(404).entity(new Message("NO SE ENCONTRARON REGISTROS CON EL ID INGRESADO!!", 404)).build();
        }
        return Response.ok(materia).build();
    }
    @POST
    @Transactional
    public Response createMateriaController(@Valid Materia materiaObj){
        try {
            String materia_response = materiaService.createMateriaService(materiaObj);
            return Response.ok(new Message(materia_response, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();

        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateMateriaController( @PathParam("id") int id,@Valid Materia materiaObj){
        try {
            String updateMateriaResponse = materiaService.updateMateriaService(materiaObj, id);
            return Response.ok(new Message(updateMateriaResponse, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteMateriaController( @PathParam("id") int id){
        try {
            String deleteMateriaResponse = materiaService.deleteMateriaService(id);
            return Response.ok(new Message(deleteMateriaResponse, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }

}
