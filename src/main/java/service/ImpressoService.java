package service;

import entity.Impresso;
import entity.Livro;
import repository.LivroRepository;

import java.util.List;

public class ImpressoService {

    private final LivroRepository repository;

    public ImpressoService(LivroRepository repository) {
        this.repository = repository;
    }

    public Livro cadastrar(Livro livro) {
        if (livro == null) {
            return null;
        }
        repository.save(livro);
        Livro persisted = repository.findById(livro.getId());
        return persisted != null ? persisted : livro;
    }

    public List<Impresso> listar() {
        return repository.findImpressos();
    }

//    public void atualizarEstoque(Integer estoque) {
//        this.estoque--;
//    }
}
