package com.unitins.agrotins.resource;

import com.unitins.agrotins.dto.PesagemDTO;
import com.unitins.agrotins.models.Pesagem;
import com.unitins.agrotins.service.PesagemService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pesagens")
@Produces(MediaType.APPLICATION_JSON)
public class PesagemResource {

    @Inject
    PesagemService pesagemService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(PesagemDTO pesagemDTO) {
        Pesagem pesagem = pesagemService.registrar(pesagemDTO);
        return Response.ok(pesagem).status(201).build();
    }

    @GET
    public List<Pesagem> historico(
            @QueryParam("tagRFID") String tagRFID,
            @QueryParam("limit") @DefaultValue("10") int limit) {
        return pesagemService.historico(tagRFID, limit);
    }

    @PUT
    @Path("/{id}")
    public Response atualizarPesagem(@PathParam("id") Long id, Pesagem pesagemAtualizada) {
        try {
            Pesagem pesagem = pesagemService.atualizar(id, pesagemAtualizada);
            return Response.ok(pesagem).build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletarPesagem(@PathParam("id") Long id) {
        try {
            pesagemService.deletar(id);
            return Response.noContent().build();
        } catch (NotFoundException e) {
            return Response.status(404).entity(e.getMessage()).build();
        }
    }
}