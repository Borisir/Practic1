package controller.dao;


import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import models.Transaccion;

public class TransaccionDao extends AdapterDao<Transaccion> {
    private Transaccion transaccion;
    private LinkedList<Transaccion> listAll;

    public TransaccionDao(){
        super(Transaccion.class);

    }

    public Transaccion getTransaccion(){
        if(transaccion == null) {
           transaccion = new Transaccion();

        }
        return this.transaccion;

    }

    public void setTransaccion(Transaccion transaccion){
        this.transaccion = transaccion;
    }

    public LinkedList<Transaccion> getListAll(){
        if(this.listAll == null) {
            this.listAll = listAll();
        }
        return this.listAll;
    }

    public Boolean save() throws Exception {
        Integer id = getListAll().getSize()+1;
        getTransaccion().setId(id);
        persist(getTransaccion());
        return true;
    }
    
}
