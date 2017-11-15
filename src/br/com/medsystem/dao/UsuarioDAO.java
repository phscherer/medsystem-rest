package br.com.medsystem.dao;

import java.util.List;

import javax.persistence.Query;

import br.com.medsystem.model.Usuario;

public class UsuarioDAO extends JpaDaoBase<Usuario> implements IDao<Usuario> {
    
    public Usuario buscaPorNome(String nome) {
        Query query = em.createNamedQuery("Usuario.buscaPorNome").setParameter("nome", nome);
        List<Usuario> usuarios = query.getResultList();
        if (!usuarios.isEmpty())
            return usuarios.get(0);
        return null;
    }
    
    public Usuario validarLogin(String nomeDeUsuario, String senha) {
        Query query = em.createNamedQuery("Usuario.verificaLogin")
                .setParameter("nomeUsuario", nomeDeUsuario)
                .setParameter("senha", senha);
        List<Usuario> usuarios = query.getResultList();
        if (!usuarios.isEmpty()) {
            return usuarios.get(0);
        }
        return null;
        
    }
    
    public void remove(String nomeUsuario) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Usuario c WHERE c.nomeUsuario = :nomeUsuario ").setParameter("nomeUsuario", nomeUsuario);
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
}
