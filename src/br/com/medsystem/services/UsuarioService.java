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
import br.com.medsystem.dao.UsuarioDAO;
import br.com.medsystem.dao.UsuarioDaoFactory;
import br.com.medsystem.exceptions.UsuarioJaExisteException;
import br.com.medsystem.model.Usuario;
import br.com.medsystem.model.rest.Usuarios;

@Path("/usuarios")
@Consumes({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
@Produces({ MediaType.TEXT_XML, MediaType.APPLICATION_XML,
        MediaType.APPLICATION_JSON })
public class UsuarioService {
    
    private UsuarioDAO usuarioDao = UsuarioDaoFactory.getInstance().getUsuarioDAO();
    private static final int TAMANHO_PAGINA = 10;
    
    @GET
    @Path("/login/{nome}/{senha}")
    public Usuario enter(@PathParam("nome") String nomeDoUsuario, @PathParam("senha") String senha) {
        Usuario user = usuarioDao.validarLogin(nomeDoUsuario, senha);
        if (user != null)
            return user;
        throw new WebApplicationException(Status.NOT_FOUND);
    }
    
    @GET
    @Path("{nome}")
    public Usuario encontreUsuario(@PathParam("nome") String nomeDoUsuario) {
        Usuario user = usuarioDao.buscaPorNome(nomeDoUsuario);
        if (user != null)
            return user;
        throw new WebApplicationException(Status.NOT_FOUND);
    }
    
    @GET
    public Usuarios listeTodosOsUsuarios(@QueryParam("pagina") int pagina) {
        List<Usuario> users = usuarioDao.listaPaginada(pagina, TAMANHO_PAGINA);
        return new Usuarios(users);
    }
    
    @POST
    public Response criarUsuario(Usuario usuario) {
        
        try {
            usuarioDao.salva(usuario);
        } catch (UsuarioJaExisteException e) {
            throw new WebApplicationException(Status.CONFLICT);
        }

        URI uri = UriBuilder.fromPath("usuarios/{nome}").build(usuario.getNome());
        return Response.created(uri).entity(usuario).build();
    }

    @PUT
    @Path("{nome}")
    public void atualizarUsuario(@PathParam("nome") String nome, Usuario usuario) {
        encontreUsuario(nome);
        usuario.setNome(nome);
        usuarioDao.atualiza(usuario);
    }

    @DELETE
    @Path("{nome}")
    public void apagarUsuario(@PathParam("nome") String nomeDeUsuario) {
         TODO: usuarioDao.remove(nomeDeUsuario);
    }

}
