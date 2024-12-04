package com.example.rest;

import java.util.HashMap;
import java.util.Random;
import java.util.logging.Logger;
import java.util.logging.Level;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.dao.services.GeneradorServices;
@Path("/inversion") // Cambiado para que coincida con la URL que mostramos
public class AppTest {

    private static final Logger logger = Logger.getLogger(AppTest.class.getName());
    private static final Random random = new Random();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response testSortingAndSearching() {
        HashMap<String, Object> response = new HashMap<>();
        GeneradorServices service = new GeneradorServices();

        int[] sizes = {10000, 20000, 25000};
        HashMap<Integer, HashMap<String, String>> results = new HashMap<>();

        try {
            for (int size : sizes) {
                int[] array = generateRandomArray(size);

                // Medir tiempo de QuickSort
                long startQuickSort = System.nanoTime();
                service.ordenarQuicksort(1, "costo");
                long endQuickSort = System.nanoTime();

                // Medir tiempo de MergeSort
                long startMergeSort = System.nanoTime();
                service.ordenarMergeSort(1, "costo");
                long endMergeSort = System.nanoTime();

                // Medir tiempo de ShellSort
                long startShellSort = System.nanoTime();
                service.ordenarShellSort(1, "costo");
                long endShellSort = System.nanoTime();

                // Medir tiempo de búsqueda binaria
                long startBinarySearch = System.nanoTime();
                service.buscarGeneradorBinario("costo", String.valueOf(array[array.length / 2]));
                long endBinarySearch = System.nanoTime();

                // Medir tiempo de búsqueda lineal
                long startLinearSearch = System.nanoTime();
                service.GeneradorsLineal("costo", String.valueOf(array[array.length / 2]));
                long endLinearSearch = System.nanoTime();

                // Registro de tiempos
                HashMap<String, String> timeData = new HashMap<>();
                timeData.put("mergeSort", formatTimeInSeconds(endMergeSort - startMergeSort));
                timeData.put("shellSort", formatTimeInSeconds(endShellSort - startShellSort));
                timeData.put("quickSort", formatTimeInSeconds(endQuickSort - startQuickSort));
                timeData.put("binarySearch", formatTimeInSeconds(endBinarySearch - startBinarySearch));
                timeData.put("linearSearch", formatTimeInSeconds(endLinearSearch - startLinearSearch));
                results.put(size, timeData);

                // Imprimir resultados en consola
                System.out.println("Tamaño del arreglo: " + size);
                timeData.forEach((alg, time) ->
                    System.out.println("  " + alg + ": " + time + " segundos"));
            }

            response.put("msg", "Pruebas completadas con éxito");
            response.put("results", results);

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error durante las pruebas: " + e.getMessage(), e);
            response.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
        }

        return Response.ok(response).build();
    }

    private int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }
        return array;
    }

    private String formatTimeInSeconds(long nanoTime) {
        double seconds = nanoTime / 1e9;
        return String.format("%.9f", seconds); // Formato con 9 decimales
    }
}