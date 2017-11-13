package br.com.medsystem.dao;

public class ConsultaDaoFactory {
    
    public static ConsultaDaoFactory instance = new ConsultaDaoFactory();
    private ConsultaDAO consultaDao;
    
    private ConsultaDaoFactory() {}
    
    public static ConsultaDaoFactory getInstance() {
        return instance;
    }
    
    public ConsultaDAO getConsultaDAO() {
        if(this.consultaDao == null)
            this.consultaDao = new ConsultaDAO();
        return this.consultaDao;
    }
    
}
