package br.com.solutis.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cliente;
    private Double valor;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Livro> livros;

    public Venda() {
    }

    public Venda(Integer id, String cliente, Double valor, List<Livro> livros) {
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Venda{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", valor=" + valor +
                ", livros=" + livros +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
}
