package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.ItemPedidoEntity;
import org.acme.entity.ItemPedidoEntityPK;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ItemPedidoRepository implements PanacheRepositoryBase<ItemPedidoEntity, ItemPedidoEntityPK> {

}