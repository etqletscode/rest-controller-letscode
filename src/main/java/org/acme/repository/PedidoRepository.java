package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.PedidoEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class PedidoRepository implements PanacheRepositoryBase<PedidoEntity, Long> {

}
