package br.com.medsystem.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.medsystem.model.Usuario;

public class UsuarioDAO extends JpaDaoBase<Usuario> implements IDao<Usuario> {
    
    public Usuario buscaPorNome(String nomeDeUsuario) {
        Query query = em.createNamedQuery("Usuario.buscaPorNome").setParameter("nomeUsuario", nomeDeUsuario);
        List<Usuario> usuarios = query.getResultList();
        if (!usuarios.isEmpty())
            return usuarios.get(0);
        return null;
    }
    
    public boolean isLoginValido(String nomeDeUsuario, String senha) {
        Query query = em.createNamedQuery("Usuario.verificaLogin")
                .setParameter("nomeUsuario", nomeDeUsuario)
                .setParameter("senha", senha);
        return !query.getResultList().isEmpty();
    }
    
    public void remove(Long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Usuario c WHERE c.id = :id ").setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
}
