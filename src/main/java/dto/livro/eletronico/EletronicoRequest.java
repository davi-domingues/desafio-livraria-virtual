package dto.livro.eletronico;

import dto.livro.LivroRequest;

public class EletronicoRequest extends LivroRequest {

    private Integer tamanho;

    public EletronicoRequest() {
    }

    public EletronicoRequest(String titulo, String autores, String editora, Double preco, Integer tamanho) {
        super(titulo, autores, editora, preco);
        this.tamanho = tamanho;
    }

    public Integer getTamanho() {
        return tamanho;
    }

    public void setTamanho(Integer tamanho) {
        this.tamanho = tamanho;
    }
}

