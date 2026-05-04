package br.com.solutis.domain.controller;

import br.com.solutis.domain.dto.livro.impresso.ImpressoRequest;
import br.com.solutis.domain.dto.livro.impresso.ImpressoResponse;
import br.com.solutis.domain.entity.Impresso;
import br.com.solutis.domain.mapper.LivroMapper;
import br.com.solutis.domain.service.ImpressoService;

import java.util.List;

public class ImpressoController {

    private final ImpressoService service;

    public ImpressoController(ImpressoService service) {
        this.service = service;
    }

    public ImpressoResponse cadastrar(ImpressoRequest request) {
        Impresso impresso = LivroMapper.toEntity(request);
        Impresso persisted = (Impresso) service.cadastrar(impresso);
        return LivroMapper.toResponse(persisted);
    }

    public List<ImpressoResponse> listar() {
        return (List<ImpressoResponse>) LivroMapper.toResponse(service.listar());
    }

    public Integer getNumImpressos() {
        return service.getNumImpressos();
    }
}
