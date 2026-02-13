package uce.edu.web.api.academia.interfaces;

import java.util.ArrayList;
import java.util.List;

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
import uce.edu.web.api.academia.application.CursoService;
import uce.edu.web.api.academia.application.representation.CursoRepresentation;
import uce.edu.web.api.academia.application.representation.LinkDto;

@Path("/cursos")
public class CursoResource {
    @Inject
    private CursoService service;
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public List<CursoRepresentation> listatodos() {
        List<CursoRepresentation> cr = new ArrayList<>();
        for (CursoRepresentation curs : this.service.listartodos()) {
            cr.add(this.construirLinks(curs));
        }
        return cr;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public CursoRepresentation buscarId(@PathParam("id") Integer id) {
        return construirLinks(this.service.buscarID(id));

    }

    private CursoRepresentation construirLinks(CursoRepresentation er) {
        String self = this.uriInfo.getBaseUriBuilder()
                .path(CursoResource.class)
                .path(String.valueOf(er.id))
                .build()
                .toString();
        er.links = List.of(new LinkDto(self, "self"));
        return er;
    }

    @POST
    @Path("")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crearCurso(CursoRepresentation cr) {
        this.service.crearCurso(cr);
        return Response.status(Response.Status.CREATED).entity(cr).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void actualizar(@PathParam("id") Integer id, CursoRepresentation cr) {
        this.service.actualizar(id, cr);
    }

    @PATCH
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarParcial(@PathParam("id") Integer id, CursoRepresentation cr) {
        this.service.actualizarParcial(id, cr);
        return Response.ok(cr).build();
    }

    @DELETE
    @Path("/{id}")
    public Response Eliminar(@PathParam("id") Integer id) {
        this.service.eliminar(id);
        return Response.ok("Curso eliminado con exito"+ id).build();
    }
}
