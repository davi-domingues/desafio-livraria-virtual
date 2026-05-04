package br.com.solutis.domain.dto.venda;

import br.com.solutis.domain.dto.livro.LivroResponse;

import java.util.List;

public class VendaResponse {

    private Integer id;
    private String cliente;
    private Double valor;
    private List<LivroResponse> livros;

    public VendaResponse() {
    }

    public VendaResponse(Integer id, String cliente, Double valor, List<LivroResponse> livros) {
        this.id = id;
        this.cliente = cliente;
        this.valor = valor;
        this.livros = livros;
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

    public List<LivroResponse> getLivros() {
        return livros;
    }

    public void setLivros(List<LivroResponse> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "\"venda\": {\n" +
                "  \"id\": " + id + ",\n" +
                "  \"cliente\": " + quote(cliente) + ",\n" +
                "  \"valor\": " + valor + ",\n" +
                "  \"livros\": " + livros + "\n" +
                "}";
    }

    private String quote(String value) {
        if (value == null) {
            return "null";
        }
        return "\"" + value.replace("\"", "\\\"") + "\"";
    }
}
