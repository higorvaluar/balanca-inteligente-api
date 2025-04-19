package com.unitins.agrotins.resource;

import com.unitins.agrotins.models.Animal;
import com.unitins.agrotins.models.Pesagem;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/animais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResource {
    @POST
    @Transactional
    public Response cadastrar(Animal animal) {
        animal.persist();
        return Response.ok(animal).status(201).build();
    }

    @GET
    public List<Animal> listar() {
        return Animal.listAll();
    }

    @GET
    @Path("/{tagRFID}")
    public Animal buscarPorTag(@PathParam("tagRFID") String tagRFID) {
        return Animal.findById(tagRFID);
    }

    @PUT
    @Path("/{tagRFID}")
    @Transactional
    public Response atualizarAnimal(@PathParam("tagRFID") String tagRFID, Animal animalAtualizado) {
        Animal animal = Animal.findById(tagRFID);
        if (animal == null) {
            return Response.status(404).entity("Animal não encontrado").build();
        }

        animal.nome = animalAtualizado.nome;
        animal.raca = animalAtualizado.raca;
        animal.dataNascimento = animalAtualizado.dataNascimento;

        return Response.ok(animal).build();
    }

    @DELETE
    @Path("/{tagRFID}")
    @Transactional
    public Response deletarAnimal(@PathParam("tagRFID") String tagRFID) {
        Animal animal = Animal.findById(tagRFID);
        if (animal == null) {
            return Response.status(404).entity("Animal não encontrado").build();
        }

        // Primeiro deleta as pesagens associadas
        Pesagem.delete("tagRFID", tagRFID);
        animal.delete();

        return Response.noContent().build();
    }
}
