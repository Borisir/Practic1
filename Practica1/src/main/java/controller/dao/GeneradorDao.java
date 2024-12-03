package controller.dao;

import java.util.Arrays;

import controller.dao.implement.AdapterDao;
import controller.tda.list.LinkedList;
import controller.tda.list.ListEmptyException;
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

    public LinkedList<Generador> ordenarQuicksort(Integer type_order, String atributo) {
        LinkedList<Generador> lista = getListAll();
        if (!lista.isEmpty()) {
            Generador[] arreglo = lista.toArray();
            lista.reset();
            quickSort(arreglo, 0, arreglo.length - 1, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void quickSort(Generador[] arr, int low, int high, Integer type_order, String atributo) {
        if (low < high) {
            int pi = partition(arr, low, high, type_order, atributo);
            quickSort(arr, low, pi - 1, type_order, atributo);  // Lado izquierdo
            quickSort(arr, pi + 1, high, type_order, atributo); // Lado derecho
        }
    }
    
    private int partition(Generador[] arr, int low, int high, Integer type_order, String atributo) {
        Generador pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparar(arr[j], pivot, type_order, atributo)) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }
    
    private boolean comparar(Generador a, Generador b, Integer type_order, String atributo) {
        if (atributo.equalsIgnoreCase("consumoPorHora")) {
            return type_order == 1 
                ? a.getConsumoPorHora() <= b.getConsumoPorHora()
                : a.getConsumoPorHora() >= b.getConsumoPorHora();
        } else if (atributo.equalsIgnoreCase("costo")) {
            return type_order == 1 
                ? a.getCosto() <= b.getCosto()
                : a.getCosto() >= b.getCosto();
        } else if (atributo.equalsIgnoreCase("podruccionEnergia")) {
            return type_order == 1 
                ? a.getPodruccionEnergia() <= b.getPodruccionEnergia()
                : a.getPodruccionEnergia() >= b.getPodruccionEnergia();
        } else {
            throw new IllegalArgumentException("Atributo no válido: " + atributo);
        }
    }
    
    

    private void swap(Generador[] arr, int i, int j) {
        Generador temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public LinkedList<Generador> ordenarMergeSort(Integer type_order, String atributo) {
        LinkedList<Generador> lista = getListAll();
        if (!lista.isEmpty()) {
            Generador[] arreglo = lista.toArray();
            lista.reset();
            mergeSort(arreglo, 0, arreglo.length - 1, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void mergeSort(Generador[] arr, int left, int right, Integer type_order, String atributo) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, type_order, atributo);
            mergeSort(arr, mid + 1, right, type_order, atributo);
            merge(arr, left, mid, right, type_order, atributo);
        }
    }
    
    private void merge(Generador[] arr, int left, int mid, int right, Integer type_order, String atributo) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
    
        Generador[] leftArr = new Generador[n1];
        Generador[] rightArr = new Generador[n2];
    
        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);
    
        int i = 0, j = 0, k = left;
    
        while (i < n1 && j < n2) {
            if (comparar(leftArr[i], rightArr[j], type_order, atributo)) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }
    
        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    public LinkedList<Generador> ordenarShellSort(Integer type_order, String atributo) {
        LinkedList<Generador> lista = getListAll();
        if (!lista.isEmpty()) {
            Generador[] arreglo = lista.toArray();
            lista.reset();
            shellSort(arreglo, type_order, atributo);
            lista.toList(arreglo);
        }
        return lista;
    }
    
    private void shellSort(Generador[] arr, Integer type_order, String atributo) {
        int n = arr.length;
        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Generador temp = arr[i];
                int j;
                for (j = i; j >= gap && comparar(arr[j - gap], temp, type_order, atributo); j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public LinkedList<Generador> buscarBinario(String criterio, String valor) throws ListEmptyException {
        LinkedList<Generador> lista = new LinkedList<>();
        LinkedList<Generador> listita = getListAll(); // Obtener todos los Generadors
    
        if (listita == null || listita.isEmpty()) {
            throw new ListEmptyException("Error: La lista está vacía.");
        }
    
        Generador[] Generadors = listita.toArray(); // Convertir la lista enlazada a un arreglo
        Arrays.sort(Generadors, (p1, p2) -> { // Ordenar según el criterio
            switch (criterio.toLowerCase()) {
                case "modelo":
                    return p1.getModelo().compareToIgnoreCase(p2.getModelo());
                case "costo":
                    return Float.compare((float) p1.getCosto(), (float) p2.getCosto());
                case "uso":
                    return p1.getUso().compareToIgnoreCase(p2.getUso());
                default:
                    throw new IllegalArgumentException("Criterio de ordenación no válido: " + criterio);
            }
        });
    
        int low = 0, high = Generadors.length - 1;
    
        while (low <= high) {
            int mid = (low + high) / 2;
            Generador midGenerador = Generadors[mid];
            boolean match = false;
    
            // Comparar según el criterio
            switch (criterio.toLowerCase()) {
                case "modelo":
                    match = midGenerador.getModelo().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "costo":
                    try {
                        float costo = Float.parseFloat(valor);
                        match = (midGenerador.getCosto() == costo || 
                                 String.valueOf(midGenerador.getCosto()).startsWith(valor));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'costo' debe ser un número válido.");
                    }
                    break;
                case "uso":
                    match = midGenerador.getUso().toLowerCase().contains(valor.toLowerCase());
                    break;
                default:
                    throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
            }
    
            // Si coincide, agregarlo a la lista
            if (match) {
                lista.add(midGenerador);
    
                // Buscar hacia la izquierda
                int left = mid - 1;
                while (left >= 0) {
                    Generador leftGenerador = Generadors[left];
                    boolean leftMatch = false;
                    switch (criterio.toLowerCase()) {
                        case "modelo":
                            leftMatch = leftGenerador.getModelo().toLowerCase().contains(valor.toLowerCase());
                            break;
                        case "costo":
                            leftMatch = String.valueOf(leftGenerador.getCosto()).startsWith(valor);
                            break;
                        case "uso":
                            leftMatch = leftGenerador.getUso().toLowerCase().contains(valor.toLowerCase());
                            break;
                    }
                    if (leftMatch) {
                        lista.add(leftGenerador);
                    } else {
                        break;
                    }
                    left--;
                }
    
                // Buscar hacia la derecha
                int right = mid + 1;
                while (right < Generadors.length) {
                    Generador rightGenerador = Generadors[right];
                    boolean rightMatch = false;
                    switch (criterio.toLowerCase()) {
                        case "modelo":
                            rightMatch = rightGenerador.getModelo().toLowerCase().contains(valor.toLowerCase());
                            break;
                        case "costo":
                            rightMatch = String.valueOf(rightGenerador.getCosto()).startsWith(valor);
                            break;
                        case "uso":
                            rightMatch = rightGenerador.getUso().toLowerCase().contains(valor.toLowerCase());
                            break;
                    }
                    if (rightMatch) {
                        lista.add(rightGenerador);
                    } else {
                        break;
                    }
                    right++;
                }
            }
    
            // Ajustar los límites
            switch (criterio.toLowerCase()) {
                case "modelo":
                    if (midGenerador.getModelo().compareToIgnoreCase(valor) < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                    break;
                case "costo":
                    try {
                        float costo = Float.parseFloat(valor);
                        if (midGenerador.getCosto() < costo) {
                            low = mid + 1;
                        } else {
                            high = mid - 1;
                        }
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'costo' debe ser un número válido.");
                    }
                    break;
                case "uso":
                    if (midGenerador.getUso().compareToIgnoreCase(valor) < 0) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                    break;
            }
        }
    
        return lista;
    }

    public LinkedList<Generador> buscarLineal(String criterio, String valor) throws ListEmptyException {
        LinkedList<Generador> lista = new LinkedList<>();
        LinkedList<Generador> listita = getListAll(); // Obtener todos los Generadors
    
        if (listita == null || listita.isEmpty()) {
            throw new ListEmptyException("Error: La lista está vacía.");
        }
    
        Generador[] Generadors = listita.toArray(); // Convertir la lista enlazada a un arreglo
    
        for (Generador Generador : Generadors) {
            boolean match = false;
    
            // Comparar según el criterio
            switch (criterio.toLowerCase()) {
                case "modelo":
                    match = Generador.getModelo().toLowerCase().contains(valor.toLowerCase());
                    break;
                case "costo":
                    try {
                        float costo = Float.parseFloat(valor);
                        match = (Generador.getCosto() == costo || 
                                 String.valueOf(Generador.getCosto()).startsWith(valor));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("El valor para 'costo' debe ser un número válido.");
                    }
                    break;
                case "uso":
                    match = Generador.getUso().toLowerCase().contains(valor.toLowerCase());
                    break;
                default:
                    throw new IllegalArgumentException("Criterio de búsqueda no válido: " + criterio);
            }
    
            if (match) {
                lista.add(Generador); // Agregar todos los Generadors coincidentes
            }
        }
    
        return lista;
    }
    

}