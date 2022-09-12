package org.acme.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "item_pedido")
public class ItemPedidoEntity {
    @EmbeddedId
    private ItemPedidoEntityPK id = new ItemPedidoEntityPK();

    @NotNull
    private Integer quantidade;

    public ItemPedidoEntityPK getId() {
        return id;
    }

    public void setId(ItemPedidoEntityPK id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
