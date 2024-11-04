package com.example.rest;


import java.util.HashMap;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;
import java.util.logging.Level;

import controller.dao.services.GeneradorServices;


@Path("/generadorr")
public class AppTest {

    private static final Logger logger = Logger.getLogger(AppTest.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getgenerador(){
        HashMap<String, Object> mapa = new HashMap<>();
        GeneradorServices gs = new GeneradorServices();
        String aux = "Listas de generadores";
        try {
            gs.getGenerador().setModelo("modelo");
            gs.getGenerador().setCosto(100);
            gs.getGenerador().setConsumoPorHora(100);
            gs.getGenerador().setPodruccionEnergia(100);
            gs.getGenerador().setUso("uso");
            gs.getGenerador().setNumeroSerie("numeroSerie");
            gs.save();

            aux = "lista  vacias" + gs.listAll().isEmpty();
            logger.info("Generador guardado" + aux);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error al guardar generador" + e.getMessage(), e);
            e.printStackTrace();
            mapa.put("msg", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(mapa).build();
        }

        mapa.put("msg", "Listas de generadores");	
        mapa.put("data", "test" + aux);
        
        return Response.ok(mapa).build();

    }
}