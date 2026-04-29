package controller;

import dto.livro.impresso.ImpressoRequest;
import dto.livro.impresso.ImpressoResponse;
import entity.Impresso;
import mapper.LivroMapper;
import service.ImpressoService;

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
}
