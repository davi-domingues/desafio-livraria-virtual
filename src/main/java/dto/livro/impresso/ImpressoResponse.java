package dto.livro.impresso;

import dto.livro.LivroResponse;

public class ImpressoResponse extends LivroResponse {

    private Double frete;
    private Integer estoque;

    public ImpressoResponse() {
    }

    public ImpressoResponse(Integer id, String titulo, String autores, String editora, Double preco, Double frete, Integer estoque) {
        super(id, titulo, autores, editora, preco);
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

    @Override
    public String toString() {
        return "\"impresso\": {\n" +
                "  \"id\": " + getId() + ",\n" +
                "  \"titulo\": " + quote(getTitulo()) + ",\n" +
                "  \"autores\": " + quote(getAutores()) + ",\n" +
                "  \"editora\": " + quote(getEditora()) + ",\n" +
                "  \"preco\": " + getPreco() + ",\n" +
                "  \"frete\": " + frete + ",\n" +
                "  \"estoque\": " + estoque + "\n" +
                "}";
    }
}
