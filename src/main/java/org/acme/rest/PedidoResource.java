package org.acme.rest;

import java.util.Set;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.models.ItemPedido;
import org.acme.models.PedidoResponse;
import org.acme.services.PedidoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.security.SecuritySchemes;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/pedido")
@Tag(name = "Pedido")
@SecuritySchemes(value = {
        @SecurityScheme(securitySchemeName = "apiKey", type = SecuritySchemeType.HTTP, scheme = "Bearer") })
public class PedidoResource {

    @Inject
    PedidoService service;

    @POST
    @Path("/novo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @SecurityRequirement(name = "apiKey")
    @RolesAllowed({ "ADMIN" })
    @Operation(summary = "Realiza pedido", description = "Recebe o pedido de um cliente")
    @APIResponse(responseCode = "201", description = "Realizado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = PedidoResponse.class)) })
    public Response salvar(final Set<ItemPedido> request) throws Exception {
        return Response.status(Response.Status.CREATED).entity(service.salvar(request)).build();
    }
}
