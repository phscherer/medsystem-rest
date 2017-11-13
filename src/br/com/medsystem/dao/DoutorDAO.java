package br.com.medsystem.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.medsystem.model.Doutor;

public class DoutorDAO extends JpaDaoBase<Doutor> implements IDao<Doutor> {
    
    public Doutor buscaPorNome(String nomeDoDoutor) {
        Query query = em.createNamedQuery("Doutor.buscaPorNome").setParameter("nome", nomeDoDoutor);
        List<Doutor> doutores = query.getResultList();
        if (!doutores.isEmpty())
            return doutores.get(0);
        return null;
    }
    
    public void remove(Long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Doutor c WHERE c.id = :id ").setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
}
