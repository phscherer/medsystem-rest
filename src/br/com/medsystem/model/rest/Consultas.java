package br.com.medsystem.model.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import br.com.medsystem.model.Consulta;

@XmlRootElement
public class Consultas {
    
private List<Consulta> consultas = new ArrayList<>();
    
    public Consultas() {}
    
    public Consultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    @XmlTransient
    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
    
    @XmlElement(name="link")
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<>();
        for (Consulta consulta : getConsultas()) {
            Link link = Link.fromPath("consultas/{id}")
                    .rel("colecao")
                    .title(consulta.getId().toString())
                    .build(consulta.getId().toString());
            links.add(link);
        }
        return links;
    }
}
