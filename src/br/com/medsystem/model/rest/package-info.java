@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(value=JaxbAdapter.class)
})

package br.com.medsystem.model.rest;

import javax.ws.rs.core.Link.JaxbAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
