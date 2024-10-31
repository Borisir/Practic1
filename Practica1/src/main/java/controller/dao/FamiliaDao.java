package controller.dao;


import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import models.Familia;
public class FamiliaDao extends AdapterDao<Familia> {
    private Familia familia;
    private LinkedList<Familia> listAll;

    public FamiliaDao(){
        super(Familia.class);
    }

    public Familia getFamilia(){
        if(familia == null) {
            familia = new Familia();
        }
        return this.familia;
    }
    
    public void setFamilia(Familia familia) {
        this.familia = familia;
    }

    public LinkedList<Familia> getListAll(){
        if (this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getFamilia().setId(id);
        persist(getFamilia());
        return true;
    }
}