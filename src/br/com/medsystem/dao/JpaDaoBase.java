package br.com.medsystem.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.medsystem.model.IBean;

public abstract class JpaDaoBase<T extends IBean> implements IDao<T> {

    private final Class<T> classe;
    protected static EntityManager em = JpaUtil.getEntityManager();

    @SuppressWarnings("unchecked")
    public JpaDaoBase() {
        this.classe = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void salva(T t) {
        em.getTransaction().begin();
        if (t.getId() == null)
            em.persist(t);
        else
            em.merge(t);
        em.getTransaction().commit();
    }

    public void remove(T t) {
        em.getTransaction().begin();
        em.remove(this.buscaPorld(t.getId()));
        em.getTransaction().commit();
    }

    public void atualiza(T t) {
        em.getTransaction().begin();
        em.merge(t);
        em.getTransaction().commit();
    }

    public List<T> listaTodos() {
        Query query = em.createQuery("select t from " + classe.getName()+ " t ");
        List<T> lista = query.getResultList();
        return lista;
    }

    public T buscaPorld(Long id) {
        return (T) em.find(classe, id);
    }
    
    public List<T> listaPaginada(int ini, int max) {
        Query query = em.createQuery("select t from " + classe.getName()+ " t ");
        query.setFirstResult(ini);
        query.setMaxResults(max);
        List<T> lista = query.getResultList();
        return lista;
    }
}