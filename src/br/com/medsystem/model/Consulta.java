package br.com.medsystem.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQueries({
    @NamedQuery(name="Consulta.buscaPorId", query="SELECT c FROM Consulta c where c.id = :id "),
    @NamedQuery(name="Consulta.buscaPorPaciente", query="SELECT c FROM Consulta c where c.paciente = :paciente "),
    @NamedQuery(name="Consulta.buscaPorDoutor", query="SELECT c FROM Consulta c where c.doutor = :doutor "),
    @NamedQuery(name="Consulta.buscaPorTitulo", query="SELECT c FROM Consulta c where c.titulo = :titulo")
})
 

@Entity
public class Consulta implements IBean {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataConsulta;
    
    private String titulo;
    private String observacoes;
    
    @ManyToOne
    @XmlTransient
    private Usuario paciente;
    
    @ManyToOne
    @XmlTransient
    private Doutor doutor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(Usuario paciente) {
        this.paciente = paciente;
    }

    public Doutor getDoutor() {
        return doutor;
    }

    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }

}
