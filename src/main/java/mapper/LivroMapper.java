package mapper;

import dto.livro.LivroRequest;
import dto.livro.LivroResponse;
import dto.livro.eletronico.EletronicoRequest;
import dto.livro.eletronico.EletronicoResponse;
import dto.livro.impresso.ImpressoRequest;
import dto.livro.impresso.ImpressoResponse;
import entity.Eletronico;
import entity.Impresso;
import entity.Livro;

import java.util.List;
import java.util.stream.Collectors;

public class LivroMapper {

    public static Impresso toEntity(ImpressoRequest request) {
        if (request == null) {
            return null;
        }
        Impresso impresso = new Impresso();
        impresso.setTitulo(request.getTitulo());
        impresso.setAutores(request.getAutores());
        impresso.setEditora(request.getEditora());
        impresso.setPreco(request.getPreco());
        impresso.setFrete(request.getFrete());
        impresso.setEstoque(request.getEstoque());
        return impresso;
    }

    public static Eletronico toEntity(EletronicoRequest request) {
        if (request == null) {
            return null;
        }
        Eletronico eletronico = new Eletronico();
        eletronico.setTitulo(request.getTitulo());
        eletronico.setAutores(request.getAutores());
        eletronico.setEditora(request.getEditora());
        eletronico.setPreco(request.getPreco());
        eletronico.setTamanho(request.getTamanho());
        return eletronico;
    }

    public static Livro toEntity(LivroRequest request) {
        if (request instanceof ImpressoRequest) {
            return toEntity((ImpressoRequest) request);
        }
        if (request instanceof EletronicoRequest) {
            return toEntity((EletronicoRequest) request);
        }
        if (request == null) {
            return null;
        }
        throw new IllegalArgumentException("Tipo de LivroRequest nao suportado: " + request.getClass().getName());
    }

    public static List<? extends Livro> toEntity(List<? extends LivroRequest> requests) {
        if (requests == null) {
            return null;
        }

        return requests.stream()
                .map(LivroMapper::toEntity)
                .collect(Collectors.toList());
    }
//    public static List<Impresso> toEntity(List<ImpressoRequest> requests) {
//        if (requests == null) {
//            return null;
//        }
//        return requests.stream()
//                .map(LivroMapper::toEntity)
//                .collect(Collectors.toList());
//    }
//
//    public static List<Eletronico> toEntity(List<EletronicoRequest> requests) {
//        if (requests == null) {
//            return null;
//        }
//        return requests.stream()
//                .map(LivroMapper::toEntity)
//                .collect(Collectors.toList());
//    }
//
//    public static List<Livro> toEntity(List<LivroRequest> requests) {
//        if (requests == null) {
//            return null;
//        }
//        return requests.stream()
//                .map(LivroMapper::toEntity)
//                .collect(Collectors.toList());
//    }

    public static ImpressoResponse toResponse(Impresso impresso) {
        if (impresso == null) {
            return null;
        }
        ImpressoResponse response = new ImpressoResponse();
        response.setId(impresso.getId());
        response.setTitulo(impresso.getTitulo());
        response.setAutores(impresso.getAutores());
        response.setEditora(impresso.getEditora());
        response.setPreco(impresso.getPreco());
        response.setFrete(impresso.getFrete());
        response.setEstoque(impresso.getEstoque());
        return response;
    }

    public static EletronicoResponse toResponse(Eletronico eletronico) {
        if (eletronico == null) {
            return null;
        }
        EletronicoResponse response = new EletronicoResponse();
        response.setId(eletronico.getId());
        response.setTitulo(eletronico.getTitulo());
        response.setAutores(eletronico.getAutores());
        response.setEditora(eletronico.getEditora());
        response.setPreco(eletronico.getPreco());
        response.setTamanho(eletronico.getTamanho());
        return response;
    }

    public static LivroResponse toResponse(Livro livro) {
        if (livro instanceof Impresso) {
            return toResponse((Impresso) livro);
        }
        if (livro instanceof Eletronico) {
            return toResponse((Eletronico) livro);
        }
        if (livro == null) {
            return null;
        }
        throw new IllegalArgumentException("Tipo de Livro nao suportado: " + livro.getClass().getName());
    }

    public static List<? extends LivroResponse> toResponse(List<? extends Livro> livros) {
        if (livros == null) {
            return null;
        }
        return livros.stream()
                .map(LivroMapper::toResponse)
                .collect(Collectors.toList());
    }
}
