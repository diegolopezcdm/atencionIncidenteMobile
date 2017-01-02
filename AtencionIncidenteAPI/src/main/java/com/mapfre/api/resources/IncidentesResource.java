package com.mapfre.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mapfre.api.model.SeguimientoSolicitud;
import com.mapfre.api.service.IncidenteService;

@Path("/incidentes")
public class IncidentesResource {

	IncidenteService service = new IncidenteService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response list() {
		return Response.status(200).entity(service.list()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}")
	public Response get(@PathParam("id") Integer id) {
		return Response.status(200).entity(service.get(id)).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{id}/aprobar")
	public Response aprobar(@PathParam("id") Integer id) {
		service.aprobar(id);
		return Response.status(200).entity("success").build();
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/{id}/observar")
	public Response observar(@PathParam("id") Integer id, SeguimientoSolicitud seguimientoSolicitud) {
		seguimientoSolicitud.setSolicitudId(id);
		service.observar(seguimientoSolicitud);
		return Response.status(200).entity("success").build();
	}
}