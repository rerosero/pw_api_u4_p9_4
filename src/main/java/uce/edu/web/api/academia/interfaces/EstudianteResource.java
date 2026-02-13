package uce.edu.web.api.academia.interfaces;

import java.util.ArrayList;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import uce.edu.web.api.academia.application.EstudianteService;
import uce.edu.web.api.academia.application.representation.EstudianteRepresentation;
import uce.edu.web.api.academia.application.representation.LinkDto;

@ApplicationScoped
@Path("/estudiantes")
public class EstudianteResource {
    @Inject
    EstudianteService estudianteService;

    @Context
    UriInfo uriInfo;

    private EstudianteRepresentation Links (EstudianteRepresentation er){
        String self = this.uriInfo.getBaseUriBuilder().path(EstudianteResource.class).path(String.valueOf(er.id)).build().toString();
        er.links = List.of(new LinkDto(self, "self"));
        return er;
    }
    @POST
    @Path("/inscribir/{idEst}/curso/{idCurso}")
    public Response InscribirEstudiante(@PathParam("idEst") Integer idEst, @PathParam("idCurso") Integer idCurso){
        this.estudianteService.inscribirCurso(idEst, idCurso);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    @DELETE
    @Path("/cancelar/{idEst}/curso/{idCurso}")
    public Response CancelarInscripcion(@PathParam("idEst") Integer idEst, @PathParam("idCurso") Integer idCurso){
        this.estudianteService.cancelarInscripcion(idEst, idCurso);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<EstudianteRepresentation> listarTodos (){
        List<EstudianteRepresentation> lista = new ArrayList<>();
        for(EstudianteRepresentation est: this.estudianteService.ListarTodos()){
            lista.add(Links(est));
        }
        return lista;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public EstudianteRepresentation buscarId (@PathParam("id") Integer id){
        return Links(this.estudianteService.consultarporID(id));
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response crearEstudiante(EstudianteRepresentation er){
        this.estudianteService.crearEstudiante(er);
        return Response.status(Response.Status.CREATED).entity(er).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response actualizar( @PathParam("id") Integer id, EstudianteRepresentation er){
        this.estudianteService.actualizar(id, er);
        return Response.status(204).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response actualizarParcial (@PathParam("id") Integer id,EstudianteRepresentation er){
        this.estudianteService.actualizarParcial(id, er);
        return Response.status(204).build();
    }

    @DELETE
    @Path("{id}")
    public Response eliminar (@PathParam("id" )Integer id){
        this.estudianteService.eliminar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
