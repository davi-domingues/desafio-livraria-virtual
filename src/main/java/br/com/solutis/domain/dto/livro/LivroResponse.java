package br.com.solutis.domain.dto.livro;

public abstract class LivroResponse {

    private Integer id;
    private String titulo;
    private String autores;
    private String editora;
    private Double preco;

    public LivroResponse() {
    }

    public LivroResponse(Integer id, String titulo, String autores, String editora, Double preco) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.editora = editora;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutores() {
        return autores;
    }

    public void setAutores(String autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "\"livro\": {\n" +
                "  \"id\": " + id + ",\n" +
                "  \"titulo\": " + quote(titulo) + ",\n" +
                "  \"autores\": " + quote(autores) + ",\n" +
                "  \"editora\": " + quote(editora) + ",\n" +
                "  \"preco\": " + preco + "\n" +
                "}";
    }

    protected String quote(String value) {
        if (value == null) {
            return "null";
        }
        return "\"" + value.replace("\"", "\\\"") + "\"";
    }
}
