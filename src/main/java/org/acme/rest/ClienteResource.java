package org.acme.rest;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.models.Cliente;
import org.acme.services.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/cliente")
@Tag(name = "Cliente")
public class ClienteResource {

    @Inject
    ClienteService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista clientes", description = "Lista de clientes cadastrados")
    @APIResponse(responseCode = "200", description = "Listado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class, type = SchemaType.ARRAY)) })
    public List<Cliente> listar() {
        return service.getClientes();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Salva cliente", description = "Salva um novo cliente")
    @APIResponse(responseCode = "201", description = "Salvo com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) })
    public Response salvar(@Valid Cliente cliente) {
        return Response.status(Response.Status.CREATED).entity(service.salvarCliente(cliente)).build();
    }
}
