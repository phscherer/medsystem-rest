package br.com.medsystem.dao;

public class DoutorDaoFactory {
    
    public static DoutorDaoFactory instance = new DoutorDaoFactory();
    private DoutorDAO doutorDao;
    
    private DoutorDaoFactory() {}
    
    public static DoutorDaoFactory getInstance() {
        return instance;
    }
    
    public DoutorDAO getConsultaDAO() {
        if(this.doutorDao == null)
            this.doutorDao = new DoutorDAO();
        return this.doutorDao;
    }
    
}
