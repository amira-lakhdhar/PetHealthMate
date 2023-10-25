package com.example.chaimarendezvous.Controller;

import com.example.chaimarendezvous.Entity.Animal;
import com.example.chaimarendezvous.Services.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
@RequiredArgsConstructor
public class AnimalController {
    private final AnimalService animalS;


    @GetMapping("/")
    public List<Animal> getAllAnimal() {return animalS.getAllAnimal();}

    @GetMapping("/{id}")
    public Animal getAnimalById(@PathVariable Long id) {return animalS.getAnimalbyId (id); }

    @PostMapping("/")
    public Animal createAnimal(@RequestBody Animal animal) {
        return animalS.createAnimal(animal);
    }

    @PutMapping("/{id}")
    public Animal updatAnimal(@PathVariable Long id, @RequestBody Animal animal) { return animalS.updateAnimal(id, animal);
    }

    @DeleteMapping("/{id}")
    public void deleteAnimal(@PathVariable Long id) { animalS.deleteAnimal(id);
    }

}