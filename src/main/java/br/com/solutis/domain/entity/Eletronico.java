package br.com.solutis.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "fkLivro")
@DiscriminatorValue("eletronico")
public class Eletronico extends Livro{

    private Integer tamanho;

    public Eletronico() {
    }

    public Eletronico(Integer id, String titulo, String autores, String editora, Double preco, Integer tamanho) {
        super(id, titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "Eletronico{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", autores='" + getAutores() + '\'' +
                ", editora='" + getEditora() + '\'' +
                ", preco=" + getPreco() +
                ", tamanho=" + tamanho +
                '}';
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }
}
