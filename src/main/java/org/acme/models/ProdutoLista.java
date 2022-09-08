package org.acme.models;

import java.util.List;

public class ProdutoLista {
    private List<Produto> list;
    private int page;
    private int size;
    private int total;

    public List<Produto> getList() {
        return list;
    }

    public void setList(List<Produto> list) {
        this.list = list;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
