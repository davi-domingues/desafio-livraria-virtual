package br.com.solutis.domain.service;

import br.com.solutis.domain.entity.Impresso;
import br.com.solutis.domain.entity.Livro;
import br.com.solutis.domain.repository.LivroRepository;

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

    public Integer getNumImpressos() {
        return repository.countImpressos();
    }

//    public void atualizarEstoque(Integer estoque) {
//        this.estoque--;
//    }
}
