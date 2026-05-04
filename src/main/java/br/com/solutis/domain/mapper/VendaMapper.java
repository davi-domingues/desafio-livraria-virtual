package br.com.solutis.domain.mapper;

import br.com.solutis.domain.dto.livro.LivroResponse;
import br.com.solutis.domain.dto.venda.VendaRequest;
import br.com.solutis.domain.dto.venda.VendaResponse;
import br.com.solutis.domain.entity.Venda;

import java.util.List;
import java.util.stream.Collectors;

public class VendaMapper {

    public static Venda toEntity(VendaRequest request) {
        if (request == null) {
            return null;
        }
        Venda venda = new Venda();
        venda.setCliente(request.getCliente());
        venda.setValor(request.getValor());
        return venda;
    }

    public static VendaResponse toResponse(Venda venda) {
        if (venda == null) {
            return null;
        }
        VendaResponse response = new VendaResponse();
        response.setId(venda.getId());
        response.setCliente(venda.getCliente());
        response.setValor(venda.getValor());
        response.setLivros((List<LivroResponse>) LivroMapper.toResponse(venda.getLivros()));
        return response;
    }

    public static List<VendaResponse> toResponse(List<Venda> vendas) {
        if (vendas == null) {
            return null;
        }
        return vendas.stream()
                .map(VendaMapper::toResponse)
                .collect(Collectors.toList());
    }
}
