package entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "fkLivro")
@DiscriminatorValue("impresso")
public class Impresso extends Livro{

    private Double frete;
    private Integer estoque;

    public Impresso() {
    }

    public Impresso(Integer id, String titulo, String autores, String editora, Double preco, Double frete, Integer estoque) {
        super(id, titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Impresso{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", autores='" + getAutores() + '\'' +
                ", editora='" + getEditora() + '\'' +
                ", preco=" + getPreco() +
                ", frete=" + frete +
                ", estoque=" + estoque +
                '}';
    }

    public Double getFrete() {
        return frete;
    }

    public void setFrete(Double frete) {
        this.frete = frete;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }
}
