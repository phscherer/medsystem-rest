package br.com.medsystem.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.envers.NotAudited;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

@NamedQuery(name="Doutor.buscaPorNome", query="SELECT d FROM Doutor d where d.nome = :nome ") 

@Entity
public class Doutor implements IBean {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name="id", nullable=false, unique=true)
    private Long id;
    
    private String nome;
    private String faixaIdade;

    @NotAudited
    @OneToMany(mappedBy="doutor", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "consultas")
    @XmlElement(name = "consulta")
    private List<Consulta> consultasRelacionadas;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFaixaIdade() {
        return faixaIdade;
    }

    public void setFaixaIdade(String faixaIdade) {
        this.faixaIdade = faixaIdade;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Doutor other = (Doutor) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }
    
}
