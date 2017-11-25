package br.com.medsystem.services;

import java.net.URI;
import java.util.Date;
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
import br.com.medsystem.dao.ConsultaDAO;
import br.com.medsystem.dao.ConsultaDaoFactory;
import br.com.medsystem.dao.UsuarioDAO;
import br.com.medsystem.exceptions.ConsultaJaExisteException;
import br.com.medsystem.model.Consulta;
import br.com.medsystem.model.rest.Consultas;

@Path("/consultas")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
public class ConsultaService {
    
    ConsultaDAO consultaDao = ConsultaDaoFactory.getInstance().getConsultaDAO();
    private static final int TAMANHO_PAGINA = 10;
    
    @GET
    @Path("{titulo}")
    public Consulta encontreConsultaPorTitulo(@PathParam("titulo") String titulo) {
        return consultaDao.buscaPorTitulo(titulo);
    }
    
    @GET
    @Path("/usuario/{usuario_id}")
    public Consultas encontreConsultaPorPaciente(@PathParam("usuario_id") Long idUsuario) {
        return consultaDao.buscaPorPaciente(1l);
    }
    
    @GET
    @Path("/doutor/{doutor_id}")
    public Consultas encontreConsultaPorDoutor(@PathParam("doutor_id") Long idDoutor) {
        return consultaDao.buscaPorDoutor(1l);
    }
    
    @GET
    public Consultas listeTodasAsConsultas(@QueryParam("pagina") int pagina) {
        List<Consulta> consultas = consultaDao.listaPaginada(pagina, TAMANHO_PAGINA);
        return new Consultas(consultas);
    }
    
    @POST
    @Path("usuario/{usuario_id}")
    public Response criarConsulta(@QueryParam("usuario_id") Long idUsuario, Consulta consulta) {
        try {
            consulta.setPaciente(new UsuarioDAO().buscaPorld(1l));
            consultaDao.salva(consulta);
        } catch (ConsultaJaExisteException e) {
            throw new WebApplicationException(Status.CONFLICT);
        }

        URI uri = UriBuilder.fromPath("consultas/{id}").build(consulta.getId());
        return Response.created(uri).entity(consulta).build();
    }
    
    @PUT
    @Path("{data}")
    public void atualizarConsulta(@PathParam("data") Date dataDaConsulta, Consulta consulta) {
        consulta.setDataConsulta(dataDaConsulta);
        consultaDao.atualiza(consulta);
    }

    @DELETE
    @Path("{titulo}")
    public void apagarConsulta(@PathParam("titulo") String titulo) {
        consultaDao.cancelarConsultaPorTitulo(titulo);
    }
    
    
    
}
