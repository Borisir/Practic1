package controller.dao;

import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import models.Generador;

public class GeneradorDao extends AdapterDao<Generador> {
    private Generador generador;
    private LinkedList<Generador> listAll;

    public GeneradorDao(){
        super(Generador.class);
    }

    public Generador getGenerador(){
        if(generador == null) {
            generador = new Generador();
        }
        return this.generador;
    }
    
    public void setGenerador(Generador generador) {
        this.generador = generador;
    }

    public LinkedList<Generador> getListAll(){
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getGenerador().setId(id);
        persist(getGenerador());
        return true;
    }

    public Boolean update() throws Exception {
        this.merge(getGenerador(), getGenerador().getId() - 1);
        this.listAll = listAll();
        return true;
    }
}