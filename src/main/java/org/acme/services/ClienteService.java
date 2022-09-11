package org.acme.services;

import java.util.List;
import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.converter.ClienteConverter;
import org.acme.entity.ClienteEntity;
import org.acme.enums.PerfilEnum;
import org.acme.models.Cliente;
import org.acme.repository.ClienteRepository;
import org.acme.security.PasswordUtils;

@RequestScoped
public class ClienteService {

    @Inject
    ClienteRepository repository;

    @Inject
    ClienteConverter converter;

    @Transactional
    public Cliente salvarCliente(Cliente cliente) {
        cliente.setPerfil(PerfilEnum.CLIENTE);

        if (Objects.isNull(cliente.getId())) {
            cliente.setSenha(PasswordUtils.encode(cliente.getSenha()));
            final ClienteEntity entidadeNaoGerenciada = converter.convertDtoTo(cliente);
            repository.persist(entidadeNaoGerenciada);
            return converter.convertEntityTo(entidadeNaoGerenciada);
        } else {
            ClienteEntity entity = repository.findById(cliente.getId());
            entity.setNome(cliente.getNome());
            entity.setEmail(cliente.getEmail());
            return converter.convertEntityTo(entity);
        }
    }

    public List<Cliente> getClientes() {
        return converter.convertEntityTo(repository.findAll().list());
    }

    public Cliente findByEmail(String email) {
        final ClienteEntity entity = repository.findByEmail(email);
        return converter.convertEntityTo(entity);
    }
}
