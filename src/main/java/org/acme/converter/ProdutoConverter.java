package org.acme.converter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import org.acme.entity.ProdutoEntity;
import org.acme.models.Produto;

@ApplicationScoped
public class ProdutoConverter {
    public Produto convertEntityTo(ProdutoEntity produtoAtualizado) {
        Produto produtoSalvo = new Produto();
        produtoSalvo.setDesconto(String.valueOf(produtoAtualizado.getDesconto()));
        produtoSalvo.setDescricao(produtoAtualizado.getDescricao());
        produtoSalvo.setEstoque(produtoAtualizado.getEstoque());
        produtoSalvo.setId(produtoAtualizado.getId());
        produtoSalvo.setFoto(produtoAtualizado.getFoto());
        produtoSalvo.setNome(produtoAtualizado.getNome());
        produtoSalvo.setPreco(String.valueOf((produtoAtualizado.getPreco())));
        return produtoSalvo;
    }

    public List<Produto> convertEntityTo(List<ProdutoEntity> produtos) {
        return produtos.stream()
                .map(this::convertEntityTo)
                .collect(Collectors.toList());
    }

    public ProdutoEntity convertProductTo(Produto produtoAtualizado) {
        ProdutoEntity produtoSalvo = new ProdutoEntity();
        produtoSalvo.setDesconto(new BigDecimal(produtoAtualizado.getDesconto()));
        produtoSalvo.setDescricao(produtoAtualizado.getDescricao());
        produtoSalvo.setEstoque(produtoAtualizado.getEstoque());
        produtoSalvo.setId(produtoAtualizado.getId());
        produtoSalvo.setFoto(produtoAtualizado.getFoto());
        produtoSalvo.setNome(produtoAtualizado.getNome());
        produtoSalvo.setPreco(new BigDecimal(produtoAtualizado.getPreco()));
        return produtoSalvo;
    }

    public List<ProdutoEntity> convertProductTo(List<Produto> produtos) {
        return produtos.stream()
                .map(this::convertProductTo)
                .collect(Collectors.toList());
    }

    public void updateProduct(ProdutoEntity produtoSalvo, Produto produtoAtualizado) {
        produtoSalvo.setDesconto(new BigDecimal(produtoAtualizado.getDesconto()));
        produtoSalvo.setDescricao(produtoAtualizado.getDescricao());
        produtoSalvo.setEstoque(produtoAtualizado.getEstoque());
        produtoSalvo.setFoto(produtoAtualizado.getFoto());
        produtoSalvo.setNome(produtoAtualizado.getNome());
        produtoSalvo.setPreco(new BigDecimal(produtoAtualizado.getPreco()));
    }
}
