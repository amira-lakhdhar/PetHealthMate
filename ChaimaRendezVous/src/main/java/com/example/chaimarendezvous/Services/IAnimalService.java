package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Animal;
import com.example.chaimarendezvous.Entity.Animal;

import java.util.List;

public interface IAnimalService {
    List<Animal> getAllAnimal();

    Animal getAnimalbyId(Long id);



    Animal createAnimal(Animal animal);

    Animal updateAnimal(Long id, Animal animal);

    void deleteAnimal(Long id);
}
