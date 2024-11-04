package com.example.rest;


import java.util.Collections;
import java.util.HashMap;
import controller.tda.list.LinkedList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import controller.dao.services.FamiliaServices;
import models.Familia;

@Path("/familia")
public class FamiliaApi {
    

@Path("/lists/{numeroSerie}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getFamilia(@PathParam("numeroSerie") String numeroSerie) throws Exception {
    HashMap<String, Object> map = new HashMap<>();
    FamiliaServices fs = new FamiliaServices();

    LinkedList<Familia> lista = fs.listAll().filter(numeroSerie);

    if (lista.isEmpty()) {
        map.put("message", "No se encontraron familias con el generador asociado ");
        map.put("data", new Object[]{});    
    } else {
        map.put("message", "Familias encontradas");
        map.put("data", lista.toArray());
    }

      return Response.ok().entity(map).build();

    }

    @Path("/add/{numeroSerie}")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response addFamilia(HashMap<String, String> map, @PathParam("numeroSerie") String numeroSerie) throws Exception {
        HashMap<String, Object> res = new HashMap<>();
        try {
            FamiliaServices fs = new FamiliaServices();
            Familia familia =  fs.getFamilia();
            familia.setNombre(map.get("nombre"));
            familia.setNumeroMiembros(Integer.parseInt(map.get("numeroMiembros")));
            familia.setgeneradorasociado(numeroSerie);
            fs.setFamilia(familia);
            fs.save();
            res.put("message", "Familia agregada correctamente");
            return Response.ok().entity(res).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
            res.put("message", "Error al agregar familia");
            res.put("error", e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
            
        }
    }
}
