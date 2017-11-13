package br.com.medsystem.dao;

public class UsuarioDaoFactory {
    public static UsuarioDaoFactory instance = new UsuarioDaoFactory();
    private UsuarioDAO usuarioDao;
    
    private UsuarioDaoFactory() {}
    
    public static UsuarioDaoFactory getInstance() {
        return instance;
    }
    
    public UsuarioDAO getUsuarioDAO() {
        if(this.usuarioDao == null)
            this.usuarioDao = new UsuarioDAO();
        return this.usuarioDao;
    }
    
}
