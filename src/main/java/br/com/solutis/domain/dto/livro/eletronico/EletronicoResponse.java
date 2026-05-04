package br.com.solutis.domain.dto.livro.eletronico;

import br.com.solutis.domain.dto.livro.LivroResponse;

public class EletronicoResponse extends LivroResponse {

    private Integer tamanho;

    public EletronicoResponse() {
    }

    public EletronicoResponse(Integer id, String titulo, String autores, String editora, Double preco, Integer tamanho) {
        super(id, titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }

    @Override
    public String toString() {
        return "\"eletronico\": {\n" +
                "  \"id\": " + getId() + ",\n" +
                "  \"titulo\": " + quote(getTitulo()) + ",\n" +
                "  \"autores\": " + quote(getAutores()) + ",\n" +
                "  \"editora\": " + quote(getEditora()) + ",\n" +
                "  \"preco\": " + getPreco() + ",\n" +
                "  \"tamanho\": " + tamanho + "\n" +
                "}";
    }
}
