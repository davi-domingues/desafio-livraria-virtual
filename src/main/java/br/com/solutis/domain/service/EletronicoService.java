package br.com.solutis.domain.service;

import br.com.solutis.domain.entity.Eletronico;
import br.com.solutis.domain.entity.Livro;
import br.com.solutis.domain.repository.LivroRepository;

import java.util.List;

public class EletronicoService {

    private final LivroRepository repository;

    public EletronicoService(LivroRepository repository) {
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

    public List<Eletronico> listar() {
        return repository.findEletronicos();
    }

    public Integer getNumEletronicos() {
        return repository.countEletronicos();
    }
}
