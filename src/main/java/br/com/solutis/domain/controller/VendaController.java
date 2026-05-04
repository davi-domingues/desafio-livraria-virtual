package br.com.solutis.domain.controller;

import br.com.solutis.domain.dto.venda.VendaRequest;
import br.com.solutis.domain.dto.venda.VendaResponse;
import br.com.solutis.domain.entity.Venda;
import java.util.List;
import br.com.solutis.domain.mapper.VendaMapper;
import br.com.solutis.domain.service.VendaService;

public class VendaController {

    private final VendaService service;

    public VendaController(VendaService service) {
        this.service = service;
    }

    public VendaResponse realizarVenda(VendaRequest request) {
        if (request == null) {
            return null;
        }
        Venda venda = VendaMapper.toEntity(request);
        Venda persisted = service.realizarVenda(venda, request.getIdLivros());
        return VendaMapper.toResponse(persisted);
    }

    public List<VendaResponse> listar() {
        return VendaMapper.toResponse(service.listar());
    }

    public Integer getNumVendas() {
        return service.getNumVendas();
    }
}
