package com.mapfre.api.resources;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.mapfre.api.service.IncidenteService;

@Path("/incidentes")
public class IncidentesResource {

	IncidenteService service = new IncidenteService();

	/*
	 * @GET
	 * 
	 * @Produces("text/html") public Response getStartingPage() { String output
	 * = "<h1>Hello World!<h1>" +
	 * "<p>RESTful Service is running ... <br>Ping @ " + new Date().toString() +
	 * "</p<br>"; return Response.status(200).entity(output).build(); }
	 */

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

}