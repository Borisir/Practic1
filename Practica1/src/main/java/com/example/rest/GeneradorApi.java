package com.example.rest;


import java.util.Collections;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import controller.dao.services.GeneradorServices;

@Path("/generador")
public class GeneradorApi {
    @Path("/list")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getgenerador(){
        HashMap map = new HashMap();
        GeneradorServices gs = new GeneradorServices();
        map.put("msg", "Listas de generadores");
        map.put("data", gs.listAll().toArray());
        if(gs.listAll().isEmpty()){
            map.put("data", new Object[]{});
        }
        return Response.ok(map).build();
    }


@Path("/create")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response save(HashMap map ){
   HashMap res = new HashMap<>();
   try {
        GeneradorServices gs = new GeneradorServices();
        gs.getGenerador().setModelo(map.get("modelo").toString());
        gs.getGenerador().setCosto(Float.parseFloat(map.get("costo").toString()));
        gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
        gs.getGenerador().setPodruccionEnergia(Float.parseFloat(map.get("podruccionEnergia").toString()));
        gs.getGenerador().setUso(map.get("uso").toString());
        gs.getGenerador().setNumeroSerie(map.get("numeroSerie").toString());

        gs.save();
        res.put("msg", "ok");
        res.put("data","Generador guardado");
        return Response.ok(res).build();
    } catch (Exception e) {
        res.put("msg", "error");
        res.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }    
  }

@Path("/list/{id}")
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getGenerador(@PathParam("id") Integer id){
    HashMap map = new HashMap();
    GeneradorServices gs = new GeneradorServices();
    try {
        gs.setGenerador(gs.get(id));
    } catch (Exception e) {
    }
    map.put("msg", "Generador");
    map.put("data", gs.getGenerador());
    if(gs.getGenerador().getId() == null){
        map.put("data", "No se encontro el generador");
        return Response.status(Response.Status.NOT_FOUND).entity(map).build();
    }
    return Response.ok(map).build();
    }



@Path("/update")
@POST
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response update(HashMap map ){
    HashMap res = new HashMap<>();
    try {
        GeneradorServices gs = new GeneradorServices();
        gs.getGenerador().setId(Integer.parseInt(map.get("id").toString()));
        gs.getGenerador().setModelo(map.get("modelo").toString());
        gs.getGenerador().setCosto(Float.parseFloat(map.get("costo").toString()));
        gs.getGenerador().setConsumoPorHora(Float.parseFloat(map.get("consumoPorHora").toString()));
        gs.getGenerador().setPodruccionEnergia(Float.parseFloat(map.get("podruccionEnergia").toString()));
        gs.getGenerador().setUso(map.get("uso").toString());
        gs.getGenerador().setNumeroSerie(map.get("numeroSerie").toString());

        gs.update();
        res.put("msg", "ok");
        res.put("data","Generador actualizado");
        return Response.ok(res).build();
    } catch (Exception e) {
        res.put("msg", "error");
        res.put("data", e.getMessage());
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(res).build();
    }    
  }

}



