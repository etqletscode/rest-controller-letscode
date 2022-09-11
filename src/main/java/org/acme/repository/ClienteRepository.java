package org.acme.repository;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.ClienteEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class ClienteRepository implements PanacheRepositoryBase<ClienteEntity, Long> {

    public ClienteEntity findByEmail(String email) {
        return find("email like ?1", email).singleResult();
    }
}
