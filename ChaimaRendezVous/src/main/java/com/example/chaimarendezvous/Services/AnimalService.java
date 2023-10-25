package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Animal;
import com.example.chaimarendezvous.Repository.AnimalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AnimalService implements IAnimalService {
    private final AnimalRepository animal;


    @Override
    public List<Animal> getAllAnimal() {
        return null;
    }

    @Override
    public Animal getAnimalbyId(Long id){
        return   animal.findById(id).orElse(null);
    }




    @Override
    public Animal createAnimal(Animal animal) {
        return null;
    }

    @Override
    public Animal updateAnimal(Long id, Animal animal) {
        return null;
    }

    @Override
    public void deleteAnimal(Long id) {

    }

    // Implémentez les méthodes de l'interface DossierMedicalService ici.
}
