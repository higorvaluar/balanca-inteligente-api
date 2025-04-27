package com.unitins.agrotins.resource;

import com.unitins.agrotins.models.Animal;
import com.unitins.agrotins.service.AnimalService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/animais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResource {

    @Inject
    AnimalService animalService;

    @POST
    public Response cadastrar(Animal animal) {
        Animal novoAnimal = animalService.cadastrar(animal);
        return Response.ok(novoAnimal).status(201).build();
    }

    @GET
    public List<Animal> listar() {
        return animalService.listar();
    }

    @GET
    @Path("/{tagRFID}")
    public Response buscarPorTag(@PathParam("tagRFID") String tagRFID) {
        try {
            Animal animal = animalService.buscarPorTag(tagRFID);
            return Response.ok(animal).build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{tagRFID}")
    public Response atualizarAnimal(@PathParam("tagRFID") String tagRFID, Animal animalAtualizado) {
        try {
            Animal animal = animalService.atualizar(tagRFID, animalAtualizado);
            return Response.ok(animal).build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarAnimal(@PathParam("id") String tagRFID) {
        try {
            animalService.deletar(tagRFID);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }
}