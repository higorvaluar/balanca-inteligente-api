package com.unitins.agrotins.service;

import com.unitins.agrotins.models.Animal;
import com.unitins.agrotins.models.Pesagem;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class AnimalService {

    @Transactional
    public Animal cadastrar(Animal animal) {
        if (animal.dataNascimento == null || animal.dataNascimento.isAfter(LocalDate.now())) {
            throw new BadRequestException("Data de nascimento inválida");
        }
        animal.persist();
        return animal;
    }

    public List<Animal> listar() {
        return Animal.listAll();
    }

    public Animal buscarPorTag(String tagRFID) {
        Animal animal = Animal.findById(tagRFID);
        if (animal == null) {
            throw new NotFoundException("Animal não encontrado");
        }
        return animal;
    }

    @Transactional
    public Animal atualizar(String tagRFID, Animal animalAtualizado) {
        Animal animal = Animal.findById(tagRFID);
        if (animal == null) {
            throw new NotFoundException("Animal não encontrado");
        }
        animal.nome = animalAtualizado.nome;
        animal.raca = animalAtualizado.raca;
        animal.dataNascimento = animalAtualizado.dataNascimento;
        return animal;
    }

    @Transactional
    public void deletar(String tagRFID) {
        Animal animal = Animal.findById(tagRFID);
        if (animal == null) {
            throw new NotFoundException("Animal não encontrado");
        }
        Pesagem.delete("tagRFID", tagRFID);
        animal.delete();
    }
}
