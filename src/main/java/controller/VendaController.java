package controller;

import dto.venda.VendaRequest;
import dto.venda.VendaResponse;
import entity.Venda;
import mapper.VendaMapper;
import service.VendaService;

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
}
