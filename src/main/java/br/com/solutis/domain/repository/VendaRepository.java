package br.com.solutis.domain.repository;

import br.com.solutis.domain.entity.Livro;
import br.com.solutis.domain.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

public class VendaRepository {

    private final EntityManagerFactory emf;

    public VendaRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public Livro findLivroById(Integer id) {
        if (id == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Livro.class, id);
        } finally {
            em.close();
        }
    }

    public void save(Venda venda) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(venda);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Venda findVendaById(Integer id) {
        if (id == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Venda> query = em.createQuery(
                    "select v from Venda v left join fetch v.livros where v.id = :id",
                    Venda.class
            );
            query.setParameter("id", id);
            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

    public Livro updateLivro(Livro livro) {
        if (livro == null) {
            return null;
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Livro merged = em.merge(livro);
            em.getTransaction().commit();
            return merged;
        } finally {
            em.close();
        }
    }

    public java.util.List<Venda> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Venda> query = em.createQuery(
                    "select distinct v from Venda v left join fetch v.livros",
                    Venda.class
            );
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Integer countVendas() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery("select count(v) from Venda v", Long.class);
            Long total = query.getSingleResult();
            return total != null ? total.intValue() : 0;
        } finally {
            em.close();
        }
    }
}
