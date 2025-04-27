package com.unitins.agrotins.service;

import com.unitins.agrotins.dto.PesagemDTO;
import com.unitins.agrotins.models.Animal;
import com.unitins.agrotins.models.Pesagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PesagemService {

    @Transactional
    public Pesagem registrar(PesagemDTO pesagemDTO) {
        Animal animal = Animal.findById(pesagemDTO.tagRFID);
        if (animal == null) {
            throw new BadRequestException("Animal com tagRFID " + pesagemDTO.tagRFID + " não encontrado");
        }
        Pesagem pesagem = new Pesagem();
        pesagem.tagRFID = pesagemDTO.tagRFID;
        pesagem.peso = pesagemDTO.peso;
        pesagem.persist();
        return pesagem;
    }

    public List<Pesagem> historico(String tagRFID, int limit) {
        if (tagRFID != null) {
            return Pesagem.find("tagRFID = ?! order by data desc", tagRFID)
                    .range(0, limit - 1)
                    .list();
        }
        return Pesagem.find("order by data desc").range(0, limit - 1).list();
    }

    @Transactional
    public Pesagem atualizar(Long id, Pesagem pesagemAtualizada) {
        Pesagem pesagem = Pesagem.findById(id);
        if (pesagem == null) {
            throw new NotFoundException("Pesagem não encontrada");
        }
        pesagem.peso = pesagemAtualizada.peso;
        pesagem.data = LocalDateTime.now();
        return pesagem;
    }

    @Transactional
    public void deletar(Long id) {
        Pesagem pesagem = Pesagem.findById(id);
        if (pesagem == null) {
            throw new NotFoundException("Pesagem não encontrada");
        }
        pesagem.delete();
    }
}
