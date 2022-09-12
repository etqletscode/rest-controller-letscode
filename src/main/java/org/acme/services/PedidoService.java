package org.acme.services;

import java.time.LocalDateTime;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

import org.acme.converter.PedidoConverter;
import org.acme.entity.PedidoEntity;
import org.acme.models.Cliente;
import org.acme.models.ItemPedido;
import org.acme.models.Pedido;
import org.acme.models.PedidoResponse;
import org.acme.repository.PedidoRepository;

@RequestScoped
public class PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    PedidoConverter converter;

    @Transactional
    public PedidoResponse salvar(Set<ItemPedido> listaProdutos) {
        Pedido pedido = new Pedido();
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        pedido.setCliente(cliente);
        pedido.setHorarioRegistro(LocalDateTime.now());
        pedido.setListaProdutos(listaProdutos);
        PedidoEntity pedidoEntity = converter.convertDtoTo(pedido);
        repository.persist(pedidoEntity);
        PedidoResponse response = new PedidoResponse();
        response.setPedidoId(pedidoEntity.getId());
        return response;
    }

}
