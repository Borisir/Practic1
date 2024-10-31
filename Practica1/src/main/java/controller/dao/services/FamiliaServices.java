package controller.dao.services;

import controller.dao.FamiliaDao;
import controller.tda.list.LinkedList;
import models.Familia;

public class FamiliaServices {
    private FamiliaDao obj;
    public FamiliaServices(){
        obj = new FamiliaDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }
    
    public LinkedList<Familia> listAll(){
        return obj.getListAll();
    }

    public Familia getFamilia(){
        return obj.getFamilia();
    }

    public void setIdFamilia(Familia familia) {
        obj.setFamilia(familia);
    }
}