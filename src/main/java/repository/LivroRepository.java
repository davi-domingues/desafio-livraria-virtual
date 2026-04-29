package repository;

import entity.Livro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import entity.Eletronico;
import entity.Impresso;

public class LivroRepository {

    private final EntityManagerFactory emf;

    public LivroRepository(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void save(Livro livro) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(livro);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Livro findById(Integer id) {
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

    public java.util.List<Livro> findAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Livro> query = em.createQuery("select l from Livro l", Livro.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public java.util.List<Impresso> findImpressos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Impresso> query = em.createQuery("select i from Impresso i", Impresso.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public java.util.List<Eletronico> findEletronicos() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Eletronico> query = em.createQuery("select e from Eletronico e", Eletronico.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
