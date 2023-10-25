package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Veterinaire;

import java.util.List;

public interface IVeterinaire {
    List<Veterinaire> getAllVeterinaire();

    Veterinaire getVeterinairebyId(Long id);

    Veterinaire createVeterinaire(Veterinaire veterinaire);

    Veterinaire updateVeterinaire(Long id, Veterinaire veterinaire);

    void deleteVeterinaire(Long id);
}
