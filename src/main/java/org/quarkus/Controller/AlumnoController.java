package org.quarkus.Controller;

import jakarta.inject.Inject;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.quarkus.Models.Alumno;
import org.quarkus.Models.Message;
import org.quarkus.Services.AlumnoService;

import java.util.List;

@Path("/alumno")
public class AlumnoController {
    @Inject
    Validator validator;
    @Inject
    AlumnoService alumnoService;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAlumnoController(){
        List<Alumno> alumnos = alumnoService.GetAlumnos();
        return Response.ok(alumnos).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response alumnoByID(@PathParam("id") int id){
        List<Alumno> alumno = alumnoService.GetAlumnoByID(id);
        if (alumno.contains(null)){
            return Response.status(404).entity(new Message("NO SE ENCONTRARON REGISTROS CON EL ID INGRESADO!!", 404)).build();
        }
        return Response.ok(alumno).build();
    }
    @POST
    @Transactional
    public Response createAlumno(@Valid Alumno alumnoObj){
        try {
            String alumno = alumnoService.createAlumno(alumnoObj);
            return Response.ok(new Message(alumno, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();

        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateAlumno( @PathParam("id") int id,@Valid Alumno alumno){
        try {
            String updateAlumnoResponse = alumnoService.updateAlumno(id, alumno);
            return Response.ok(new Message(updateAlumnoResponse, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteAlumno( @PathParam("id") int id){
        try {
            String deleteAlumnoResponse = alumnoService.deleteAlumno(id);
            return Response.ok(new Message(deleteAlumnoResponse, 200)).status(200).build();
        }catch (PersistenceException e){
            return Response.ok(new Message(e.getMessage(), 500)).status(500).build();
        }
    }
}
