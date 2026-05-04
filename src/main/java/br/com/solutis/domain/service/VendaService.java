package br.com.solutis.domain.service;

import br.com.solutis.domain.entity.Impresso;
import br.com.solutis.domain.entity.Livro;
import br.com.solutis.domain.entity.Venda;
import br.com.solutis.domain.repository.VendaRepository;

import java.util.ArrayList;
import java.util.List;

public class VendaService {

    private final VendaRepository repository;

    public VendaService(VendaRepository repository) {
        this.repository = repository;
    }

    public void listarLivros(List<Livro> livros){}

    public Venda realizarVenda(Venda venda, List<Integer> idsLivros) {
        if (venda == null) {
            return null;
        }
        List<Livro> livros = addLivros(idsLivros);
        venda.setLivros(livros);
        repository.save(venda);
        Venda persisted = repository.findVendaById(venda.getId());
        return persisted != null ? persisted : venda;
    }

    public List<Livro> addLivros(List<Integer> idsLivros){
        if (idsLivros == null) {
            return null;
        }
        List<Livro> livros = new ArrayList<>();
        for (Integer id : idsLivros) {
            Livro livro = repository.findLivroById(id);
            if (livro == null) {
                continue;
            }
            if (livro instanceof Impresso) {
                Impresso impresso = (Impresso) livro;
                Integer estoque = impresso.getEstoque();
                if (estoque == null || estoque <= 0) {
                    continue;
                }
                impresso.setEstoque(estoque - 1);
                repository.updateLivro(impresso);
            }
            livros.add(livro);
        }
        return livros;
    }

    public List<Venda> listar() {
        return repository.listar();
    }

    public Integer getNumVendas() {
        return repository.countVendas();
    }
}
