package application;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repository.LivroRepository;
import repository.VendaRepository;
import service.EletronicoService;
import service.ImpressoService;
import service.VendaService;

public class ApplicationContext {

    private final EletronicoService eletronicoService;
    private final ImpressoService impressoService;
    private final VendaService vendaService;

    public ApplicationContext() {
        EntityManagerFactory emf;
        try {
            emf = Persistence.createEntityManagerFactory("livaria-virtual");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        this.eletronicoService = new EletronicoService(new LivroRepository(emf));
        this.impressoService = new ImpressoService(new LivroRepository(emf));
        this.vendaService = new VendaService(new VendaRepository(emf));
    }

    public EletronicoService getEletronicoService() {
        return eletronicoService;
    }

    public ImpressoService getImpressoService() {
        return impressoService;
    }

    public VendaService getVendaService() {
        return vendaService;
    }
}
