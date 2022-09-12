package org.acme.services;

import java.time.LocalDateTime;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.converter.ClienteConverter;
import org.acme.converter.PedidoConverter;
import org.acme.entity.ClienteEntity;
import org.acme.entity.PedidoEntity;
import org.acme.models.Cliente;
import org.acme.models.ItemPedido;
import org.acme.models.Pedido;
import org.acme.models.PedidoResponse;
import org.acme.repository.ClienteRepository;
import org.acme.repository.PedidoRepository;
import org.eclipse.microprofile.jwt.JsonWebToken;

@RequestScoped
public class PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    PedidoConverter converter;

    @Inject
    JsonWebToken jwt;

    @Inject
    ClienteService clienteService;

    @Transactional
    public PedidoResponse salvar(Set<ItemPedido> listaProdutos) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clienteService.findByEmail(jwt.getSubject()));
        pedido.setHorarioRegistro(LocalDateTime.now());
        pedido.setListaProdutos(listaProdutos);
        PedidoEntity pedidoEntity = converter.convertDtoTo(pedido);
        repository.persist(pedidoEntity);
        PedidoResponse response = new PedidoResponse();
        response.setPedidoId(pedidoEntity.getId());
        return response;
    }

}
