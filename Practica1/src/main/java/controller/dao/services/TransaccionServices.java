package controller.dao.services;

import controller.dao.TransaccionDao;
import controller.tda.list.LinkedList;
import models.Transaccion;

public class TransaccionServices {
    private TransaccionDao obj;
    public TransaccionServices(){
        obj = new TransaccionDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public LinkedList<Transaccion> listAll(){
        return obj.getListAll();

    }

    public Transaccion getTransaccion(){
        return obj.getTransaccion();
    }

    public void setTransaccion(Transaccion transaccion) {
        obj.setTransaccion(transaccion);
    }
}
