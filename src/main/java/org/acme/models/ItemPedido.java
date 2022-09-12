package org.acme.models;

import javax.validation.constraints.NotNull;

public class ItemPedido {
    @NotNull(message = "Produto deve ser preenchido")
    private Long produtoId;

    @NotNull(message = "Quantidade é obrigatória")
    private Integer quantidade;

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
