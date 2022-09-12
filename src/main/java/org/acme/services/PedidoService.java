package org.acme.services;

import java.time.LocalDateTime;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.resource.spi.InvalidPropertyException;
import javax.transaction.Transactional;

import org.acme.converter.PedidoConverter;
import org.acme.entity.ItemPedidoEntity;
import org.acme.entity.PedidoEntity;
import org.acme.models.ItemPedido;
import org.acme.models.Pedido;
import org.acme.models.PedidoResponse;
import org.acme.models.Produto;
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

    @Inject
    ProdutoService produtoService;

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
        processarPedido(pedidoEntity);
        return response;
    }

    private void processarPedido(PedidoEntity pedidoEntity) {
        for (ItemPedidoEntity item : pedidoEntity.getListaProdutos()) {
            Produto produto = produtoService.buscaPorId(item.getId().getProdutoSelecionado().getId());
            if (item.getQuantidade() > produto.getEstoque()) {
                throw new IllegalArgumentException();
            }
            produto.setEstoque(produto.getEstoque() - item.getQuantidade());
            produtoService.atualizar(produto);
        }
    }

}
