package org.acme.models;

import java.time.LocalDateTime;
import java.util.Set;

public class Pedido {
    private Long id;
    private Set<ItemPedido> listaProdutos;
    private Cliente cliente;
    private LocalDateTime horarioRegistro;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<ItemPedido> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(Set<ItemPedido> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getHorarioRegistro() {
        return horarioRegistro;
    }

    public void setHorarioRegistro(LocalDateTime horarioRegistro) {
        this.horarioRegistro = horarioRegistro;
    }
}
