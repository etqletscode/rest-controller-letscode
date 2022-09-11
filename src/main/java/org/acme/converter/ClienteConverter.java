package org.acme.converter;

import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.ClienteEntity;
import org.acme.models.Cliente;

@ApplicationScoped
public class ClienteConverter {
    public Cliente convertEntityTo(ClienteEntity clienteEntity) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteEntity.getId());
        cliente.setCpf(clienteEntity.getCpf());
        cliente.setEmail(clienteEntity.getEmail());
        cliente.setNome(clienteEntity.getNome());
        cliente.setSenha(clienteEntity.getSenha());
        cliente.setPerfil(clienteEntity.getPerfil());
        return cliente;
    }

    public ClienteEntity convertDtoTo(Cliente cliente) {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(cliente.getId());
        entity.setCpf(cliente.getCpf());
        entity.setEmail(cliente.getEmail());
        entity.setNome(cliente.getNome());
        entity.setSenha(cliente.getSenha());
        entity.setPerfil(cliente.getPerfil());
        return entity;
    }

    public List<Cliente> convertEntityTo(List<ClienteEntity> entities) {
        return entities.stream().map(this::convertEntityTo)
                .collect(Collectors.toList());
    }
}
