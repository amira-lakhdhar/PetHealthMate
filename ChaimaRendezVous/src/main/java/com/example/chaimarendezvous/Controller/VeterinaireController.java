package com.example.chaimarendezvous.Controller;

import com.example.chaimarendezvous.Entity.Veterinaire;
import com.example.chaimarendezvous.Services.VeterinaireService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/veterinaire")
@AllArgsConstructor
public class VeterinaireController {





        private final VeterinaireService veterinaireS;


        @GetMapping("/")
        public List<Veterinaire> getAllVeterinaire() {return veterinaireS.getAllVeterinaire();}

        @GetMapping("/{id}")
        public Veterinaire getVeterinaireById(@PathVariable Long id) {return veterinaireS.getVeterinairebyId (id); }

        @PostMapping("/")
        public Veterinaire createVeterinaire(@RequestBody Veterinaire veterinaire) {return veterinaireS.createVeterinaire(veterinaire);
        }

        @PutMapping("/{id}")
        public Veterinaire updatveterinaire(@PathVariable Long id, @RequestBody Veterinaire veterinaire) { return veterinaireS.updateVeterinaire(id, veterinaire);
        }
    @DeleteMapping("/{id}")
    public void deleteVeterinaire(@PathVariable Long id) { veterinaireS.deleteVeterinaire(id);
    }
    }

