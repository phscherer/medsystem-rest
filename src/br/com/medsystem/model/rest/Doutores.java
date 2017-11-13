package br.com.medsystem.model.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import br.com.medsystem.model.Doutor;

@XmlRootElement
public class Doutores {
    
    private List<Doutor> doutores = new ArrayList<>();
    
    public Doutores() {}
    
    public Doutores(List<Doutor> doutores) {
        this.doutores = doutores;
    }
    
    @XmlTransient
    public List<Doutor> getDoutores() {
        return doutores;
    }
    
    public void setDoutores(List<Doutor> doutores) {
        this.doutores = doutores;
    }
    
    @XmlElement(name="link")
    public List<Link> getLinks() {
        List<Link> links = new ArrayList<>();
        for (Doutor doutor : getDoutores()) {
            Link link = Link.fromPath("doutores/{nome}")
                    .rel("colecao")
                    .title(doutor.getNome())
                    .build(doutor.getNome());
            links.add(link);
        }
        return links;
    }

}
