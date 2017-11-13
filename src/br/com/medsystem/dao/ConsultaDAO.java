package br.com.medsystem.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import br.com.medsystem.model.Consulta;
import br.com.medsystem.model.rest.Consultas;

public class ConsultaDAO extends JpaDaoBase<Consulta> implements IDao<Consulta> {
    
    public Consultas buscaPorPaciente(Long idPaciente) {
        Query query = em.createNamedQuery("Consulta.buscaPorPaciente").setParameter("paciente", new UsuarioDAO().buscaPorld(idPaciente));
        List<Consulta> consultas = query.getResultList();
        if (!consultas.isEmpty())
            return new Consultas(consultas);
        return null;
    }
    
    public Consultas buscaPorDoutor(Long idDoutor) {
        Query query = em.createNamedQuery("Consulta.buscaPorDoutor").setParameter("doutor", new DoutorDAO().buscaPorld(idDoutor));
        List<Consulta> consultas = query.getResultList();
        if (!consultas.isEmpty())
            return new Consultas(consultas);
        return null;
    }
    
    public Consultas buscaPorData(Date dataConsulta) {
        Query query = em.createNamedQuery("Consulta.buscaPorData").setParameter("dataConsulta", dataConsulta);
        List<Consulta> consultas = query.getResultList();
        if (!consultas.isEmpty())
            return new Consultas(consultas);
        return null;
    }
    
    public void cancelarConsulta(Long id) {
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Consulta c WHERE c.id = :id ").setParameter("id", id);
        query.executeUpdate();
        em.getTransaction().commit();
    }
    
    
}
