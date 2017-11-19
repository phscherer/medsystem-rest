package br.com.medsystem.model;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
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
@NamedQueries({
    @NamedQuery(name="Usuario.buscaPorNome",
                query="SELECT u FROM Usuario u where u.nome = :nome"),
    @NamedQuery(name="Usuario.verificaLogin",
                query="SELECT u FROM Usuario u WHERE u.nomeUsuario = :nomeUsuario AND u.senha = :senha"),
})

@Entity
public class Usuario implements IBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeUsuario;
    private String senha;
    private String nome;
    private int idade;
    private String genero;
    
    @OneToMany(mappedBy="paciente", cascade = CascadeType.ALL)
    @XmlElementWrapper(name = "consultas")
    @XmlElement(name = "consulta")
    private List<Consulta> consultasRelacionadas;
    
    public Usuario() {
        super();
    }
    
    public Usuario(String nomeUsuario, String senha, String nome, int idade, String genero) {
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.nome = nome;
        this.idade = idade;
        this.genero = genero;
    }
    
    public List<Consulta> getConsultasRelacionadas() {
        return consultasRelacionadas;
    }

    public void setConsultasRelacionadas(List<Consulta> consultasRelacionadas) {
        this.consultasRelacionadas = consultasRelacionadas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((nomeUsuario == null) ? 0 : nomeUsuario.hashCode());
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
        Usuario other = (Usuario) obj;
        if (nomeUsuario == null) {
            if (other.nomeUsuario != null)
                return false;
        } else if (!nomeUsuario.equals(other.nomeUsuario))
            return false;
        return true;
    }
    
    
}
