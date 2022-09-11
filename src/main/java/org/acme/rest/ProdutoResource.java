package org.acme.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.models.Mensagem;
import org.acme.models.Produto;
import org.acme.models.ProdutoLista;
import org.acme.services.ProdutoService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import io.quarkus.logging.Log;

@Path("/produto")
@Tag(name = "Produto")
public class ProdutoResource {

    @Inject
    ProdutoService service;

    @GET
    @Path("/buscar/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Busca por ID", description = "Busca produto por ID")
    @APIResponse(responseCode = "200", description = "Produto encontrado", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    public Response buscaPorId(final @PathParam("id") Long id) throws Exception {
        return Response.status(Response.Status.OK).entity(service.buscaPorId(id)).build();
    }

    @POST
    @Path("/salvar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Salva produto", description = "Salva um novo produto")
    @APIResponse(responseCode = "201", description = "Salvo com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    public Response salvar(final Produto request) throws Exception {
        return Response.status(Response.Status.CREATED).entity(service.salvar(request)).build();
    }

    @PUT
    @Path("/atualizar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "Atualiza produto", description = "Atualiza produto existente")
    @APIResponse(responseCode = "200", description = "Atualizado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    public Response atualizar(final Produto request) throws Exception {
        return Response.status(Response.Status.OK).entity(service.atualizar(request)).build();
    }

    @DELETE
    @Path("/excluir/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Exclui produto", description = "Exclui produto existente")
    @APIResponse(responseCode = "200", description = "Excluído com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Mensagem.class)) })
    public Response excluir(final @PathParam("id") Long id) throws Exception {
        return Response.status(Response.Status.CREATED).entity(service.excluirPorId(id)).build();
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Lista produtos", description = "Lista paginada de produtos")
    @APIResponse(responseCode = "200", description = "Listado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = ProdutoLista.class)) })
    public Response listar(@QueryParam("filtro") @DefaultValue("") String filtro,
            @QueryParam("page") @DefaultValue("0") Integer page,
            @QueryParam("size") @DefaultValue("20") Integer size) {
        Log.info("Recebendo filtro {}" + filtro);
        return Response.ok(service.listar(filtro, page, size)).build();
    }
}
