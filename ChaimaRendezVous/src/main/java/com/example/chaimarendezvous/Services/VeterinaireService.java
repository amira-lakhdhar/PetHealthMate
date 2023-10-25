package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Veterinaire;
import com.example.chaimarendezvous.Repository.VeterinaireRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VeterinaireService implements IVeterinaire {

    private final VeterinaireRepository Veterinaire;


    @Override
    public List<Veterinaire> getAllVeterinaire() {
        return null;
    }

    @Override
    public Veterinaire getVeterinairebyId(Long id){
        return   Veterinaire.findById(id).orElse(null);
    }



    @Override
    public Veterinaire createVeterinaire(Veterinaire veterinaire) {
        return null;
    }

    @Override
    public Veterinaire updateVeterinaire(Long id, Veterinaire veterinaire) {
        return null;
    }

    @Override
    public void deleteVeterinaire(Long id) {

    }

}
