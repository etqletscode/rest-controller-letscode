package org.acme.services;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.acme.converter.ProdutoConverter;
import org.acme.entity.ProdutoEntity;
import org.acme.models.Mensagem;
import org.acme.models.Produto;
import org.acme.models.ProdutoLista;
import org.acme.repository.ProdutoRepository;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;

@RequestScoped
public class ProdutoService {

    @Inject
    ProdutoRepository repository;

    @Inject
    ProdutoConverter converter;

    @Transactional
    public Produto salvar(Produto novoProduto) {
        final ProdutoEntity produtoEntity = converter.convertProductTo(novoProduto);
        repository.persist(produtoEntity);
        return converter.convertEntityTo(produtoEntity);
    }

    @Transactional
    public List<Produto> salvarLista(List<Produto> novoProduto) {
        final List<ProdutoEntity> produtoEntityList = converter.convertProductTo(novoProduto);
        repository.persist(produtoEntityList);
        return converter.convertEntityTo(produtoEntityList);
    }

    @Transactional
    public Mensagem excluirPorId(Long id) {
        Mensagem res = new Mensagem();
        res.setId(id);
        if (repository.deleteById(id)) {
            res.setMensagem("Produto excluído com sucesso");
        } else {
            res.setMensagem("Produto não encontrado");
        }
        return res;
    }

    @Transactional
    public Produto atualizar(Produto produtoAtualizado) {
        final ProdutoEntity produtoSalvo = ProdutoEntity.findById(produtoAtualizado.getId());
        if (produtoSalvo == null) {
            throw new RuntimeException();
        }
        converter.updateProduct(produtoSalvo, produtoAtualizado);
        repository.persist(produtoSalvo);
        return converter.convertEntityTo(produtoSalvo);
    }

    public Produto buscaPorId(Long id) {
        final ProdutoEntity produtoSalvo = ProdutoEntity.findById(id);
        if (produtoSalvo == null) {
            throw new RuntimeException();
        }
        return converter.convertEntityTo(produtoSalvo);
    }

    public boolean isEmpty() {
        return repository.count() == 0;
    }

    public ProdutoLista listar(String filtro, Integer page, Integer size) {
        PanacheQuery<ProdutoEntity> produtoPanacheQuery = repository.findByNomeOrDescricao(filtro, Page.of(page, size));
        ProdutoLista p = new ProdutoLista();
        p.setList(converter.convertEntityTo(produtoPanacheQuery.list()));
        p.setSize(produtoPanacheQuery.list().size());
        p.setPage(page);
        p.setTotal(produtoPanacheQuery.pageCount());
        return p;
    }
}
