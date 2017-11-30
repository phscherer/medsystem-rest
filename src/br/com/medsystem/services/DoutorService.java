package br.com.medsystem.services;

import java.net.URI;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.Status;
import br.com.medsystem.dao.DoutorDAO;
import br.com.medsystem.dao.DoutorDaoFactory;
import br.com.medsystem.exceptions.DoutorJaExisteException;
import br.com.medsystem.model.Doutor;
import br.com.medsystem.model.rest.Doutores;

@Path("/doutores")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
public class DoutorService {
    
    DoutorDAO doutorDao = DoutorDaoFactory.getInstance().getDoutorDAO();
    private static final int TAMANHO_PAGINA = 10;
    
    @GET
    @Path("{nome}")
    public Doutor encontreDoutor(@PathParam("nome") String nome) {
        Doutor doctor = doutorDao.buscaPorNome(nome);
        if (doctor != null)
            return doctor;
        throw new WebApplicationException(Status.NOT_FOUND);
    }
    
    @GET
    public Doutores listeTodosOsDoutores(@QueryParam("pagina") int pagina) {
        List<Doutor> doctors = doutorDao.listaPaginada(pagina, TAMANHO_PAGINA);
        return new Doutores(doctors);
    }
    
    @POST
    public Response criarDoutor(Doutor doutor) {
        
        try {
            doutorDao.salva(doutor);
        } catch (DoutorJaExisteException e) {
            throw new WebApplicationException(Status.CONFLICT);
        }

        URI uri = UriBuilder.fromPath("doutores/{nome}").build(doutor.getNome());
        return Response.created(uri).entity(doutor).build();
    }
    
    @PUT
    public void atualizarDoutor(Doutor doutor) {
        doutorDao.atualiza(doutor);
    }

    @DELETE
    @Path("{nome}")
    public void apagarDoutor(@PathParam("nome") String nome) {
         doutorDao.remove(nome);
    }
    
}
