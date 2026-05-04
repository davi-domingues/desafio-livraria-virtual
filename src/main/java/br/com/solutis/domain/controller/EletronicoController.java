package br.com.solutis.domain.controller;

import br.com.solutis.domain.dto.livro.eletronico.EletronicoRequest;
import br.com.solutis.domain.dto.livro.eletronico.EletronicoResponse;
import br.com.solutis.domain.entity.Eletronico;
import br.com.solutis.domain.mapper.LivroMapper;
import br.com.solutis.domain.service.EletronicoService;

import java.util.List;

public class EletronicoController {

    private final EletronicoService service;

    public EletronicoController(EletronicoService service) {
        this.service = service;
    }

    public EletronicoResponse cadastrar(EletronicoRequest request) {
        Eletronico eletronico = LivroMapper.toEntity(request);
        Eletronico persisted = (Eletronico) service.cadastrar(LivroMapper.toEntity(request));
        return LivroMapper.toResponse(persisted);
    }

    public List<EletronicoResponse> listar() {
        return (List<EletronicoResponse>) LivroMapper.toResponse(service.listar());
    }

    public Integer getNumEletronicos() {
        return service.getNumEletronicos();
    }
}
