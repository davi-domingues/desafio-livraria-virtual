package dto.livro.impresso;

import dto.livro.LivroRequest;

public class ImpressoRequest extends LivroRequest {

    private Double frete;
    private Integer estoque;

    public ImpressoRequest() {
    }

    public ImpressoRequest(Integer id, String titulo, String autores, String editora, Double preco, Double frete, Integer estoque) {
        super(titulo, autores, editora, preco);
        this.frete = frete;
        this.estoque = estoque;
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

