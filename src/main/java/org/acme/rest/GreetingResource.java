package org.acme.rest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.services.GreetingService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/hello")
@Tag(name = "Let's Code")
public class GreetingResource {
    @Inject
    GreetingService service;

    @GET
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Hello!", description = "Gera uma mensagem de boas-vindas")
    public String hello() {
        return service.hello();
    }

    @GET
    @Path("/{nome}")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Hello, {nome}!", description = "Gera uma mensagem de boas-vindas com o nome")
    public Response helloNome(final @PathParam("nome") String nome) throws Exception {
        return Response.status(Response.Status.OK).entity(service.helloNome(nome)).build();
    }

    @GET
    @Path("/{nome}/horario")
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(summary = "Hello, {nome}! Agora são {horário}, não se esqueça!", description = "Gera uma mensagem de boas-vindas com o nome e horário")
    public Response helloNomeHorario(final @PathParam("nome") String nome) throws Exception {
        return Response.status(Response.Status.OK).entity(service.helloNomeHorario(nome)).build();
    }
}
