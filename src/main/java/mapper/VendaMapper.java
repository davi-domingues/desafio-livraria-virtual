package mapper;

import dto.livro.LivroResponse;
import dto.venda.VendaRequest;
import dto.venda.VendaResponse;
import entity.Venda;

import java.util.List;

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
}
