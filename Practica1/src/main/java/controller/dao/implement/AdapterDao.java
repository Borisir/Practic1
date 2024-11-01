package controller.dao.implement;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;
import com.google.gson.Gson;
import controller.tda.list.LinkedList;

public class AdapterDao<T> implements InterfazDao<T> {
    private Class<T> clazz;
    private Gson g;

    // Cambiamos a una ruta absoluta en la raíz del proyecto
    public static String filePath = "data/"; //src/main/java/Data/
    public AdapterDao(Class<T> clazz) {
        this.clazz = clazz;
        this.g = new Gson();
        // Crear el directorio data si no existe
        File dataDir = new File(filePath);
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
    }

    public T get(Integer id) throws Exception {
        LinkedList<T> list = listAll(); // Implementar según sea necesario
        if(!list.isEmpty()){
            T[] matriz = list.toArray();
            return matriz[id - 1];
        }
        return null;
    }

    public LinkedList<T> listAll() {
        LinkedList<T> list = new LinkedList<>();
        try {
            String data = readFile();
            T[] matrix = g.fromJson(data, com.google.gson.reflect.TypeToken.getArray(clazz).getType());
            list.toList(matrix);
        } catch (Exception e) {
            System.out.println("Error al leer la lista: " + e.getMessage());
            e.printStackTrace();
        }
        return list;
    }

    public void merge(T object, Integer index) throws Exception {
        LinkedList<T> list = listAll();
        list.update(object, index);
        String info = g.toJson(list.toArray());
        saveFile(info);
    }

    public void persist(T object) throws Exception {
        System.out.println("Persisting object: " + object);
        LinkedList<T> list = listAll();
        if (list == null) {
            System.out.println("La lista es null. Asegúrate de que el archivo JSON se esté leyendo correctamente.");
            return;
        }
        list.add(object);
        String info = g.toJson(list.toArray());
        System.out.println("Escribiendo datos al archivo: " + info);
        saveFile(info);
    }

    private String readFile() throws Exception {
        File file = new File(filePath + clazz.getSimpleName() + ".json");

        if (!file.exists()) {
            System.out.println("El archivo no existe, creando uno nuevo: " + file.getAbsolutePath());
            file.getParentFile().mkdirs();
            saveFile("[]");
        }

        StringBuilder sb = new StringBuilder();
        try (Scanner in = new Scanner(new FileReader(file))) {
            while (in.hasNextLine()) {
                sb.append(in.nextLine()).append("\n");
            }
        }
        return sb.toString().trim();
    }

    private void saveFile(String data) throws Exception {
        File file = new File(filePath + clazz.getSimpleName() + ".json");
        file.getParentFile().mkdirs();

        if (!file.exists()) {
            System.out.println("Creando el archivo JSON: " + file.getAbsolutePath());
            file.createNewFile();
        }

        try (FileWriter f = new FileWriter(file)) {
            f.write(data);
            f.flush();
            System.out.println("Datos guardados exitosamente en: " + file.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}