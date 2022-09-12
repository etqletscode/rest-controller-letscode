package org.acme.converter;

import java.util.Set;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.acme.entity.ItemPedidoEntity;
import org.acme.entity.PedidoEntity;
import org.acme.entity.ProdutoEntity;
import org.acme.models.ItemPedido;
import org.acme.models.Pedido;

@ApplicationScoped
public class PedidoConverter {
    @Inject
    ClienteConverter clienteConverter;

    public Pedido convertEntityTo(PedidoEntity pedidoEntity) {
        Pedido pedidoModel = new Pedido();
        pedidoModel.setCliente(clienteConverter.convertEntityTo(pedidoEntity.getCliente()));
        pedidoModel.setHorarioRegistro(pedidoEntity.getHorarioRegistro());
        pedidoModel.setId(pedidoEntity.getId());
        pedidoModel.setListaProdutos(this.convertEntityTo(pedidoEntity.getListaProdutos()));
        return pedidoModel;
    }

    public Set<ItemPedido> convertEntityTo(Set<ItemPedidoEntity> listaProdutos) {
        return listaProdutos.stream()
                .map(this::convertEntityTo)
                .collect(Collectors.toSet());
    }

    public ItemPedido convertEntityTo(ItemPedidoEntity itemPedidoEntity) {
        ItemPedido itemPedidoModel = new ItemPedido();
        itemPedidoModel.setProdutoId(itemPedidoEntity.getId().getPedidoOrigem().getId());
        itemPedidoModel.setQuantidade(itemPedidoEntity.getQuantidade());
        return itemPedidoModel;
    }

    public PedidoEntity convertDtoTo(Pedido pedidoModel) {
        PedidoEntity pedidoEntity = new PedidoEntity();
        pedidoEntity.setCliente(clienteConverter.convertDtoTo(pedidoModel.getCliente()));
        pedidoEntity.setHorarioRegistro(pedidoModel.getHorarioRegistro());
        pedidoEntity.setId(pedidoModel.getId());
        Set<ItemPedidoEntity> listaProdutos = this.convertDtoTo(pedidoModel.getListaProdutos());
        for (ItemPedidoEntity itemPedidoEntity : listaProdutos) {
            pedidoEntity.adicionarProduto(itemPedidoEntity);
        }
        return pedidoEntity;
    }

    public Set<ItemPedidoEntity> convertDtoTo(Set<ItemPedido> listaProdutos) {
        return listaProdutos.stream()
                .map(this::convertDtoTo)
                .collect(Collectors.toSet());
    }

    public ItemPedidoEntity convertDtoTo(ItemPedido itemPedido) {
        ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();
        ProdutoEntity produtoEntity = new ProdutoEntity();
        produtoEntity.setId(itemPedido.getProdutoId());
        itemPedidoEntity.getId().setProdutoSelecionado(produtoEntity);
        itemPedidoEntity.setQuantidade(itemPedido.getQuantidade());
        return itemPedidoEntity;
    }
}
