package org.acme.rest;

import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.models.Cliente;
import org.acme.models.Login;
import org.acme.models.Produto;
import org.acme.models.Token;
import org.acme.security.JwtUtils;
import org.acme.security.PasswordUtils;
import org.acme.services.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/login")
@Tag(name = "Acesso")
public class LoginResoure {

    @Inject
    ClienteService service;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Entrar com usuário", description = "Possibilita acesso a API's restritas")
    @APIResponse(responseCode = "200", description = "Logado com sucesso", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Produto.class)) })
    public Response logar(Login login) {

        final Cliente cliente = service.findByEmail(login.getEmail());

        if (Objects.isNull(cliente) || !cliente.getSenha().equals(PasswordUtils.encode(login.getSenha()))) {
            return Response.status(403).build();
        }

        final String token = JwtUtils.generateToken(cliente.getEmail(), Set.of(cliente.getPerfil().name()));

        return Response.ok(new Token(cliente.getEmail(), token)).build();
    }
}
