package controller.dao.services;

import controller.dao.GeneradorDao;
import controller.tda.list.LinkedList;
import models.Generador;

public class GeneradorServices {
    private GeneradorDao obj;
    public GeneradorServices(){
        obj = new GeneradorDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }
    
    public LinkedList<Generador> listAll(){
        return obj.getListAll();
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setIdGenerador(Generador generador) {
        obj.setGenerador(generador);
    }
}
