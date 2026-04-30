package controller;

import dto.livro.eletronico.EletronicoRequest;
import dto.livro.eletronico.EletronicoResponse;
import entity.Eletronico;
import mapper.LivroMapper;
import service.EletronicoService;

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
