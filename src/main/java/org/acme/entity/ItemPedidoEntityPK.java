package org.acme.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Embeddable
public class ItemPedidoEntityPK implements Serializable {
    @ManyToOne
    private ProdutoEntity produtoSelecionado;

    @ManyToOne(fetch = FetchType.LAZY)
    private PedidoEntity pedidoOrigem;

    public ItemPedidoEntityPK() {
    }

    public ItemPedidoEntityPK(ProdutoEntity produtoSelecionado, PedidoEntity pedidoOrigem) {
        this.produtoSelecionado = produtoSelecionado;
        this.pedidoOrigem = pedidoOrigem;
    }

    public ProdutoEntity getProdutoSelecionado() {
        return produtoSelecionado;
    }

    public void setProdutoSelecionado(ProdutoEntity produtoSelecionado) {
        this.produtoSelecionado = produtoSelecionado;
    }

    public PedidoEntity getPedidoOrigem() {
        return pedidoOrigem;
    }

    public void setPedidoOrigem(PedidoEntity pedidoOrigem) {
        this.pedidoOrigem = pedidoOrigem;
    }
}
