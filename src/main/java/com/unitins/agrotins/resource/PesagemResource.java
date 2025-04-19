package com.unitins.agrotins.resource;

import java.time.LocalDateTime;
import com.unitins.agrotins.dto.PesagemDTO;
import com.unitins.agrotins.models.Pesagem;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/pesagens")
@Produces(MediaType.APPLICATION_JSON)
public class PesagemResource {
    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrar(PesagemDTO pesagemDTO) {
        // Cria uma NOVA entidade
        Pesagem pesagem = new Pesagem();
        pesagem.tagRFID = pesagemDTO.tagRFID;
        pesagem.peso = pesagemDTO.peso;
        pesagem.persist(); // Persiste o objeto novo

        return Response.ok(pesagem).status(201).build();
    }

    @GET
    public List<Pesagem> historico(
            @QueryParam("tagRFID") String tagRFID,
            @QueryParam("limit") @DefaultValue("10") int limit
    ) {
        if (tagRFID != null) {
            return Pesagem.find("tagRFID = ?1 order by data desc", tagRFID)
                    .range(0, limit - 1)
                    .list();
        }
        return Pesagem.find("order by data desc").range(0, limit - 1).list();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response atualizarPesagem(@PathParam("id") Long id, Pesagem pesagemAtualizada) {
        Pesagem pesagem = Pesagem.findById(id);
        if (pesagem == null) {
            return Response.status(404).entity("Pesagem não encontrada").build();
        }

        pesagem.peso = pesagemAtualizada.peso;
        pesagem.data = LocalDateTime.now(); // atualiza a data

        return Response.ok(pesagem).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deletarPesagem(@PathParam("id") Long id) {
        Pesagem pesagem = Pesagem.findById(id);
        if (pesagem == null) {
            return Response.status(404).entity("Pesagem não encontrada").build();
        }

        pesagem.delete();
        return Response.noContent().build();
    }
}
