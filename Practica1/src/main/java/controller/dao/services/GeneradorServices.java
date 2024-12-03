package controller.dao.services;

import controller.dao.GeneradorDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
import models.Generador;

public class GeneradorServices {
    private GeneradorDao obj;
    public GeneradorServices(){
        obj = new GeneradorDao();
    }

    public Boolean save() throws Exception {
        return obj.save();
    }

    public Boolean update() throws Exception {
        return obj.update();
    }
    
    public LinkedList<Generador> listAll(){
        return obj.getListAll();
    }

    public Generador getGenerador(){
        return obj.getGenerador();
    }

    public void setGenerador(Generador generador) {
        obj.setGenerador(generador);
    }

    public Generador get(Integer id) throws Exception {
        return obj.get(id);
    }

    public LinkedList<Generador> ordenarQuicksort(Integer type_order, String atributo) {
        return obj.ordenarQuicksort(type_order, atributo);
    }
    public LinkedList<Generador> ordenarMergeSort(Integer type_order, String atributo) {
        return obj.ordenarQuicksort(type_order, atributo);
    }
    public LinkedList<Generador> ordenarShellSort(Integer type_order, String atributo) {
        return obj.ordenarQuicksort(type_order, atributo);
    }

    public LinkedList<Generador> GeneradorsLineal(String criterio, String valor) throws ListEmptyException {
        return obj.buscarBinario(criterio, valor);
    }

    public LinkedList<Generador> buscarGeneradorBinario(String criterio, String valor) throws ListEmptyException {
        return obj.buscarLineal(criterio, valor);
    }
}
