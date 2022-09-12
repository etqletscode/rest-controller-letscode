package org.acme.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pedido")
public class PedidoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @OneToMany(mappedBy = "id.pedidoOrigem", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ItemPedidoEntity> listaProdutos = new HashSet<ItemPedidoEntity>();

    @NotNull
    @ManyToOne
    private ClienteEntity cliente;

    @NotNull
    private LocalDateTime horarioRegistro;

    public void adicionarProduto(ItemPedidoEntity produto) {
        listaProdutos.add(produto);
        produto.getId().setPedidoOrigem(this);
    }

    public void removerProduto(ItemPedidoEntity produto) {
        listaProdutos.remove(produto);
        produto.getId().setPedidoOrigem(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ItemPedidoEntity> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(Set<ItemPedidoEntity> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(LocalDateTime horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }
}
