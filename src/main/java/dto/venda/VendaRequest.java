package dto.venda;

import dto.livro.LivroRequest;

import java.util.List;

public class VendaRequest {

    private String cliente;
    private Double valor;
    private List<Integer> idLivros;

    public VendaRequest() {
    }

    public VendaRequest(String cliente, Double valor, List<Integer> idLivros) {
        this.cliente = cliente;
        this.valor = valor;
        this.idLivros = idLivros;
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

    public List<Integer> getIdLivros() {
        return idLivros;
    }

    public void setIdLivros(List<Integer> idLivros) {
        this.idLivros = idLivros;
    }
}

